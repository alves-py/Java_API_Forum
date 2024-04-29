package com.example.csiauth.shared.answers.topics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnswersTopicResponse {
    private String id;
    private String topic_id;
    private String ideas;
    private String id_user;
}
