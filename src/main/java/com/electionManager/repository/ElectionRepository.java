package com.electionManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.electionManager.model.Election;

/**
 * The interface User repository.
 *
 
 */
@Repository
public interface ElectionRepository extends JpaRepository<Election, Long> {}
