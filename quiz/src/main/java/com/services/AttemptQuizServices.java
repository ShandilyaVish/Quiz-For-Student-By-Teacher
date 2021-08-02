package com.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.EvaluatedResponse;
import com.dto.EvaluatedResponsesWrapper;
import com.dto.ResponseDTO;
import com.model.Answer;
import com.model.IndividualResponse;
import com.model.Question;
import com.model.Quiz;
import com.model.Response;
import com.model.User;
import com.repository.IndividualRepository;
import com.repository.QuizRepository;
import com.repository.ResponseRepository;
import com.repository.UserRepository;

@Service
public class AttemptQuizServices implements IAttemptQuizServices{
	@Autowired
	private ResponseRepository responseRepository;
	@Autowired
	private IndividualRepository individualRepository;
	@Autowired
	private IQuizServices quizServices;
	@Autowired
	private IUserServices userServices;
	@Autowired
	private IQuestionServices questionServices;
	@Autowired
	private IAnswerServices answerServices;

	@Override
	public boolean attemptQuiz(List<ResponseDTO> responsesDTO, long quizId, String userUID) {
		Optional<Quiz> quiz = quizServices.getQuizById(quizId);
		User user = userServices.findUserById(userUID);
		if(quiz.isEmpty()) {
			return false;
		}
		Response response = saveResponse(quiz.get(), user);
		List<IndividualResponse> individualResponses = new ArrayList<IndividualResponse>();
		for(ResponseDTO responseDTO : responsesDTO) {
			IndividualResponse individualResponse = new IndividualResponse();
			Optional<Question> question = questionServices.
					findQuestionByQuizAndQuestionNumber(quiz.get(),responseDTO.getQuestionNumber());
			Optional<Answer> answer = answerServices.
					findByQuestionAndAnswerNumber(question.get(), responseDTO.getAnswerNumber());
			if(answer.isEmpty() || question.isEmpty()) {
				continue;
			}
			individualResponse.setAnswer(answer.get());
			individualResponse.setQuestion(question.get());
			individualResponse.setResponse(response);
			individualResponses.add(individualRepository.save(individualResponse));
		}
		response.setIndividualResponses(individualResponses);
		return true;
	}

	@Override
	public Optional<EvaluatedResponsesWrapper> evaluateResponse(long quizId, String userUID) {
		User user = userServices.findUserById(userUID);
		Optional<Quiz> quiz = quizServices.getQuizById(quizId);
		if(quiz.isEmpty()) {
			return Optional.empty();
		}
		
		Optional<Response> responded = responseRepository.findByUserAndQuiz(user, quiz.get());
		List<EvaluatedResponse> evaluatedResponses = new ArrayList<EvaluatedResponse>();
		List<IndividualResponse> individualResponses = responded.get().getIndividualResponses();
		int totalCorrectAnswers = 0;
		
		
		for(int i = 0;i < individualResponses.size();i++) {
			Question question = individualResponses.get(i).getQuestion();
			String correctAnswer = "";
			boolean isCorrect = false;
			if(question.getCorrectAnswer() != null) {
				correctAnswer += question.getCorrectAnswer().getAnswerContent();
				if(individualResponses.get(i).
						getAnswer().getAnswerContent().
							equals(correctAnswer)) {
					isCorrect = true;
				}
			}
			
			if(isCorrect) {
				totalCorrectAnswers++;
			}
			
			EvaluatedResponse evaluatedResponse = EvaluatedResponse.builder().
					questionNumber(question.getQuestionNumber()).
					correctAnswers(correctAnswer).
					markedAnswers(individualResponses.get(i).getAnswer().getAnswerContent()).
					isAnswerCorrect(isCorrect).build();
			evaluatedResponses.add(evaluatedResponse);
		}
		
		EvaluatedResponsesWrapper evaluatedResponseWrapper = EvaluatedResponsesWrapper.builder().
			evaluatedResponses(evaluatedResponses).
			totalCorrectAnswers(totalCorrectAnswers).build();
		return Optional.of(evaluatedResponseWrapper);
	}

	@Override
	public Response saveResponse(Quiz quiz, User user) {
		Optional<Response> responsePresent = responseRepository.findByUserAndQuiz(user, quiz);
		if(!responsePresent.isEmpty()) {
			return responsePresent.get();
			
		}
		Response response = new Response();
		response.setQuiz(quiz);
		response.setUser(user);
		return responseRepository.save(response);
	}

}
