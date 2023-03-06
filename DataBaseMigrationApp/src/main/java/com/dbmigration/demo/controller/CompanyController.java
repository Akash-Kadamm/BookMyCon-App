package com.dbmigration.demo.controller;

import com.dbmigration.demo.model.Company;
import com.dbmigration.demo.service.CompanyService;
import org.apache.log4j.Logger;
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

    private static Logger logger= Logger.getLogger(CompanyController.class);
    @GetMapping("/getAllUsers/{companyName}")
    public ResponseEntity<List<Company>> getAllUsers(@PathVariable String companyName){
        return new ResponseEntity<>(
                companyService.getAllUsersToBeMigrate(companyName),
                HttpStatus.OK
        );
    }

}
