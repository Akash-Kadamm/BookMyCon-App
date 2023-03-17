package com.bookmycon.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_role")
    private String userRole;

    @Column(name = "user_contact")
    private String userContact;

//    @Column(name = "image")
    private String thumbnail;
}
