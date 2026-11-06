package com.KmerQuiz.services.Implementation;

import com.KmerQuiz.dto.QuizRequestDTO;
import com.KmerQuiz.dto.QuizResponseDTO;
import com.KmerQuiz.entities.Party;
import com.KmerQuiz.entities.Quiz;
import com.KmerQuiz.entities.User;
import com.KmerQuiz.exceptions.RessourceNotFoundException;
import com.KmerQuiz.mappers.QuizMapper;
import com.KmerQuiz.repositories.PartyRepository;
import com.KmerQuiz.repositories.QuizRepository;
import com.KmerQuiz.repositories.UserRepository;
import com.KmerQuiz.services.Interface.QuizServiceInt;
import com.KmerQuiz.utils.PlayStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuizService implements QuizServiceInt {

    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final PartyRepository partyRepository;
    private final QuizMapper quizMapper;

    public QuizService(
            QuizRepository quizRepository,
            UserRepository userRepository,
            PartyRepository partyRepository,
            QuizMapper quizMapper
    ) {
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
        this.partyRepository = partyRepository;
        this.quizMapper = quizMapper;
    }

    @Override
    public QuizResponseDTO createQuiz(QuizRequestDTO requestDTO) {
        User user = userRepository.findById(requestDTO.idUser())
                .orElseThrow(() -> new RessourceNotFoundException("User", "idUser", requestDTO.idUser()));

        Long codePartyLong;
        try {
            codePartyLong = Long.parseLong(requestDTO.codeParty());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid party code format: " + requestDTO.codeParty());
        }

        Party party = partyRepository.findById(codePartyLong)
                .orElseThrow(() -> new RessourceNotFoundException("Party", "codeParty", requestDTO.codeParty()));

        Quiz quiz = quizMapper.toEntity(requestDTO);
        quiz.setUser(user);
        quiz.setParty(party);
        quiz.setQuizStatus(PlayStatus.Not_STARTED);
        quiz.setValidQuiz(true);

        Quiz savedQuiz = quizRepository.save(quiz);
        return quizMapper.toResponseDTO(savedQuiz);
    }

    @Override
    public QuizResponseDTO updateQuiz(Long idQuiz, QuizRequestDTO requestDTO) {
        Quiz existingQuiz = quizRepository.findById(idQuiz)
                .orElseThrow(() -> new RessourceNotFoundException("Quiz", "idQuiz", idQuiz));

        User user = userRepository.findById(requestDTO.idUser())
                .orElseThrow(() -> new RessourceNotFoundException("User", "idUser", requestDTO.idUser()));

        Long codePartyLong;
        try {
            codePartyLong = Long.parseLong(requestDTO.codeParty());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid party code format: " + requestDTO.codeParty());
        }

        Party party = partyRepository.findById(codePartyLong)
                .orElseThrow(() -> new RessourceNotFoundException("Party", "codeParty", requestDTO.codeParty()));

        quizMapper.updateEntityFromDTO(requestDTO, existingQuiz);
        existingQuiz.setUser(user);
        existingQuiz.setParty(party);

        Quiz updatedQuiz = quizRepository.save(existingQuiz);
        return quizMapper.toResponseDTO(updatedQuiz);
    }

    @Override
    @Transactional(readOnly = true)
    public QuizResponseDTO getQuizById(Long idQuiz) {
        Quiz quiz = quizRepository.findById(idQuiz)
                .orElseThrow(() -> new RessourceNotFoundException("Quiz", "idQuiz", idQuiz));
        return quizMapper.toResponseDTO(quiz);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuizResponseDTO> getAllQuizzes() {
        return quizMapper.toResponseDTOList(quizRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuizResponseDTO> getQuizzesByUser(String idUser) {
        return quizMapper.toResponseDTOList(quizRepository.findByUser_IdUser(idUser));
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuizResponseDTO> getQuizzesByParty(Long codeParty) {
        return quizMapper.toResponseDTOList(quizRepository.findByParty_CodeParty(codeParty));
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuizResponseDTO> getQuizzesByStatus(PlayStatus status) {
        return quizMapper.toResponseDTOList(quizRepository.findByQuizStatus(status));
    }

    @Override
    public QuizResponseDTO validateQuiz(Long idQuiz, boolean isValid) {
        Quiz quiz = quizRepository.findById(idQuiz)
                .orElseThrow(() -> new RessourceNotFoundException("Quiz", "idQuiz", idQuiz));
        quiz.setValidQuiz(isValid);
        Quiz updatedQuiz = quizRepository.save(quiz);
        return quizMapper.toResponseDTO(updatedQuiz);
    }

    @Override
    public void deleteQuiz(Long idQuiz) {
        if (!quizRepository.existsById(idQuiz)) {
            throw new RessourceNotFoundException("Quiz", "idQuiz", idQuiz);
        }
        quizRepository.deleteById(idQuiz);
    }
}
