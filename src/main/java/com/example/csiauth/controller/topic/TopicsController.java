package com.example.csiauth.controller.topic;

import com.example.csiauth.services.topics.TopicServices;
import com.example.csiauth.shared.topic.TopicResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class TopicsController {
    @Autowired
    private TopicServices topicServices;

    @GetMapping
    public ResponseEntity<List<TopicResponse>> getAllByIduser(HttpServletRequest request){
        String userId = (String) request.getAttribute("userId");
        return new ResponseEntity<>(topicServices.getAllTopics(userId), HttpStatus.OK);
    }
}
