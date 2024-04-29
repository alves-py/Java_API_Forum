package com.example.csiauth.repository.vote.answer;

import com.example.csiauth.model.vote.answers.VoteAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteAnswerRepository extends JpaRepository<VoteAnswers, String> {
    @Query("FROM VoteAnswers AS v1 WHERE v1.answer_id = :answerId AND v1.user_id = :userId")
    Optional<VoteAnswers> findByAnswerIdAndUserId(@Param("answerId") String answerId, @Param("userId") String userId);

    @Query("FROM VoteAnswers AS v1 WHERE v1.answer_id = :answerId")
    List<VoteAnswers> findByAnswerId(@Param("answerId") String answerId);

}
