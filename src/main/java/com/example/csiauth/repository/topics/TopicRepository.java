package com.example.csiauth.repository.topics;

import com.example.csiauth.model.topics.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, String> {

    @Query("FROM Topic AS t1 WHERE t1.id_user = :userId")
    List<Topic> findByIdUser(@Param("userId") String userId);
}
