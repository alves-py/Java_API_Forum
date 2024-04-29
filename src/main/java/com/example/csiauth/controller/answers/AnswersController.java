package com.example.csiauth.controller.answers;

import com.example.csiauth.services.answers.topics.AnswersTopicServices;
import com.example.csiauth.shared.answers.topics.AnswersTopicDTO;
import com.example.csiauth.shared.answers.topics.AnswersTopicResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics/answers")
public class AnswersController {
    @Autowired
    AnswersTopicServices answersTopicServices;

    @PostMapping("/{topic_id}")
    public ResponseEntity<AnswersTopicResponse> addAnswer(
            @RequestBody AnswersTopicDTO answersTopicDTO,
            HttpServletRequest request,
            @PathVariable String topic_id) {
        String userId = (String) request.getAttribute("userId");
        answersTopicServices.save(answersTopicDTO, topic_id, userId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{topic_id}")
    public ResponseEntity<List<AnswersTopicResponse>> getAnswer(@PathVariable String topic_id) {
        List<AnswersTopicResponse> answersTopicResponses = answersTopicServices.getAllByTopic(topic_id);
        return new  ResponseEntity<>(answersTopicResponses, HttpStatus.OK);
    }

    @PutMapping("/{topic_id}/{answer_id}")
    public ResponseEntity<AnswersTopicResponse> updateAnswer(HttpServletRequest request,
                                                             @PathVariable String topic_id,
                                                             @PathVariable String answer_id,
                                                             @RequestBody AnswersTopicDTO answersTopicDTO) {
        String userId = (String) request.getAttribute("userId");
        answersTopicServices.update(userId, topic_id, answer_id, answersTopicDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{topic_id}/{answer_id}")
    public ResponseEntity<AnswersTopicResponse> deleteAnswer(HttpServletRequest request,
                                         @PathVariable String topic_id,
                                         @PathVariable String answer_id){
        String userId = (String) request.getAttribute("userId");
        answersTopicServices.delete(answer_id, topic_id, userId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
