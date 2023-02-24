package com.bookmycon.service;


import com.bookmycon.model.Complaint;
import com.bookmycon.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;


    /*
     * Retrieve All complaints.
     *
     * @param
     * @return List of complaints
     *
     * */
    public List<Complaint> getAllComplaints(){
        return  complaintRepository.findAll();
    }

    /*
     * Retrieve All Drink and Snacks complaints.
     *
     * @param
     * @return List of complaints
     *
     * */
    public List<Complaint> getAllDrinksAndSnacksComplaints(){
        return  complaintRepository.drinksAndSnacksComplaints();
    }

    /*
     * Retrieve All HouseKeeping complaints.
     *
     * @param
     * @return List of complaints
     *
     * */
    public List<Complaint> getAllHouseKeepingComplaints(){
        return  complaintRepository.houseKeepingComplaints();
    }


    public String makeComplaint(Complaint complaint){
        complaintRepository.save(complaint);
        return "Complaint Send..";
    }
//    public String makeComplaint(Complaint complaint){
//        complaintRepository.save(complaint);
//        return "Complaint Send..";
//    }

    public String resolveComplaint(int complaintId){
        complaintRepository.deleteById(complaintId);
        return "Complaint is resolved.. "+complaintId;
    }
}
