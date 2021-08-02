package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long>{
	public Optional<Quiz> findByQuizName(String quizName);
}
