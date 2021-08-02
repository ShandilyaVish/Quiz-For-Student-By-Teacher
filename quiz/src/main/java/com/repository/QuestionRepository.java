package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Question;
import com.model.Quiz;

public interface QuestionRepository extends JpaRepository<Question, Long>{
	public int countByQuiz(Quiz quiz);
	public List<Question> findByQuizOrderByQuestionNumberAsc(Quiz quiz);
	public Optional<Question> findTop1ByQuizOrderByQuestionNumberDesc(Quiz quiz);
	public Optional<Question> findByQuizAndQuestionNumber(Quiz quiz,int questionNumber);
}
