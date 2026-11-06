package com.KmerQuiz.services.Implementation;

import com.KmerQuiz.dto.QuestionResponseDTO;
import com.KmerQuiz.dto.QuestionRequestDTO;
import com.KmerQuiz.dto.ResponseRequestDTO;
import com.KmerQuiz.entities.Category;
import com.KmerQuiz.entities.Difficulty;
import com.KmerQuiz.entities.Question;
import com.KmerQuiz.entities.Response;
import com.KmerQuiz.exceptions.RessourceNotFoundException;
import com.KmerQuiz.mappers.QuestioMapper;
import com.KmerQuiz.mappers.ResponseMapper;
import com.KmerQuiz.repositories.CategoryRepository;
import com.KmerQuiz.repositories.DifficultyRepository;
import com.KmerQuiz.repositories.QuestionRepository;
import com.KmerQuiz.repositories.ResponseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class QuestionResponseFactoryService {

    private final QuestionRepository questionRepository;
    private final DifficultyRepository difficultyRepository;
    private final CategoryRepository categoryRepository;
    private final ResponseRepository responseRepository;
    private final QuestioMapper questionMapper;
    private final ResponseMapper responseMapper;

    public QuestionResponseFactoryService(
            QuestionRepository questionRepository,
            DifficultyRepository difficultyRepository,
            CategoryRepository categoryRepository,
            ResponseRepository responseRepository,
            QuestioMapper questionMapper,
            ResponseMapper responseMapper
    ) {
        this.questionRepository = questionRepository;
        this.difficultyRepository = difficultyRepository;
        this.categoryRepository = categoryRepository;
        this.responseRepository = responseRepository;
        this.questionMapper = questionMapper;
        this.responseMapper = responseMapper;
    }

    /**
     * Atomically creates a question with all its possible response choices.
     */
    public QuestionResponseDTO createQuestionWithResponses(QuestionRequestDTO questionDTO, List<ResponseRequestDTO> responses) {
        if (responses == null || responses.isEmpty()) {
            throw new IllegalArgumentException("A question must have at least one response option");
        }

        boolean hasCorrectResponse = responses.stream().anyMatch(ResponseRequestDTO::isCorrect);
        if (!hasCorrectResponse) {
            throw new IllegalArgumentException("At least one response option must be marked as correct");
        }

        Difficulty difficulty = difficultyRepository.findById(questionDTO.idDifficulty())
                .orElseThrow(() -> new RessourceNotFoundException("Difficulty", "idDifficulty", questionDTO.idDifficulty()));

        List<Category> categories = categoryRepository.findAllById(questionDTO.idCategories());

        Question question = questionMapper.toEntity(questionDTO);
        question.setDifficulty(difficulty);
        question.setCategories(categories);

        Question savedQuestion = questionRepository.save(question);

        List<Response> responseEntities = new ArrayList<>();
        for (ResponseRequestDTO respDto : responses) {
            Response response = responseMapper.toEntity(respDto);
            response.setQuestion(savedQuestion);
            responseEntities.add(response);
        }
        responseRepository.saveAll(responseEntities);

        QuestionResponseDTO baseDTO = questionMapper.toResponseDTO(savedQuestion);
        return new QuestionResponseDTO(
                savedQuestion.getIdQuestion(),
                savedQuestion.getQuestionText1(),
                savedQuestion.getQuestionText2(),
                savedQuestion.getQuestionText3(),
                savedQuestion.getDurationQuestion(),
                baseDTO.difficulty(),
                baseDTO.categories(),
                responseMapper.toResponseDTOList(responseEntities),
                new ArrayList<>()
        );
    }
}
