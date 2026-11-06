package com.KmerQuiz.repositories;

import com.KmerQuiz.entities.Party;
import com.KmerQuiz.utils.PlayStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {
    List<Party> findByPartyStatus(PlayStatus partyStatus);
    List<Party> findByCreatedDateBetween(Date startDate, Date endDate);
    List<Party> findByMaxPlayersGreaterThanEqual(int minPlayers);
}
