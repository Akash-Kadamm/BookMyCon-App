package com.ioffice.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ioffice.model.Auditoriums;
import com.ioffice.model.Booking;
import com.ioffice.model.User;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
	List<Booking> findByAduitoriamId(Auditoriums adudiAuditoriums);

	List<Booking> findByBookingDateFromAndBookingDateTo(LocalDate dateFrom, LocalDate dateTo);

	@Query(value = "select * from booking where user_id=?1 and booking_date_from >= CURDATE()",nativeQuery = true)
	List<Booking> findByUserId(int userId);
	
	List<Booking> findByUserId(User userId);


}
