package com.example.csiauth.shared.vote.answer;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoteAnswerResponse {
    private String id;
    private String user_id;
    private String answer_id;
}
