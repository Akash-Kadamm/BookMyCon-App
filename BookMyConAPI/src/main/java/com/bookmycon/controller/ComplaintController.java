package com.bookmycon.controller;

import com.bookmycon.model.Complaint;
import com.bookmycon.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaint")
@CrossOrigin("*")
public class ComplaintController {


    @Autowired
    private ComplaintService complaintService;


    /*
     * Retrieve All Complaints.
     *
     * @param
     * @return ResponseEntity object
     *
     * */
    @GetMapping("/getAllComplaints")
    public ResponseEntity<List<Complaint>> allComplaints(){
        return  new ResponseEntity<List<Complaint>>(
                complaintService.getAllComplaints(),
                HttpStatus.OK
         );
    }

    /*
     * Retrieve All Drinks and Snacks Complaints.
     *
     * @param
     * @return ResponseEntity object
     *
     * */
    @GetMapping("/getAllDrinksAndSnacksComplaints")
    public ResponseEntity<List<Complaint>> drinksAndSnacksComplaints(){
        return  new ResponseEntity<List<Complaint>>(
                complaintService.getAllDrinksAndSnacksComplaints(),
                HttpStatus.OK
        );
    }


    /*
     * Retrieve All HouseKeeping Complaints .
     *
     * @param
     * @return ResponseEntity object
     *
     * */
    @GetMapping("/getAllHouseKeepingComplaints")
    public ResponseEntity<List<Complaint>> houseKeepingComplaints(){
        return  new ResponseEntity<List<Complaint>>(
                complaintService.getAllHouseKeepingComplaints(),
                HttpStatus.OK
        );
    }


    @PostMapping("/makeComplaint")
    public ResponseEntity<String> makeComplaints(@RequestBody Complaint complaint){
        return  new ResponseEntity<>(
                complaintService.makeComplaint(complaint),
                HttpStatus.CREATED
        );
    }

//
//    @PostMapping("/makeComplaint")
//    public ResponseEntity<String> makeComplaints(@RequestBody Complaint complaint){
//        return  new ResponseEntity<>(
//                complaintService.makeComplaint(complaint),
//                HttpStatus.CREATED
//        );
//    }

    @DeleteMapping("/resolveComplaint/{complaintId}")
    public ResponseEntity<String> deleteComplaint(@PathVariable int complaintId){
         return new ResponseEntity<>(
            complaintService.resolveComplaint(complaintId),
            HttpStatus.OK
         );
    }



}
