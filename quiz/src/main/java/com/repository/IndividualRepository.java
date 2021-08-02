package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.IndividualResponse;

public interface IndividualRepository extends JpaRepository<IndividualResponse, Long>{

}
