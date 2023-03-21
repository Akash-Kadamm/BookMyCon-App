package com.micro.housekeepingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HousekeepingDto {
    private Long id;
    private String auditoriumName;
    private String housekeepingServices;
}
