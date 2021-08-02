package com.services;

import java.util.List;
import java.util.Optional;

import com.model.Answer;
import com.model.Question;

public interface IAnswerServices {
	public void addAnswer(Answer answer);
	public Optional<Answer> findAnswerById(Long answerId);
	public int totalNumberOfAnswersInAQuestion(Question question);
	public void update(Answer answer,Long answerId);
	public void delete(Long answerId);
	public List<Answer> allAnswersForGivenQuestion(Long questionId);
	public Optional<Answer> findTop1ByQuestionOrderByQuestionNumberDesc(Long questionId);
	public Optional<Answer> findByQuestionAndAnswerNumber(Question question, int answerNumber);
}
