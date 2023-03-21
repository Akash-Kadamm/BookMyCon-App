package com.micro.housekeepingservice.controller;

import com.micro.housekeepingservice.dto.HousekeepingDto;
import com.micro.housekeepingservice.service.HousekeepingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/housekeeping")
@CrossOrigin("*")
public class HousekeepingController {
    private HousekeepingService housekeepingService;
    //Build Rest Api for housekeeping service
    @PostMapping
    public ResponseEntity<HousekeepingDto> saveHousekeepingRequest(@RequestBody HousekeepingDto housekeepingDto){
        HousekeepingDto savedHousekeeping = housekeepingService.saveHousekeepingRequest(housekeepingDto);
        return new ResponseEntity<>(savedHousekeeping, HttpStatus.CREATED);
    }
    //Build REST API to retrieve all housekeeping requests.
    @GetMapping
    public ResponseEntity<List<HousekeepingDto>> getAllHousekeepingRequests(){
        List<HousekeepingDto> housekeeping = housekeepingService.getAllHousekeepingRequests();
        return new ResponseEntity<>(housekeeping,HttpStatus.OK);
    }
    //Build REST API to delete housekeeping requests.
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteHousekeepingRequest(@PathVariable("id") Long housekeepingId){
        housekeepingService.deleteHouskeepingRequest(housekeepingId);
        return new ResponseEntity<>("Housekeeping Request Deleted Successfully!!!",HttpStatus.OK);
    }

}
