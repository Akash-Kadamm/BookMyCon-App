package com.bookmycon.dto;

import com.bookmycon.model.Auditoriums;
import com.bookmycon.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingsDTO {
    private int bookingId;
    private Auditoriums aduitoriamId;

    private User userId;
    private String bookingTimeFrom;
    private String bookingTimeTO;
    private String bookingAgenda;
    private LocalDate bookingDateFrom;
    private LocalDate bookingDateTo;

}
