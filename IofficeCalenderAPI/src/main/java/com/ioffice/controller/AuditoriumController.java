package com.ioffice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ioffice.model.Auditoriums;
import com.ioffice.service.AuditoriumService;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin")
public class AuditoriumController {
	@Autowired
	private AuditoriumService auditoriumService;

	@PostMapping("/addAudi")
	public ResponseEntity<Auditoriums> addAuditorium(@RequestBody Auditoriums auditorium) {
		System.out.println("in controller");
		auditoriumService.addAuditorium(auditorium);
		return new ResponseEntity<Auditoriums>(HttpStatus.CREATED);

	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Auditoriums>> getAllAuditoriums()

	{
		return new ResponseEntity<List<Auditoriums>>(auditoriumService.showAll(), HttpStatus.OK);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateAuditorium(@PathVariable int id, @RequestBody Auditoriums auditorium) {
		System.out.println("Is in controlleer");
		System.out.println(auditorium);
		auditorium.setAuditoriumId(id);
		auditoriumService.updateAuditorium(id, auditorium);

		System.out.println(auditorium);
		return new ResponseEntity<String>("record updated", HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAuditorium(@PathVariable int id) {
		auditoriumService.deleteById(id);
		return new ResponseEntity<String>("record deleted", HttpStatus.OK);
	}
	
	

}
