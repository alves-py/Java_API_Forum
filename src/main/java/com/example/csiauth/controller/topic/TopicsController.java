package com.example.csiauth.controller.topic;

import com.example.csiauth.services.topics.TopicServices;
import com.example.csiauth.shared.topic.TopicDTO;
import com.example.csiauth.shared.topic.TopicResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class TopicsController {
    @Autowired
    private TopicServices topicServices;

    @GetMapping("/user")
    public ResponseEntity<List<TopicResponse>> getAllByIduser(HttpServletRequest request){
        String userId = (String) request.getAttribute("userId");
        return new ResponseEntity<>(topicServices.getAllTopicsByUser(userId), HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<TopicResponse>> getAllTopic(){
        return new ResponseEntity<>(topicServices.getAllTopics(), HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<TopicResponse> createTopic(HttpServletRequest request, @RequestBody @Valid TopicDTO topicDTO){
        String userId = (String) request.getAttribute("userId");
        topicServices.addTopic(userId, topicDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicResponse> updateTopic(HttpServletRequest request, @RequestBody @Valid TopicDTO topicDTO, @PathVariable String id){
        String userId = (String) request.getAttribute("userId");
        topicServices.updateTopic(userId, id, topicDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TopicResponse> deleteTopic(HttpServletRequest request, @PathVariable String id){
        String userId = (String) request.getAttribute("userId");
        topicServices.deleteTopic(userId, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
