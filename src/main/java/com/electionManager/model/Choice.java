package com.electionManager.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * The type choice.
 *
 */
@Entity
@Table(name = "election_choices")
public class Choice {
	@Id
	@Column(name = "id", nullable = false, columnDefinition = "int(11)")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "choice", nullable = false, columnDefinition ="varchar(255)")
    private String choice; 
    
    
    @Column(name = "election_id", nullable = false)
    private Long election_id;

    @Override
	    public String toString() {
	        return "Choice{" +
	                "id=" + id +
	                ", choice='" + choice + '\'' +
	                ", election_id='" + election_id + '\'' +                        
	                '}';
	    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public Long getElection_id() {
		return election_id;
	}

	public void setElection_id(Long election_id) {
		this.election_id = election_id;
	}
 

   


}
