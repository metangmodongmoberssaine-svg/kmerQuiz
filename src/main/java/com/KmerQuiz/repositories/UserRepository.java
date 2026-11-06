package com.KmerQuiz.repositories;

import com.KmerQuiz.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByPseudoUser(String pseudoUser);
    Optional<User> findByEmailUser(String emailUser);
    Optional<User> findByPhoneUser(String phoneUser);
    Optional<User> findByEmailUserOrPhoneUser(String emailUser, String phoneUser);
    List<User> findAllByPseudoUserContainingIgnoreCase(String token);

    boolean existsByEmailUser(String emailUser);
    boolean existsByPseudoUser(String pseudoUser);
    boolean existsByPhoneUser(String phoneUser);

    @Query(value = "SELECT * FROM users WHERE avatar_user IS NULL", nativeQuery = true)
    List<User> getAllByWithNoAvatar();

    @Modifying
    @Query(value = "DELETE FROM User u WHERE u.pseudoUser = :pseudo AND u.idUser = :id")
    void deleteByPseudoAndId(@Param("pseudo") String pseudoUser, @Param("id") String id);
}
