package com.example.csiauth.model.topics;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "topic")
@AllArgsConstructor
@NoArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(name = "id_user")
    private String id_user;

    public Topic(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
