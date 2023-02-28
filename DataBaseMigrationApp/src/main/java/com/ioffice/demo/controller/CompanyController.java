package com.ioffice.demo.controller;

import com.ioffice.demo.model.Company;
import com.ioffice.demo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/company")
public class CompanyController {


    @Autowired
    private CompanyService companyService;

    @GetMapping("/getAllUsers/{companyName}")
    public ResponseEntity<List<Company>> getAllUsers(@PathVariable String companyName){
        return new ResponseEntity<>(
                companyService.getAllUsersToBeMigrate(companyName),
                HttpStatus.OK
        );
    }

}
