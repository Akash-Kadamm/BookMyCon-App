package com.micro.housekeepingservice.mapper;

import com.micro.housekeepingservice.dto.HousekeepingDto;
import com.micro.housekeepingservice.entity.Housekeeping;

public class HousekeepingMapper {
    //convert jpa entity to dto
    public static HousekeepingDto mapToHousekeepingDto(Housekeeping housekeeping){
        HousekeepingDto housekeepingDto = new HousekeepingDto(
                housekeeping.getId(),
                housekeeping.getAuditoriumName(),
                housekeeping.getHousekeepingServices()
        );
        return housekeepingDto;
    }
    //convert Dto To jpa entity
    public static Housekeeping mapToHousekeeping(HousekeepingDto housekeepingDto){
        Housekeeping housekeeping = new Housekeeping(
                housekeepingDto.getId(),
                housekeepingDto.getAuditoriumName(),
                housekeepingDto.getHousekeepingServices()
        );
        return housekeeping;
    }
}
