package com.example.csiauth.model.vote.answers;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "vote")
@AllArgsConstructor
@NoArgsConstructor
public class VoteAnswers {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String user_id;

    @Column(nullable = false)
    private String answer_id;

    public VoteAnswers(String userId, String answerId) {
        this.user_id = userId;
        this.answer_id = answerId;
    }
}
