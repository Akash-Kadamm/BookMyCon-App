package com.bookmycon.controller;

import com.bookmycon.model.PieChart;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/piechart")
public class PieChartController {

    @GetMapping("/all")
    public List<PieChart> getAllCapacities() {
        return Arrays.asList(
                new PieChart("Conference Halls", 35),
                new PieChart("Cafeterias", 20),
                new PieChart("Open Offices", 30),
                new PieChart("Play Areas", 5)
        );
    }
}