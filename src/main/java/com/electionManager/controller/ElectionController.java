package com.electionManager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import com.electionManager.repository.ElectionRepository;
import com.electionManager.repository.ChoiceRepository;
import com.electionManager.exception.ResourceNotFoundException;
import com.electionManager.model.Election;
import com.electionManager.model.Choice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
//@RequestMapping("/api/v1")
public class ElectionController {
			@Autowired
			private ChoiceRepository ChoiceRepository;
			@Autowired
			private ElectionRepository electionRepository;
			//***************************************************************************
		    //== Heartbeat == This is used to check if the service is up.
			@RequestMapping(value = "/heartbeat")
		    public void Heartbeat(){	
		    }
			
			//***************************************************************************
			//== creating an election ==
		    @PostMapping("/elections/save")
		    public String createElection(@Valid @RequestBody Election elect) {
		    	 electionRepository.save(elect);
				return "{\"message\": \"successful\"}";
		    }
		    //***************************************************************************
		    //== editing an election ==
		    @PutMapping("/elections/update")
			public String editElection(@Valid @RequestBody Election Elect)
					throws ResourceNotFoundException {

				Election elect =electionRepository

								.findById( Elect.getId())

								.orElseThrow(() -> new ResourceNotFoundException("Election not found on :: " + Elect.getId()));

				elect.setName(Elect.getName());

				elect.setStartTime(Elect.getStartTime());

				elect.setEndTime(Elect.getEndTime());

				electionRepository.save(elect);
				return "{\"message\": \"successful\"}";
                }
		   
		    //***************************************************************************
		    //== removing an election ==
		    @GetMapping("elections/{electionId}/remove")

			   public String removeElection(@PathVariable(value = "electionId") Long electionId) throws Exception {

			     Election elect =electionRepository

			             .findById(electionId)

			             .orElseThrow(() -> new ResourceNotFoundException("Election not found on :: " + electionId));

			     electionRepository.delete(elect);
			     return "{\"message\": \"successful\"}";
			     }
		   //***************************************************************************
		   //== get election details ==
		    @GetMapping("elections/{electionId}/get")
	        public String getElectionDetails(@PathVariable(value = "electionId") Long electionId)
	            
	        	throws ResourceNotFoundException {

	  		      Election elect =

	  		          electionRepository

	  		              .findById(electionId)

	  		              .orElseThrow(() -> new ResourceNotFoundException("Election not found on :: " + electionId));
				String electDetail= "\"id\":" + elect.getId()+
						",\"name\":\""+ elect.getName()+
						"\",\"startTime\":\"" + elect.getStartTime() +
						"\",\"endTime\":\"" + elect.getEndTime()+"\"";

	  		      return "{ \"data\":{" + electDetail + "} , \"message\": \"successful\"}";
		    	
	       }
		   //***************************************************************************
		   //== get all elections ==
		    @RequestMapping(method = RequestMethod.GET, value="/elections/all")

			   public String getAllElections() {
				   String output="";
				   List<Election> list = electionRepository.findAll();
				   for(int i=0;i<list.size();i++) {
					   Election elect=list.get(i);
					   String electDetail= "{\"id\":" + elect.getId()+
							   ",\"name\":\""+ elect.getName()+
							   "\",\"startTime\":\"" + elect.getStartTime() +
							   "\",\"endTime\":\"" + elect.getEndTime()+"\"}";
					   if(i>0) output= output + ","+electDetail;
					   else output= output +electDetail;
						
				   }
				   return "{ \"data\":[" + output + "] , \"message\": \"successful\"}";

			   }
		    //***************************************************************************
		    //== check if election exists ==
		    @RequestMapping("/elections/exists")
		    public String electionExists(@RequestParam("electionId") int electionId) throws ResourceNotFoundException{
		    	Long id =Long.valueOf(electionId);	    	
		    	Election elect =

		  		          electionRepository

		  		              .findById(id)

		  		              .orElseThrow(() -> new ResourceNotFoundException("Election not found on :: " + electionId));
		           return "{\"message\": \"successful\"}";
			    	
		       }
		   //***************************************************************************
		   // == creating a choice for an election ==
		    @PostMapping("/election/{electionId}/choices/save")

			public String editElection(@PathVariable(value = "electionId") Long electionId, @Valid @RequestBody Choice choice)throws ResourceNotFoundException{
		    	
		    	choice.setElection_id(electionId);
		    	 ChoiceRepository.save(choice);
		    	
			   return "{\"message\": \"successful\"}";
              }
		   //***************************************************************************
		   // == editing a choice of an election ==
		    @PutMapping("/elections/{electionId}/choices/{choiceId}/edit")
			public String editChoice(@PathVariable(value = "electionId") Long electionId,@PathVariable(value = "choiceId") Long choiceId, @Valid @RequestBody Choice choice)
					throws ResourceNotFoundException {

				Choice Cho =ChoiceRepository

								.findById(choiceId)

								.orElseThrow(() -> new ResourceNotFoundException("Choice not found on :: " + choiceId));

				Cho.setChoice(choice.getChoice());
				Cho.setElection_id(electionId);
			    ChoiceRepository.save(Cho);
                
				return "{\"message\": \"successful\"}";
                }
		   //***************************************************************************
		   //== removing a choice from an election ==
		    @GetMapping("/elections/{electionId}/choices/{choiceId}/remove")

			   public String removechoice(@PathVariable(value = "electionId") Long electionId,@PathVariable(value = "choiceId") Long choiceId) throws Exception {

			              Choice choice =ChoiceRepository

			             .findById(choiceId)

			             .orElseThrow(() -> new ResourceNotFoundException("Election not found on :: " + electionId));
			              ChoiceRepository.delete(choice);
			              return "{\"message\": \"successful\"}";
			    
			     }
		  //***************************************************************************
		    //get election choices
		    @GetMapping(value="/elections/{electionId}/choices/all")

		    public String getchoice(@PathVariable(value = "electionId") Long electionId) throws ResourceNotFoundException {
		    	 
			    	 List<Choice> choice =ChoiceRepository.findAll();
			    	 int i;
			    	 Choice ch;
			    	 String output=null;
                     for(i=0;i<choice.size();i++) {
                    	  ch=choice.get(i);
                    	  Long id=ch.getElection_id();
                    	  if(id==electionId) {	
                           String chDetail= "{\"id\":" +ch.getId()+ ",\"choice\":\""+ ch.getChoice()+"\"}";
					        if(i>0) output= output + ","+chDetail;
					        else output= output +chDetail;
					      }
		            }		 
				   
				   return "{ \"data\":[" + output + "] , \"message\": \"successful\"}";

			   }
		    //********************************************************************
		    //== increment total vote number for an election ==
		   
		    @GetMapping("/elections/votes/increment")

	      public String IncremenetNumberOfVotes()         	
          
		        	throws ResourceNotFoundException {

		  		      List<Election> elect = electionRepository.findAll();
		  		      int i;
			    	  Election elec;			    	
                      for(i=0;i<elect.size();i++) {
                   	  elec=elect.get(i);
                   	  elec.setNumberOfVotes(elec.getNumberOfVotes()+1);
                    	electionRepository.save(elec);
		            }	
 		            return "{\"message\": \"successful\"}";
					    	
		       }
		    
	}

