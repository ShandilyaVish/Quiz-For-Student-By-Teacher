package com.dto;

import java.util.List;

import lombok.Builder;

@Builder
public class EvaluatedResponsesWrapper {
	private List<EvaluatedResponse> evaluatedResponses;
	private int totalCorrectAnswers;
	public List<EvaluatedResponse> getEvaluatedResponses() {
		return evaluatedResponses;
	}
	public void setEvaluatedResponses(List<EvaluatedResponse> evaluatedResponses) {
		this.evaluatedResponses = evaluatedResponses;
	}
	public int getTotalCorrectAnswers() {
		return totalCorrectAnswers;
	}
	public void setTotalCorrectAnswers(int totalCorrectAnswers) {
		this.totalCorrectAnswers = totalCorrectAnswers;
	}
	
}
