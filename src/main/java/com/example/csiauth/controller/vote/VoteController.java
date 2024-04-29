package com.example.csiauth.controller.vote;

import com.example.csiauth.services.vote.answer.VoteAnswersServices;
import com.example.csiauth.shared.vote.answer.VoteAnswerResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/answers/vote")
public class VoteController {
    @Autowired
    VoteAnswersServices voteAnswersServices;

    @PostMapping("/{answersId}")
    public ResponseEntity voteAnswer(@PathVariable String answersId, HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");

        voteAnswersServices.addVote(userId, answersId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{answersId}")
    public ResponseEntity<List<VoteAnswerResponse>> getVoteAnswers(@PathVariable String answersId) {
        List<VoteAnswerResponse> responses = voteAnswersServices.getVoteAnswers(answersId);
        return new  ResponseEntity<>(responses, HttpStatus.OK);
    }

    @DeleteMapping("/{voteId}")
    public ResponseEntity deleteVoteAnswer(@PathVariable String voteId, HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        voteAnswersServices.deleteVote(userId, voteId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
