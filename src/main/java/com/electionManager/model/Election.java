package com.electionManager.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.*;



/**
 * The type election.
 *
 */
@Entity
@Table(name = "elections")
public class Election {

    
	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
    

    @Column(name = "name", nullable = false, columnDefinition ="varchar(255)")
    private String name;    

    
    @Column(name = "start_time", nullable = false)
    private String startTime;

    
    @Column(name = "end_time", nullable = false)
    private String endTime;
    
   @Column(name = "number_of_votes", nullable = false)
    private int numberOfVotes;
    
 


	public long getId() {
	return id;
}




public void setId(long id) {
	this.id = id;
}




public String getName() {
	return name;
}




public void setName(String name) {
	this.name = name;
}




public String getStartTime() {
	return startTime;
}




public void setStartTime(String startTime) {
	this.startTime = startTime;
}




public String getEndTime() {
	return endTime;
}




public void setEndTime(String endTime) {
	this.endTime = endTime;
}




public int getNumberOfVotes() {
	return numberOfVotes;
}




public void setNumberOfVotes(int numberOfVotes) {
	this.numberOfVotes = numberOfVotes;
}




	@Override
    public String toString() {
        return "Election{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +                         
                '}';
    }


}
