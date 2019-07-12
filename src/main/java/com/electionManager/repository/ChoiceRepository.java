package com.electionManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.electionManager.model.Choice;

/**
 * The interface User repository.
 *
 
 */
@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {}
