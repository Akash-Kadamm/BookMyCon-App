package com.ioffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ioffice.dto.BookingDTO;
import com.ioffice.model.Auditoriums;
import com.ioffice.model.Booking;
import com.ioffice.model.User;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>{
	List<Booking> findByAduitoriamId(Auditoriums  adudiAuditoriums);
	List<Booking> findByUserId(User userId);
}
