package com.KmerQuiz.repositories;

import com.KmerQuiz.entities.IdUserGain;
import com.KmerQuiz.entities.UserGain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGainRepository extends JpaRepository<UserGain, IdUserGain> {
    List<UserGain> findByIdUserGain_IdUser(String idUser);
    List<UserGain> findByIdUserGain_IdGain(Long idGain);
    boolean existsByIdUserGain_IdUserAndIdUserGain_IdGain(String idUser, Long idGain);
}
