package com.bookmycon.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookmycon.model.Auditoriums;
import com.bookmycon.repository.AuditoriumRepository;

@Service
public class AuditoriumService {

	@Autowired
	AuditoriumRepository auditoriumRepo;
	
	Logger logger=Logger.getLogger(AuditoriumService.class);

	public Auditoriums addAuditorium(Auditoriums auditoriums) {
		auditoriumRepo.save(auditoriums);
		return auditoriums;
	}
	

	/*
	 * Update the Auditorium
	 * 
	 * @param Auditorium Id- Path Variable and Auditorium object
	 * @param Auditorium
	 * @return void
	 * 
	 * */
	public void updateAuditorium(int id, Auditoriums auditorium) {
		 logger.info("Auditorium id:"+id+" Auditorium object: "+auditorium);
		 logger.debug("Finding the Auditorium object by its id");
		Auditoriums updateAuditorium = auditoriumRepo.findById(id).orElse(null);
         logger.info("Setting the updated Values and save to db ");
		updateAuditorium.setAuditoriumName(auditorium.getAuditoriumName());
		updateAuditorium.setAuditoriumLocation(auditorium.getAuditoriumLocation());
		updateAuditorium.setAuditoriumCapacity(auditorium.getAuditoriumCapacity());
		auditoriumRepo.save(updateAuditorium);
	}

	public void deleteById(int id) {
		auditoriumRepo.deleteById(id);
	}

	public List<Auditoriums> showAll() {
		return auditoriumRepo.findAll();

	}
	
	
	
	public List<Auditoriums> findByAuditoriumByName(String name ) {
		return auditoriumRepo.findByAuditoriumName(name);

	}
	
	/*
	 * Get the Auditorium By Its Id
	 * 
	 * @param Auditorium Id
	 * @return Auditorium Object
	 * 
	 * */
	public  Map<String, Object> getAuditoriumById(int id) {
		Map<String, Object> response=new HashMap<>();
		logger.info("Auditorium id:"+id);
		 logger.debug("Finding the Auditorium object by its id and return object");
		Auditoriums audi= auditoriumRepo.findById(id).orElse(null);
		response.put("Auditorium", audi);
	  return response;
	}
}
