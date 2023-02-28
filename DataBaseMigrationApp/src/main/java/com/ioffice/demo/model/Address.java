package com.ioffice.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private int addressId;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "local_landmark")
    private String localLandmark;

    @Column(name = "home_number")
    private String homeNumber;
    @Column(name = "pin_code")
    private String pinCode;
}
