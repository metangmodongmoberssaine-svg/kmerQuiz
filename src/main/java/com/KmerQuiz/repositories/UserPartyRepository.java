package com.KmerQuiz.repositories;

import com.KmerQuiz.entities.IdUserParty;
import com.KmerQuiz.entities.UserParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPartyRepository extends JpaRepository<UserParty, IdUserParty> {
    List<UserParty> findByIdUserParty_IdUser(String idUser);
    List<UserParty> findByIdUserParty_CodeParty(Long codeParty);
    List<UserParty> findByIdUserParty_CodePartyOrderByScorePlayerDesc(Long codeParty);
    boolean existsByIdUserParty_IdUserAndIdUserParty_CodeParty(String idUser, Long codeParty);
}
