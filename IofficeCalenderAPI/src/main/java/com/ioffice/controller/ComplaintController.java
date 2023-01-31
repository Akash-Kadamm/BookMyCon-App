package com.ioffice.controller;

import com.ioffice.model.Complaint;
import com.ioffice.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/complaint")
@CrossOrigin("*")
public class ComplaintController {


    @Autowired
    private ComplaintService complaintService;


    @GetMapping("/getAllComplaints")
    public ResponseEntity<List<Complaint>> allComplaints(){
        return  new ResponseEntity<List<Complaint>>(
                complaintService.getAllComplaints(),
                HttpStatus.OK
         );
    }

    @GetMapping("/getAllDrinksAndSnacksComplaints")
    public ResponseEntity<List<Complaint>> drinksAndSnacksComplaints(){
        return  new ResponseEntity<List<Complaint>>(
                complaintService.getAllDrinksAndSnacksComplaints(),
                HttpStatus.OK
        );
    }

    @GetMapping("/getAllHouseKeepingComplaints")
    public ResponseEntity<List<Complaint>> houseKeepingComplaints(){
        return  new ResponseEntity<List<Complaint>>(
                complaintService.getAllHouseKeepingComplaints(),
                HttpStatus.OK
        );
    }


}
