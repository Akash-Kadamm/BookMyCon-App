package com.bookmycon.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookmycon.model.Auditoriums;

@Repository
public interface AuditoriumRepository extends JpaRepository<Auditoriums, Integer> {
	List<Auditoriums> findByAuditoriumName(String name);
	List<Auditoriums> findAll();

	@Query("SELECT COUNT(a) FROM Auditoriums a WHERE a.booked = true")
	int countBookedAuditoriums();
}