package com.dbmigration.demo.dto;

import com.dbmigration.demo.model.*;
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
