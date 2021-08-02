package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.McqDTO;
import com.model.Quiz;
import com.services.IAnswerServices;
import com.services.IQuestionServices;
import com.services.IQuizServices;
import com.utils.Utils;

@RestController
@RequestMapping("/quiz")
public class QuizTeacherController {
	
	@Autowired
	IQuizServices quizServices;
	
	@Autowired
	IAnswerServices answerServices;
	
	@Autowired
	IQuestionServices questionServices;
	
	@PostMapping("/teacher/addQuiz")
	public ResponseEntity<String> addQuiz(@RequestBody Quiz quiz) {
		boolean canNewQuizBeCreated = quizServices.addNewQuiz(quiz);
		if(!canNewQuizBeCreated) {
			return new ResponseEntity<String>(Utils.ERROR,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Utils.SAVED,HttpStatus.CREATED);
	}
	@GetMapping("/fetch")
	public ResponseEntity<List<McqDTO>> findQuizById(@RequestParam Long quizId) {
		Optional<Quiz> quiz = quizServices.getQuizById(quizId);
		if(quiz.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<McqDTO> allTheQuestion = quizServices.fetchAllQuestions(quizId);
		return new  ResponseEntity<>(allTheQuestion,HttpStatus.FOUND);
	}

}
