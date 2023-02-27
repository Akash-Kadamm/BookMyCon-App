package com.bookmycon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookmycon.model.Auditoriums;

@Repository
public interface AuditoriumRepository extends JpaRepository<Auditoriums, Integer> { 
	List<Auditoriums> findByAuditoriumName(String name);
}
