package com.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.dto.AnswerDTO;
import com.dto.McqDTO;
import com.model.Answer;
import com.model.Question;
import com.model.Quiz;
import com.repository.QuizRepository;

@Service
public class QuizServices implements IQuizServices {
	
	@Autowired
	private QuizRepository quizRepository;
	
	@Autowired
	private IQuestionServices questionServices;
	
	@Autowired
	private IAnswerServices answerServices;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public boolean addNewQuiz(Quiz quiz) {
		
		if(quizRepository.findByQuizName(quiz.getQuizName()).
				isPresent()) {
			return false;
		}
		
		quizRepository.save(quiz);
		return true;
	}


	@Override
	public Optional<Quiz> getQuizById(long quizId) {
		Optional<Quiz> quiz = quizRepository.findById(quizId);
		return quiz;
	}


	@Override
	public List<McqDTO> fetchAllQuestions(long quizId) {
		List<Question> allQuestionsOfTheQuiz = questionServices.findAllQuestionsByQuizId(quizId);
		List<McqDTO> mcqs = new ArrayList<McqDTO>();
		for(Question question : allQuestionsOfTheQuiz) {
			int questionNumber = question.getQuestionNumber();
			String questionDescription = question.getDescription();
			List<Answer> answers = answerServices.
					allAnswersForGivenQuestion(question.getId());
			List<AnswerDTO> possibleAnswersToBeShownToStudent = new ArrayList<AnswerDTO>();
			for(Answer answer : answers) {
				AnswerDTO possibleAnswerToBeShownToStudent = modelMapper.
						map(answer, AnswerDTO.class);
				possibleAnswersToBeShownToStudent.
				add(possibleAnswerToBeShownToStudent);
			}
			McqDTO mcq = McqDTO.builder().
					questionNumber(questionNumber).
						answers(possibleAnswersToBeShownToStudent).
							question(questionDescription).build();
			mcqs.add(mcq);
		}
		return mcqs;
	}

	

}
