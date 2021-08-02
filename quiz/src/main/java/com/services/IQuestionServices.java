package com.services;

import java.util.List;
import java.util.Optional;

import com.model.Answer;
import com.model.Question;
import com.model.Quiz;

public interface IQuestionServices {
	
	public void saveQuestion(Question question);
	public Optional<Question> findQuestionById(Long questionId);
	public void updateQuestionById(Long questionId, Question question);
	public void deleteQuestionById(Long questionId);
	public void setRightAnswer(Question question, Answer answer);
	public void saveAnswersToQuestion(Question question,Answer answer);
	public List<Question> findAllQuestionsByQuizId(Long quizId);
	public Optional<Question> findTop1QuestionByQuestionNumber(Long quizId);
	public Optional<Question> findQuestionByQuizAndQuestionNumber(Quiz quiz,int questionNumber);
}
