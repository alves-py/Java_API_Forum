package com.example.csiauth.shared.topic;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicResponse {

    private String id;
    private String title;
    private String description;
    private String id_user;
}
