package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Answer;
import com.model.Question;

public interface AnswerRepository extends JpaRepository<Answer, Long>{
	public int countByQuestion(Question question);
	public List<Answer> findByQuestionOrderByAnswerNumberAsc(Question question);
	public Optional<Answer> findTop1ByQuestionOrderByAnswerNumberDesc(Question question);
	public Optional<Answer> findByQuestionAndAnswerNumber(Question question,  int answerNumber);
}
