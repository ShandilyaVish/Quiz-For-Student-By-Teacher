package com.services;


import java.util.List;
import java.util.Optional;

import com.dto.McqDTO;
import com.model.Quiz;

public interface IQuizServices {

	public boolean addNewQuiz(Quiz quiz);
	public Optional<Quiz> getQuizById(long quizId);
	public List<McqDTO> fetchAllQuestions(long quizId);
}
