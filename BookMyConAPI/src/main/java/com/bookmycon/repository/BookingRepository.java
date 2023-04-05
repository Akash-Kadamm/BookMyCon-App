package com.bookmycon.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookmycon.model.Auditoriums;
import com.bookmycon.model.Booking;
import com.bookmycon.model.User;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
	List<Booking> findByAduitoriamId(Auditoriums adudiAuditoriums);

	List<Booking> findByBookingDateFromAndBookingDateTo(LocalDate dateFrom, LocalDate dateTo);

	@Query(value = "select * from booking where user_id=?1 and booking_date_from >= CURDATE()",nativeQuery = true)
	List<Booking> findByUserId(int userId);
	
	List<Booking> findByUserId(User userId);

	List<Booking> findByAduitoriamIdAndBookingDateFrom(Auditoriums auditoriums,LocalDate bookingDateFrom);


}
