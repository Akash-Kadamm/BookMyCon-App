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


    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }


    public List<Complaint> getAllDrinksAndSnacksComplaints() {
        return complaintRepository.drinksAndSnacksComplaints();
    }


    public List<Complaint> getAllHouseKeepingComplaints() {
        return complaintRepository.houseKeepingComplaints();
    }


    public String makeComplaint(Complaint complaint) {
        complaintRepository.save(complaint);
        return "Complaint Send..";
    }

    public String resolveComplaint(int complaintId) {
        complaintRepository.deleteById(complaintId);
        return "Complaint is resolved.. " + complaintId;
    }
}
