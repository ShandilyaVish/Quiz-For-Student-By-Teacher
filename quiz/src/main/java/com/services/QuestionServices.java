package com.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Answer;
import com.model.Question;
import com.model.Quiz;
import com.repository.QuestionRepository;

@Service
public class QuestionServices implements IQuestionServices{
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private IAnswerServices answerServices;
	
	@Autowired
	private IQuizServices quizServices;

	@Override
	public void saveQuestion(Question question) {
		Optional<Question> lastQuestion = findTop1QuestionByQuestionNumber(question.getQuiz().getQuizId());
		int questionNumber = 0;
		if(!lastQuestion.isEmpty()) {
			questionNumber = lastQuestion.get().getQuestionNumber();
		}
		question.setQuestionNumber(questionNumber+1);
		questionRepository.save(question);
	}

	@Override
	public Optional<Question> findQuestionById(Long questionId) {
		Optional<Question> question = questionRepository.findById(questionId);
		
		return question;
	}

	@Override
	public void updateQuestionById(Long questionId, Question question) {
		Optional<Question> questionToBeUpdated = findQuestionById(questionId);
		questionToBeUpdated.get().setDescription(question.getDescription());
		
		//questionToBeUpdated.get().setQuestionNumber(question.getQuestionNumber());
		
		questionRepository.save(questionToBeUpdated.get());
	}

	@Override
	public void deleteQuestionById(Long questionId) {
		questionRepository.deleteById(questionId);
	}

	@Override
	public void setRightAnswer(Question question, Answer answer) {
		question.setCorrectAnswer(answer);
		saveQuestion(question);
	}

	@Override
	public void saveAnswersToQuestion(Question question, Answer answer) {
		int answerNumber = answerServices.totalNumberOfAnswersInAQuestion(question);
		answer.setAnswerNumber(answerNumber+1);
		answer.setQuestion(question);
		answerServices.addAnswer(answer);
		question.getAnswers().add(answer);
		saveQuestion(question);
	}

	@Override
	public List<Question> findAllQuestionsByQuizId(Long quizId) {
		Optional<Quiz> quiz = quizServices.getQuizById(quizId);
		return questionRepository.findByQuizOrderByQuestionNumberAsc(quiz.get());
	}

	@Override
	public Optional<Question> findTop1QuestionByQuestionNumber(Long quizId) {
		Optional<Quiz> quiz = quizServices.getQuizById(quizId);
		
		return questionRepository.findTop1ByQuizOrderByQuestionNumberDesc(quiz.get());
	}

	@Override
	public Optional<Question> findQuestionByQuizAndQuestionNumber(Quiz quiz, int questionNumber) {
		return questionRepository.findByQuizAndQuestionNumber(quiz, questionNumber);
	}
	
	

}
