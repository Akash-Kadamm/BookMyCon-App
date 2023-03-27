package com.dbMigration.demo.dto;


import com.dbMigration.demo.payload.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDTO {
    private User user;
    private Company company;
    private Address address;
    private Department department;
    private CardDetail cardDetail;

}
