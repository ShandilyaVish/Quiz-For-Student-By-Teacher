package com.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Response")
public class Response {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne
	private Quiz quiz;
	
	@OneToOne 
	private User user;
	
	@OneToMany(mappedBy = "response", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	List<IndividualResponse> individualResponses;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<IndividualResponse> getIndividualResponses() {
		return individualResponses;
	}

	public void setIndividualResponses(List<IndividualResponse> individualResponses) {
		this.individualResponses = individualResponses;
	}
	
}
