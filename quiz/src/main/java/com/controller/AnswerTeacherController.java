package com.controller;

import java.util.List;
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

import com.dto.AnswerDTO;
import com.model.Answer;
import com.model.Question;
import com.services.IAnswerServices;
import com.services.IQuestionServices;
import com.utils.Utils;

@RestController
@RequestMapping("/quiz/teacher/answer")
public class AnswerTeacherController {
	
	@Autowired
	private IAnswerServices answerServices;
	
	@Autowired
	private IQuestionServices questionServices;
	
	@PostMapping("/add")
	public ResponseEntity<String> addAnswer(@RequestBody List<Answer> answers, @RequestParam Long questionId) {
		Optional<Question> question = questionServices.findQuestionById(questionId);
		if(question.isEmpty()) {
			return new ResponseEntity<String>(Utils.QUESTION_NOT_FOUND,HttpStatus.BAD_REQUEST);
		}
		int answerNumber = answerServices.totalNumberOfAnswersInAQuestion(question.get());
		for(Answer answer : answers) {
			answer.setAnswerNumber(++answerNumber);
			answer.setQuestion(question.get());
			answerServices.addAnswer(answer);
		}
		return new ResponseEntity<String>(Utils.SAVED, HttpStatus.OK);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<String> updateAnswer(@RequestBody Answer answer,@RequestParam Long answerId) {
		Optional<Answer> currentAnswer = answerServices.findAnswerById(answerId);
		if(currentAnswer.isEmpty()) {
			return new ResponseEntity<String>(Utils.ERROR,HttpStatus.BAD_REQUEST);
		}
		answerServices.update(answer,answerId);
		return new ResponseEntity<String>(Utils.SAVED, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteAnswer(@RequestParam Long answerId,@RequestParam Long questionId) {
		Optional<Answer> currentAnswer = answerServices.findAnswerById(answerId);
		Optional<Question> question = questionServices.findQuestionById(questionId);
		if(currentAnswer.isEmpty()) {
			
			return new ResponseEntity<String>(Utils.ERROR,HttpStatus.BAD_REQUEST);
		}
		if(question.get().getCorrectAnswer() != null && 
				question.get().getCorrectAnswer().equals(currentAnswer.get())) {
			return new ResponseEntity<String>(Utils.PROHIBITED_TO_DELETE_CORRECT_ANSWER,HttpStatus.BAD_REQUEST);
		}
		answerServices.delete(answerId);
		return new ResponseEntity<String>(Utils.DELETED,HttpStatus.OK);
	}
	
}
