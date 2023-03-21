package com.bookmycon.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Table(name = "complaints")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "complaint_id")
    private int complaintId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "booking_id")
    private int bookingId;

    @Column(name = "order_id")
    private int orderId;

    @Column(name = "description")
    private String description;

    @Column(name = "complaint_type")
    private String complaintType;
}
