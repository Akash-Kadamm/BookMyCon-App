package com.bookmycon.service;


import com.bookmycon.model.Complaint;
import com.bookmycon.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import java.util.List;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    Logger logger=Logger.getLogger(ComplaintService.class);

    /*
     * Retrieve All complaints.
     *
     * @param
     * @return List of complaints
     *
     * */
    public List<Complaint> getAllComplaints(){
        logger.info("Getting all complaints");
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
        logger.info("Getting all drinks and snacks complaints");
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
        logger.info("Getting all housekeeping complaints");
        return  complaintRepository.houseKeepingComplaints();
    }


    public String makeComplaint(Complaint complaint){
        logger.info("Making a new complaint");
        complaintRepository.save(complaint);
        return "Complaint Send..";
    }
//    public String makeComplaint(Complaint complaint){
//        complaintRepository.save(complaint);
//        return "Complaint Send..";
//    }

    public String resolveComplaint(int complaintId){
        logger.info("Resolving the complaint with ID: {}" + complaintId);
        complaintRepository.deleteById(complaintId);
        return "Complaint is resolved.. "+complaintId;
    }
}
