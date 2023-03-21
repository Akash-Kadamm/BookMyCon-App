package com.micro.housekeepingservice.service;

import com.micro.housekeepingservice.dto.HousekeepingDto;

import java.util.List;

public interface HousekeepingService {
    HousekeepingDto saveHousekeepingRequest(HousekeepingDto housekeepingDto);
    List<HousekeepingDto> getAllHousekeepingRequests();
    void deleteHouskeepingRequest(Long HousekeepingId);
}
