package com.KmerQuiz.services.Implementation;

import com.KmerQuiz.dto.QuestionResponseDTO;
import com.KmerQuiz.dto.QuestionRequestDTO;
import com.KmerQuiz.dto.ResponseResponseDTO;
import com.KmerQuiz.entities.Category;
import com.KmerQuiz.entities.Difficulty;
import com.KmerQuiz.entities.Question;
import com.KmerQuiz.exceptions.RessourceNotFoundException;
import com.KmerQuiz.mappers.QuestioMapper;
import com.KmerQuiz.mappers.ResponseMapper;
import com.KmerQuiz.repositories.CategoryRepository;
import com.KmerQuiz.repositories.DifficultyRepository;
import com.KmerQuiz.repositories.QuestionRepository;
import com.KmerQuiz.repositories.ResponseRepository;
import com.KmerQuiz.services.Interface.QuestionServiceInt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class QuestionService implements QuestionServiceInt {

    private final QuestionRepository questionRepository;
    private final DifficultyRepository difficultyRepository;
    private final CategoryRepository categoryRepository;
    private final ResponseRepository responseRepository;
    private final QuestioMapper questionMapper;
    private final ResponseMapper responseMapper;

    public QuestionService(
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

    @Override
    public QuestionResponseDTO createQuestion(QuestionRequestDTO requestDTO) {
        Difficulty difficulty = difficultyRepository.findById(requestDTO.idDifficulty())
                .orElseThrow(() -> new RessourceNotFoundException("Difficulty", "idDifficulty", requestDTO.idDifficulty()));

        List<Category> categories = categoryRepository.findAllById(requestDTO.idCategories());
        if (categories.isEmpty() && !requestDTO.idCategories().isEmpty()) {
            throw new RessourceNotFoundException("Categories", "idCategories", requestDTO.idCategories());
        }

        Question question = questionMapper.toEntity(requestDTO);
        question.setDifficulty(difficulty);
        question.setCategories(categories);

        Question savedQuestion = questionRepository.save(question);
        return mapToDetailedDTO(savedQuestion);
    }

    @Override
    public QuestionResponseDTO updateQuestion(Long idQuestion, QuestionRequestDTO requestDTO) {
        Question existingQuestion = questionRepository.findById(idQuestion)
                .orElseThrow(() -> new RessourceNotFoundException("Question", "idQuestion", idQuestion));

        Difficulty difficulty = difficultyRepository.findById(requestDTO.idDifficulty())
                .orElseThrow(() -> new RessourceNotFoundException("Difficulty", "idDifficulty", requestDTO.idDifficulty()));

        List<Category> categories = categoryRepository.findAllById(requestDTO.idCategories());

        questionMapper.updateEntityFromDTO(requestDTO, existingQuestion);
        existingQuestion.setDifficulty(difficulty);
        existingQuestion.setCategories(categories);

        Question updatedQuestion = questionRepository.save(existingQuestion);
        return mapToDetailedDTO(updatedQuestion);
    }

    @Override
    @Transactional(readOnly = true)
    public QuestionResponseDTO getQuestionById(Long idQuestion) {
        Question question = questionRepository.findById(idQuestion)
                .orElseThrow(() -> new RessourceNotFoundException("Question", "idQuestion", idQuestion));
        return mapToDetailedDTO(question);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionResponseDTO> getAllQuestions() {
        return questionRepository.findAll().stream()
                .map(this::mapToDetailedDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionResponseDTO> getQuestionsByDifficulty(Long idDifficulty) {
        return questionRepository.findByDifficulty_IdDifficulty(idDifficulty).stream()
                .map(this::mapToDetailedDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionResponseDTO> getQuestionsByCategory(Long idCategory) {
        return questionRepository.findByCategories_IdCategory(idCategory).stream()
                .map(this::mapToDetailedDTO)
                .toList();
    }

    @Override
    public void deleteQuestion(Long idQuestion) {
        if (!questionRepository.existsById(idQuestion)) {
            throw new RessourceNotFoundException("Question", "idQuestion", idQuestion);
        }
        questionRepository.deleteById(idQuestion);
    }

    private QuestionResponseDTO mapToDetailedDTO(Question question) {
        QuestionResponseDTO baseDTO = questionMapper.toResponseDTO(question);
        List<ResponseResponseDTO> responses = responseMapper.toResponseDTOList(
                responseRepository.findByQuestion_IdQuestion(question.getIdQuestion())
        );

        return new QuestionResponseDTO(
                baseDTO.idQuestion(),
                baseDTO.questionText1(),
                baseDTO.questionText2(),
                baseDTO.questionText3(),
                baseDTO.durationQuestion(),
                baseDTO.difficulty(),
                baseDTO.categories(),
                responses != null ? responses : new ArrayList<>(),
                baseDTO.ressources() != null ? baseDTO.ressources() : new ArrayList<>()
        );
    }
}
