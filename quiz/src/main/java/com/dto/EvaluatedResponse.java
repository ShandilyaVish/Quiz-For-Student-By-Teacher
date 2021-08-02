package com.dto;

import lombok.Builder;

@Builder
public class EvaluatedResponse {
	private int questionNumber;
	private String markedAnswers;
	private String correctAnswers;
	private boolean isAnswerCorrect;
	public EvaluatedResponse(int questionNumber, String markedAnswers, String correctAnswers,
			boolean isAnswerCorrect) {
		super();
		this.questionNumber = questionNumber;
		this.markedAnswers = markedAnswers;
		this.correctAnswers = correctAnswers;
		this.isAnswerCorrect = isAnswerCorrect;
	}
	public int getQuestionNumber() {
		return questionNumber;
	}
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	public String getMarkedAnswers() {
		return markedAnswers;
	}
	public void setMarkedAnswers(String markedAnswers) {
		this.markedAnswers = markedAnswers;
	}
	public String getCorrectAnswers() {
		return correctAnswers;
	}
	public void setCorrectAnswers(String correctAnswers) {
		this.correctAnswers = correctAnswers;
	}
	public boolean isAnswerCorrect() {
		return isAnswerCorrect;
	}
	public void setAnswerCorrect(boolean isAnswerCorrect) {
		this.isAnswerCorrect = isAnswerCorrect;
	}
	
}
