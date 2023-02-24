package com.bookmycon.repository;

import com.bookmycon.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository
        extends JpaRepository<Complaint,Integer> {


    @Query(
            value = "select * from complaints where complaint_type ='drinksAndSnacks' "
            ,nativeQuery = true
           )
    public List<Complaint> drinksAndSnacksComplaints();


    @Query(
            value = "select * from complaints where complaint_type ='houseKeeping' "
            ,nativeQuery = true
          )
    public List<Complaint> houseKeepingComplaints();


}
