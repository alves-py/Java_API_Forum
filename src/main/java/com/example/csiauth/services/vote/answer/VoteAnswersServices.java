package com.example.csiauth.services.vote.answer;

import com.example.csiauth.model.answers.topics.AnswersTopic;
import com.example.csiauth.model.exception.ResourceNotFoundException;
import com.example.csiauth.model.vote.answers.VoteAnswers;
import com.example.csiauth.repository.answers.topics.AnswersTopicRepository;
import com.example.csiauth.repository.vote.answer.VoteAnswerRepository;
import com.example.csiauth.shared.answers.topics.AnswersTopicResponse;
import com.example.csiauth.shared.vote.answer.VoteAnswerResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VoteAnswersServices {

    @Autowired
    private VoteAnswerRepository voteAnswerRepository;

    @Autowired
    private AnswersTopicRepository answersTopicRepository;

    public void addVote(String userId, String answerId){
        List<AnswersTopic> answersTopic = answersTopicRepository.findByAnswer_id(answerId);
        if (answersTopic.isEmpty()) {
            throw new ResourceNotFoundException("Idea not found");
        }
        Optional<VoteAnswers> voteAnswers = voteAnswerRepository.findByAnswerIdAndUserId(answerId, userId);
        if (voteAnswers.isPresent()) {
            throw new ResourceNotFoundException("Not double vote supported yet");
        }

        VoteAnswers voteAnswer = new VoteAnswers(userId, answerId);
        voteAnswerRepository.save(voteAnswer);
    }

    public  void  deleteVote(String userId, String voteId){
        Optional<VoteAnswers> voteAnswers = voteAnswerRepository.findById(voteId);
        if (voteAnswers.isEmpty()) {
            throw new ResourceNotFoundException("Vote not found or user doesn't match");
        }
        if (!Objects.equals(voteAnswers.get().getUser_id(), userId)){
            throw new ResourceNotFoundException("Vote not found or user doesn't match");
        }
        voteAnswerRepository.deleteById(voteId);
    }

    public List<VoteAnswerResponse> getVoteAnswers(String answerId){
        List<AnswersTopic> answersTopic = answersTopicRepository.findByAnswer_id(answerId);
        if (answersTopic.isEmpty()) {
            throw new ResourceNotFoundException("Idea not found");
        }
        List<VoteAnswers> voteAnswers = voteAnswerRepository.findByAnswerId(answerId);
        return voteAnswers.stream()
                .map(voteAnswers1 -> new ModelMapper().map(voteAnswers1, VoteAnswerResponse.class))
                .collect(Collectors.toList());
    }
}