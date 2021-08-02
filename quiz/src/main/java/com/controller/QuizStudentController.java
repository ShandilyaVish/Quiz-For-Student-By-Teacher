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

import com.dto.EvaluatedResponse;
import com.dto.EvaluatedResponsesWrapper;
import com.dto.ResponseDTO;
import com.services.IAttemptQuizServices;
import com.utils.Utils;

@RestController
@RequestMapping("/quiz/student")
public class QuizStudentController {
	@Autowired
	IAttemptQuizServices attemptQuizServices;
	
	@PostMapping("/attemptQuiz")
	public ResponseEntity<String> attemptQuiz(@RequestBody List<ResponseDTO> answersMarked,
			@RequestParam Long quizId,@RequestParam String userUID) {
		boolean canStudentAttempt = attemptQuizServices.attemptQuiz(answersMarked, quizId, userUID);
		if(!canStudentAttempt) {
			return new ResponseEntity<String>(Utils.ALREADY_PRESENT,HttpStatus.ALREADY_REPORTED);
		}
		return new ResponseEntity<String>(Utils.SAVED,HttpStatus.ACCEPTED);
	}
	@GetMapping("/checkEvaluation")
	public ResponseEntity<EvaluatedResponsesWrapper> checkEvaluation(@RequestParam String userUID,
			@RequestParam Long quizId) {
		Optional<EvaluatedResponsesWrapper> evaluatedResponseWrapper = attemptQuizServices.
				evaluateResponse(quizId, userUID);
		
		if(evaluatedResponseWrapper.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(evaluatedResponseWrapper.get(), HttpStatus.FOUND);
	}
}
