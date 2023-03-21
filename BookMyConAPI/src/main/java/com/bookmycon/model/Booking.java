package com.bookmycon.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="booking_id")
    private int bookingId;
    
    @OneToOne 
    @JoinColumn(name = "aduitoriam_id")
    private Auditoriums aduitoriamId;
    
    @ManyToOne 
    @JoinColumn(name = "user_id")
    private User userId;
    
    @Column(name="booking_date_from")
    private LocalDate bookingDateFrom;
    
    @Column(name="booking_date_to")
    private LocalDate bookingDateTo;

	@Column(name="booking_time_from")
	private LocalTime bookingTimeFrom;

	@Column(name="booking_time_to")
	private LocalTime bookingTimeTO;
    
    @Column(name="booking_agenda")
    private String bookingAgenda;

    @JsonIgnore
    @OneToMany(mappedBy = "booking",fetch = FetchType.LAZY)
    List<Order> orders;

}

