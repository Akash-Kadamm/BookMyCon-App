package com.ioffice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ioffice.model.Auditoriums;
import com.ioffice.repository.AuditoriumRepository;

@Service
public class AuditoriumService {

	@Autowired
	AuditoriumRepository auditoriumRepo;

	public Auditoriums addAuditorium(Auditoriums auditoriums) {
		auditoriumRepo.save(auditoriums);
		return auditoriums;
	}

	public void updateAuditorium(int id, Auditoriums auditorium) {
		Auditoriums updateAuditorium = auditoriumRepo.findById(id).orElse(null);

		updateAuditorium.setAuditoriumName(auditorium.getAuditoriumName());
		updateAuditorium.setAuditoriumLocation(auditorium.getAuditoriumLocation());
		updateAuditorium.setAuditoriumType(auditorium.getAuditoriumType());
		updateAuditorium.setAuditoriumCapacity(auditorium.getAuditoriumCapacity());
		updateAuditorium.setAuditoriumAminity(auditorium.getAuditoriumAminity());
		auditoriumRepo.save(updateAuditorium);
	}

	public void deleteById(int id) {
		auditoriumRepo.deleteById(id);
	}

	public List<Auditoriums> showAll() {
		return auditoriumRepo.findAll();

	}
	
}
