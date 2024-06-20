package com.bookmycon.model;

import javax.persistence.*;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@Entity
@Table(name = "auditorium")
public class Auditoriums implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="auditorium_id")
	private int auditoriumId;

	@Column(name="auditorium_name")
	private String auditoriumName;

	@Column(name="auditorium_location")
	private String auditoriumLocation;

	@Column(name="auditorium_Capacity")
	private int auditoriumCapacity;

	@Column(name="auditorium_type")
	private String auditoriumType;

	@Column(name="auditorium_aminity")
	private String auditoriumAminity;

	@Column(name="auditorium_book")
	private Boolean booked;

	@OneToOne(mappedBy = "auditoriums", cascade = CascadeType.ALL)
	private Booking booking;

	public Auditoriums() {
		super();
	}
	public Auditoriums(int auditoriumId, String auditoriumName, String auditoriumLocation, int auditoriumCapacity,
					   String auditoriumType, String auditoriumAminity, boolean booked, Booking booking) {
		super();
		this.auditoriumId = auditoriumId;
		this.auditoriumName = auditoriumName;
		this.auditoriumLocation = auditoriumLocation;
		this.auditoriumCapacity = auditoriumCapacity;
		this.auditoriumType = auditoriumType;
		this.auditoriumAminity = auditoriumAminity;
		this.booked = booked;
		this.booking = booking;

	}

	public int getAuditoriumId() {
		return auditoriumId;
	}

	public void setAuditoriumId(int auditoriumId) {
		this.auditoriumId = auditoriumId;
	}

	public String getAuditoriumName() {
		return auditoriumName;
	}

	public void setAuditoriumName(String auditoriumName) {
		this.auditoriumName = auditoriumName;
	}

	public String getAuditoriumLocation() {
		return auditoriumLocation;
	}

	public void setAuditoriumLocation(String auditoriumLocation) {
		this.auditoriumLocation = auditoriumLocation;
	}

	public int getAuditoriumCapacity() {
		return auditoriumCapacity;
	}

	public void setAuditoriumCapacity(int auditoriumCapacity) {
		this.auditoriumCapacity = auditoriumCapacity;
	}

	public String getAuditoriumType() {
		return auditoriumType;
	}

	public void setAuditoriumType(String auditoriumType) {
		this.auditoriumType = auditoriumType;
	}

	public String getAuditoriumAminity() {
		return auditoriumAminity;
	}

	public void setAuditoriumAminity(String auditoriumAminity) {
		this.auditoriumAminity = auditoriumAminity;
	}
	public Boolean  isBooked() {
		return booked;
	}

	public void setBooked(Boolean  booked) {
		this.booked = booked;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	@Override
	public String toString() {
		return "Auditoriums{" +
				"auditoriumId=" + auditoriumId +
				", auditoriumName='" + auditoriumName + '\'' +
				", auditoriumLocation='" + auditoriumLocation + '\'' +
				", auditoriumCapacity=" + auditoriumCapacity +
				", auditoriumType='" + auditoriumType + '\'' +
				", auditoriumAminity='" + auditoriumAminity + '\'' +
				", booked=" + booked +
				", booking=" + booking +
				'}';
	}

	public Auditoriums(String auditoriumName, String auditoriumLocation, int auditoriumCapacity, String auditoriumType,
					   String auditoriumAminity) {
		super();
		this.auditoriumName = auditoriumName;
		this.auditoriumLocation = auditoriumLocation;
		this.auditoriumCapacity = auditoriumCapacity;
		this.auditoriumType = auditoriumType;
		this.auditoriumAminity = auditoriumAminity;
	}


}

