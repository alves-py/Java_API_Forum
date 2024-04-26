package com.example.csiauth.services.topics;


import com.example.csiauth.model.topics.Topic;
import com.example.csiauth.repository.topics.TopicRepository;
import com.example.csiauth.shared.topic.TopicResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicServices {

    @Autowired
    private TopicRepository topicRepository;

    public List<TopicResponse> getAllTopics(String idUser) {
        List<Topic> topics = topicRepository.findByIdUser(idUser);
        return  topics.stream()
                .map(topic -> new ModelMapper().map(topic, TopicResponse.class))
                .collect(Collectors.toList());
    }
}
