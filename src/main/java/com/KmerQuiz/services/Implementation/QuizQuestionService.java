package com.KmerQuiz.services.Implementation;

import com.KmerQuiz.dto.QuizQuestionDTO;
import com.KmerQuiz.entities.IdQuizQquestion;
import com.KmerQuiz.entities.Question;
import com.KmerQuiz.entities.Quiz;
import com.KmerQuiz.entities.QuizQuestion;
import com.KmerQuiz.exceptions.DuplicatedRessourcException;
import com.KmerQuiz.exceptions.RessourceNotFoundException;
import com.KmerQuiz.mappers.QuizQuestionMapper;
import com.KmerQuiz.repositories.QuestionRepository;
import com.KmerQuiz.repositories.QuizQuestionRepository;
import com.KmerQuiz.repositories.QuizRepository;
import com.KmerQuiz.services.Interface.QuizQuestionServiceInt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuizQuestionService implements QuizQuestionServiceInt {

    private final QuizQuestionRepository quizQuestionRepository;
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final QuizQuestionMapper quizQuestionMapper;

    public QuizQuestionService(
            QuizQuestionRepository quizQuestionRepository,
            QuizRepository quizRepository,
            QuestionRepository questionRepository,
            QuizQuestionMapper quizQuestionMapper
    ) {
        this.quizQuestionRepository = quizQuestionRepository;
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.quizQuestionMapper = quizQuestionMapper;
    }

    @Override
    public QuizQuestionDTO addQuestionToQuiz(Long idQuiz, Long idQuestion, boolean status) {
        Quiz quiz = quizRepository.findById(idQuiz)
                .orElseThrow(() -> new RessourceNotFoundException("Quiz", "idQuiz", idQuiz));

        Question question = questionRepository.findById(idQuestion)
                .orElseThrow(() -> new RessourceNotFoundException("Question", "idQuestion", idQuestion));

        IdQuizQquestion id = new IdQuizQquestion(idQuiz, idQuestion);
        if (quizQuestionRepository.existsById(id)) {
            throw new DuplicatedRessourcException("QuizQuestion already exists for quiz " + idQuiz + " and question " + idQuestion);
        }

        QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setIdQuizQquestion(id);
        quizQuestion.setQuiz(quiz);
        quizQuestion.setQuestion(question);
        quizQuestion.setStatus(status);

        QuizQuestion saved = quizQuestionRepository.save(quizQuestion);
        return quizQuestionMapper.toDTO(saved);
    }

    @Override
    public void removeQuestionFromQuiz(Long idQuiz, Long idQuestion) {
        IdQuizQquestion id = new IdQuizQquestion(idQuiz, idQuestion);
        if (!quizQuestionRepository.existsById(id)) {
            throw new RessourceNotFoundException("QuizQuestion not found for quiz " + idQuiz + " and question " + idQuestion);
        }
        quizQuestionRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuizQuestionDTO> getQuestionsByQuiz(Long idQuiz) {
        return quizQuestionMapper.toDTOList(quizQuestionRepository.findByIdQuizQquestion_IdQuiz(idQuiz));
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuizQuestionDTO> getQuizzesByQuestion(Long idQuestion) {
        return quizQuestionMapper.toDTOList(quizQuestionRepository.findByIdQuizQquestion_IdQquestion(idQuestion));
    }

    @Override
    public QuizQuestionDTO updateQuizQuestionStatus(Long idQuiz, Long idQuestion, boolean status) {
        IdQuizQquestion id = new IdQuizQquestion(idQuiz, idQuestion);
        QuizQuestion quizQuestion = quizQuestionRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("QuizQuestion not found for quiz " + idQuiz + " and question " + idQuestion));

        quizQuestion.setStatus(status);
        QuizQuestion updated = quizQuestionRepository.save(quizQuestion);
        return quizQuestionMapper.toDTO(updated);
    }
}
