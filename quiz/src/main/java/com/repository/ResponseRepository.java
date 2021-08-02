package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Quiz;
import com.model.Response;
import com.model.User;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long>{
	public Optional<Response> findByUser(User user);
	public Optional<Response> findByUserAndQuiz(User user,Quiz quiz);
}
