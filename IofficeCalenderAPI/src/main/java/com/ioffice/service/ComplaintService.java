package com.ioffice.service;


import com.ioffice.model.Complaint;
import com.ioffice.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;


    public List<Complaint> getAllComplaints(){
        return  complaintRepository.findAll();
    }

    public List<Complaint> getAllDrinksAndSnacksComplaints(){
        return  complaintRepository.drinksAndSnacksComplaints();
    }
    public List<Complaint> getAllHouseKeepingComplaints(){
        return  complaintRepository.houseKeepingComplaints();
    }


}
