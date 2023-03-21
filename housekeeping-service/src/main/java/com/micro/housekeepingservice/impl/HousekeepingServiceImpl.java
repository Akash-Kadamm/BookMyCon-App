package com.micro.housekeepingservice.impl;

import com.micro.housekeepingservice.dto.HousekeepingDto;
import com.micro.housekeepingservice.entity.Housekeeping;
import com.micro.housekeepingservice.mapper.HousekeepingMapper;
import com.micro.housekeepingservice.repository.HousekeepingRepository;
import com.micro.housekeepingservice.service.HousekeepingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HousekeepingServiceImpl implements HousekeepingService {
    private HousekeepingRepository housekeepingRepository;
    @Override
    public HousekeepingDto saveHousekeepingRequest(HousekeepingDto housekeepingDto) {
        //convert dto to jpa
        Housekeeping housekeeping = new Housekeeping(
                housekeepingDto.getId(),
                housekeepingDto.getAuditoriumName(),
                housekeepingDto.getHousekeepingServices()
        );
        Housekeeping savedHousekeeping = housekeepingRepository.save(housekeeping);

        HousekeepingDto savedHousekeepingDto = new HousekeepingDto(
                savedHousekeeping.getId(),
                savedHousekeeping.getAuditoriumName(),
                savedHousekeeping.getHousekeepingServices()
        );
        return savedHousekeepingDto;
    }

    @Override
    public List<HousekeepingDto> getAllHousekeepingRequests() {
        List<Housekeeping> housekeepings = housekeepingRepository.findAll();
        return housekeepings.stream().map(HousekeepingMapper::mapToHousekeepingDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteHouskeepingRequest(Long HousekeepingId) {
        housekeepingRepository.deleteById(HousekeepingId);
    }
}
