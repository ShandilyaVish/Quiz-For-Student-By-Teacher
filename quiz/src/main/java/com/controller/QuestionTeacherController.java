package com.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.Answer;
import com.model.Question;
import com.model.Quiz;
import com.services.IAnswerServices;
import com.services.IQuestionServices;
import com.services.IQuizServices;
import com.utils.Utils;

@RestController
@RequestMapping("/quiz/teacher/question")
public class QuestionTeacherController {
	
	@Autowired
	IQuizServices quizServices;

	@Autowired
	IQuestionServices questionServices;
	
	@Autowired
	IAnswerServices answerServices;
	
	
	@PostMapping("/add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question
			,@RequestParam Long quizId) {
		Optional<Quiz> quiz = quizServices.getQuizById(quizId);
		if(quiz.isEmpty()) {
			return new ResponseEntity<String>(Utils.QUIZ_NOT_FOUND,HttpStatus.BAD_REQUEST);
		}
		question.setQuiz(quiz.get());
		quiz.get().getQuestions().add(question);
		questionServices.saveQuestion(question);
		return new ResponseEntity<String>(Utils.SAVED, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateQuestion(@RequestParam Long questionId,@RequestBody Question question) {
		
		Optional<Question> questionAlreadyPresent = questionServices.findQuestionById(questionId);
		if(questionAlreadyPresent.isEmpty()) {
			return new ResponseEntity<String>(Utils.QUESTION_NOT_FOUND,HttpStatus.BAD_REQUEST);
		}
		questionServices.updateQuestionById(questionId, question);
		return new ResponseEntity<String>(Utils.SAVED, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteQuestion(@RequestParam Long questionId) {
		Optional<Question> question = questionServices.findQuestionById(questionId);
		
		if(question.isEmpty()) {
			return new ResponseEntity<String>(Utils.QUESTION_NOT_FOUND,HttpStatus.BAD_REQUEST);
		}
		questionServices.deleteQuestionById(questionId);
		return new ResponseEntity<String>(Utils.DELETED,HttpStatus.OK);
	}
	
	@PostMapping("/addCorrectAnswer")
	public ResponseEntity<String> addCorrectAnswer(@RequestParam Long answerId,@RequestParam Long questionId) {
		Optional<Question> question = questionServices.findQuestionById(questionId);
		Optional<Answer> answer = answerServices.findAnswerById(answerId);
		if(question.isEmpty()) {
			return new ResponseEntity<String>(Utils.QUESTION_NOT_FOUND,HttpStatus.BAD_REQUEST);
		}
		questionServices.setRightAnswer(question.get(), answer.get());
		return new ResponseEntity<String>(Utils.SAVED, HttpStatus.OK);
	}
}
