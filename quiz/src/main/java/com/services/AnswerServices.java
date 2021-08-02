package com.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Answer;
import com.model.Question;
import com.repository.AnswerRepository;

@Service
public class AnswerServices implements IAnswerServices{
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private IQuestionServices questionServices;
	

	@Override
	public void addAnswer(Answer answer) {
		Optional<Answer> lastAnswer = findTop1ByQuestionOrderByQuestionNumberDesc(answer.getQuestion().getId());
		int answerNumber = 0;
		if(lastAnswer.isPresent()) {
			answerNumber = lastAnswer.get().getAnswerNumber();
		}
		answer.setAnswerNumber(answerNumber+1);
		answerRepository.save(answer);
	}

	@Override
	public Optional<Answer> findAnswerById(Long answerId) {
		return answerRepository.findById(answerId);
	}

	@Override
	public int totalNumberOfAnswersInAQuestion(Question question) {
		
		return answerRepository.countByQuestion(question);
	}

	@Override
	public void update(Answer newAnswer,Long answerId) {
		Answer answer = findAnswerById(answerId).get();
		answer.setAnswerContent(newAnswer.getAnswerContent());
		addAnswer(answer);
	}

	@Override
	public void delete(Long answerId) {
		answerRepository.deleteById(answerId);
		
	}

	@Override
	public List<Answer> allAnswersForGivenQuestion(Long questionId) {
		Optional<Question> question = questionServices.findQuestionById(questionId);
		if(question.isEmpty()) {
			return new ArrayList<Answer>();
		}
		return answerRepository.findByQuestionOrderByAnswerNumberAsc(question.get());
	}

	@Override
	public Optional<Answer> findTop1ByQuestionOrderByQuestionNumberDesc(Long questionId) {
		Optional<Question> question = questionServices.findQuestionById(questionId);
		
		return answerRepository.findTop1ByQuestionOrderByAnswerNumberDesc(question.get());
	}

	@Override
	public Optional<Answer> findByQuestionAndAnswerNumber(Question question, int answerNumber) {
		return answerRepository.findByQuestionAndAnswerNumber(question, answerNumber);
	}

}
