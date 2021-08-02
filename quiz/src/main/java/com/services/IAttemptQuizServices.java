package com.services;

import java.util.List;
import java.util.Optional;

import com.dto.EvaluatedResponse;
import com.dto.EvaluatedResponsesWrapper;
import com.dto.ResponseDTO;
import com.model.Quiz;
import com.model.Response;
import com.model.User;

public interface IAttemptQuizServices {
	public boolean attemptQuiz(List<ResponseDTO> answersMarked, long quizId,String userUID);
	public Optional<EvaluatedResponsesWrapper> evaluateResponse(long quizId,String userUID);
	public Response saveResponse(Quiz quiz, User user);
}
