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

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public Auditoriums getAduitoriamId() {
		return aduitoriamId;
	}

	public void setAduitoriamId(Auditoriums aduitoriamId) {
		this.aduitoriamId = aduitoriamId;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public LocalDateTime getBookingDateTimeFrom() {
		return bookingDateTimeFrom;
	}

	public void setBookingDateTimeFrom(LocalDateTime bookingDateTimeFrom) {
		this.bookingDateTimeFrom = bookingDateTimeFrom;
	}

	public LocalDateTime getBookingDateTimeTo() {
		return bookingDateTimeTo;
	}

	public void setBookingDateTimeTo(LocalDateTime bookingDateTimeTo) {
		this.bookingDateTimeTo = bookingDateTimeTo;
	}

	public String getBookingAgenda() {
		return bookingAgenda;
	}

	public void setBookingAgenda(String bookingAgenda) {
		this.bookingAgenda = bookingAgenda;
	}

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
