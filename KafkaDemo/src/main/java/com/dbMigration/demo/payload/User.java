package com.dbMigration.demo.payload;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
@Table(name = "Users")
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

    @Column(name = "user_contact_number")
    private String userContactNumber;

    @Column(name = "company_Id")
    private int companyId;
    @Column(name = "department_id")
    private int departmentId;

    @Column(name = "address_id")
    private int addressId;

    @Column(columnDefinition = "boolean default false", name = "is_migrate")
    private boolean isMigrate;
}
