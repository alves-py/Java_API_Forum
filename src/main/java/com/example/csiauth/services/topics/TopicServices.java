package com.example.csiauth.services.topics;


import com.example.csiauth.model.exception.ResourceNotFoundException;
import com.example.csiauth.model.topics.Topic;
import com.example.csiauth.repository.topics.TopicRepository;
import com.example.csiauth.shared.topic.TopicDTO;
import com.example.csiauth.shared.topic.TopicResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicServices {

    @Autowired
    private TopicRepository topicRepository;

    public List<TopicResponse> getAllTopicsByUser(String idUser) {
        List<Topic> topics = topicRepository.findByIdUser(idUser);
        return  topics.stream()
                .map(topic -> new ModelMapper().map(topic, TopicResponse.class))
                .collect(Collectors.toList());
    }

    public List<TopicResponse> getAllTopics() {
        List<Topic> topics = topicRepository.findAll();
        return topics.stream()
                .map(topic -> new ModelMapper().map(topic, TopicResponse.class))
                .collect(Collectors.toList());
    }
    public void addTopic(String user_id, TopicDTO topicDTO) {
            Topic topic = new Topic(topicDTO.title(), topicDTO.description());
            topic.setId_user(user_id);
            topicRepository.save(topic);
    }

    public void updateTopic(String user_id, String idTopic,  TopicDTO topicDTO) {
        Optional<Topic> topic = topicRepository.findById(idTopic);
        if (topic.isEmpty()) {
            throw new ResourceNotFoundException("Topic not found or user doesn't match");
        }

        if (!Objects.equals(topic.get().getId_user(), user_id)) {
            throw new ResourceNotFoundException("Topic not found or user doesn't match");
        }
        topic.get().setTitle(topicDTO.title());
        topic.get().setDescription(topicDTO.description());

        topicRepository.save(topic.get());
    }
    public void deleteTopic(String user_id, String idTopic) {
        Optional<Topic> topic = topicRepository.findById(idTopic);
        if (topic.isEmpty()) {
            throw new ResourceNotFoundException("Topic not found or user doesn't match");
        }
        if (!Objects.equals(topic.get().getId_user(), user_id)) {
            throw new ResourceNotFoundException("Topic not found or user doesn't match");
        }
        topicRepository.deleteById(idTopic);
    }
}
