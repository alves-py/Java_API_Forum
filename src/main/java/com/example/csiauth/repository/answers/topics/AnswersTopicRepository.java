package com.example.csiauth.repository.answers.topics;

import com.example.csiauth.model.answers.topics.AnswersTopic;
import com.example.csiauth.model.topics.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AnswersTopicRepository extends JpaRepository<AnswersTopic, String> {

   @Query("FROM AnswersTopic AS t1 WHERE t1.topic_id = :topicId")
    List<AnswersTopic> findByTopic_id(@Param("topicId") String topicId);

   @Query("FROM AnswersTopic AS t1 WHERE t1.id = :answerId")
    List<AnswersTopic> findByAnswer_id(@Param("answerId") String answerId);

}
