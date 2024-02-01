package com.bookmycon.controller;

import com.bookmycon.model.Floor;
import com.bookmycon.repository.FloorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class FloorController {

    @Autowired
    private FloorRepository floorRepository;

    @GetMapping("floor")
    public List<Floor> getFloor() {

        return this.floorRepository.findAll();
    }

//    @PostMapping
//    public Floor createVenue(@RequestBody Floor venue) {
//        return floorRepository.save(venue);
//    }
//
//    @PostConstruct
//    public void initDummyData() {
//
//        Floor venue1 = new Floor(3, "Open office-1", "A-102", "open office ", "Monitor, Speaker", 20, true);
//        Floor venue2 = new Floor(4, "Play Zone", "B-708", "play area", "pool table, foos ball", 10, false);
//        Floor venue3 = new Floor(5, "Open office-2", "B-108", "ac open office", "Monitor, Spekaer", 30, true);
//        Floor venue4 = new Floor(6, "Play Zone", "B-708", "play area", "table tennis, chess, carom", 20, true);
//        Floor venue5= new Floor(7, "Conerence Hall", "Galaxy-309", "conference hall", "Monitor, speaker, printer, projector", 100, true);
//
//     floorRepository.saveAll(List.of(venue1, venue2,venue3,venue4,venue5));
//    }
   }
