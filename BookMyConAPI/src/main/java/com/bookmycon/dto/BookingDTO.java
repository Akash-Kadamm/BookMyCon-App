package com.bookmycon.dto;
import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.bookmycon.model.Auditoriums;
import com.bookmycon.model.Booking;
import com.bookmycon.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingDTO {

	private int bookingId;
	private Auditoriums aduitoriamId;
	@JsonIgnore
	private User userId;
	private LocalDateTime bookingDateTimeFrom;
	private LocalDateTime bookingDateTimeTo;
	private String bookingAgenda;
	
	public static BookingDTO entityToDto (Booking entity) {
		BookingDTO bookinDto = new BookingDTO();
		BeanUtils.copyProperties(entity, bookinDto);
		bookinDto.setBookingDateTimeTo(LocalDateTime.of(entity.getBookingDateTo(), entity.getBookingTimeTO()));
		bookinDto.setBookingDateTimeFrom(LocalDateTime.of(entity.getBookingDateFrom(), entity.getBookingTimeFrom()));
		return bookinDto;
	}
}
