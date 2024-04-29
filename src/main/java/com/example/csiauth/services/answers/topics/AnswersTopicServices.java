package com.example.csiauth.services.answers.topics;

import com.example.csiauth.model.answers.topics.AnswersTopic;
import com.example.csiauth.model.exception.ResourceNotFoundException;
import com.example.csiauth.model.topics.Topic;
import com.example.csiauth.repository.answers.topics.AnswersTopicRepository;
import com.example.csiauth.repository.topics.TopicRepository;
import com.example.csiauth.shared.answers.topics.AnswersTopicDTO;
import com.example.csiauth.shared.answers.topics.AnswersTopicResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnswersTopicServices {

    @Autowired
    private AnswersTopicRepository answersTopicRepository;

    @Autowired
    private TopicRepository topicRepository;

    public List<AnswersTopicResponse> getAllByTopic(String topicId) {
        Optional<Topic> topic = topicRepository.findById(topicId);
        if (topic.isEmpty()) {
            throw new ResourceNotFoundException("Topic not found");
        }
        List<AnswersTopic> answersTopics = answersTopicRepository.findByTopic_id(topicId);
        return answersTopics.stream()
                .map(answersTopic -> new ModelMapper().map(answersTopic, AnswersTopicResponse.class))
                .collect(Collectors.toList());
    }

    public void save(AnswersTopicDTO answersTopic, String topicId, String userId) {
        Optional<Topic> topic = topicRepository.findById(topicId);
        if (topic.isEmpty()) {
            throw new ResourceNotFoundException("Topic not found");
        }
        AnswersTopic answersTopicEntity = new AnswersTopic(answersTopic.answer(), topicId, userId);
        answersTopicRepository.save(answersTopicEntity);
    }

    public void delete(String answersId, String topicId, String userId) {
        Optional<Topic> topic = topicRepository.findById(topicId);
        if (topic.isEmpty()) {
            throw new ResourceNotFoundException("Topic not found");
        }
        Optional<AnswersTopic> answersTopic = answersTopicRepository.findById(answersId);
        if (answersTopic.isEmpty()) {
            throw new ResourceNotFoundException("Answer not found or user doesn't match");
        }
        if(!Objects.equals(answersTopic.get().getId_user(), userId)){
            throw new ResourceNotFoundException("Answer not found or user doesn't match");
        }
        answersTopicRepository.deleteById(answersId);
    }

    public void update(String userId, String topicId, String answerId, AnswersTopicDTO answersTopicDTO) {
        Optional<Topic> topic = topicRepository.findById(topicId);
        if (topic.isEmpty()) {
            throw new ResourceNotFoundException("Topic not found");
        }
        Optional<AnswersTopic> answersTopic = answersTopicRepository.findById(answerId);
        if (answersTopic.isEmpty()) {
            throw new ResourceNotFoundException("Answer not found or user doesn't match");
        }
        if(!Objects.equals(answersTopic.get().getId_user(), userId)){
            throw new ResourceNotFoundException("Answer not found or user doesn't match");
        }
        answersTopic.get().setIdeas(answersTopicDTO.answer());
        answersTopicRepository.save(answersTopic.get());
    }
}
