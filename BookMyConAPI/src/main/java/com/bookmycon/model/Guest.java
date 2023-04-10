package com.bookmycon.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "guest")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class Guest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int guestId;
	@Column(name = "guest_name")
	private String guestName;
	@Column(name = "guest_company")
	private String guestCompany;
	@Column(name = "guest_email")
	private String guestEmail;
	@Column(name = "guest_mobile_no")
	private String guestMobileNo;
	@Column(name = "image")
	private String thumbnail;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User users;

}
