package com.example.csiauth.model.answers.topics;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "answers")
@AllArgsConstructor
@NoArgsConstructor
public class AnswersTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String topic_id;

    @Column(nullable = false)
    private String ideas;

    @Column(nullable = false)
    private String id_user;


    public AnswersTopic(String answer, String topicId,  String userId) {
        this.ideas = answer;
        this.topic_id = topicId;
        this.id_user = userId;
    }
}
