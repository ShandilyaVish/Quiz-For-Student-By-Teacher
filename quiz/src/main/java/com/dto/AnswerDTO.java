package com.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AnswerDTO {
	private String answerContent;
	@JsonIgnore
	private Integer answerNumber;
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	public Integer getAnswerNumber() {
		return answerNumber;
	}
	public void setAnswerNumber(Integer answerNumber) {
		this.answerNumber = answerNumber;
	}
	
}
