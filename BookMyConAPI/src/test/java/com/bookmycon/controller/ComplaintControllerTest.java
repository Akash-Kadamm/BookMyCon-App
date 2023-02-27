package com.bookmycon.controller;

import com.bookmycon.model.Complaint;
import com.bookmycon.service.ComplaintService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ComplaintControllerTest {

    @InjectMocks
    private ComplaintController complaintController;

    @Mock
    private ComplaintService complaintService;

    Complaint complaint1;
    Complaint complaint2;

    ResponseEntity<List<Complaint>> complaints;

    ResponseEntity<String> actualMessage;

     static final String makeComplaintMessage="Complaint Send..";
    @Before
    public void setData(){
        complaint1=Complaint.builder()
                .complaintId(1)
                .complaintType("drinksAndSnacks")
                .userId(1)
                .orderId(1)
                .description("not good")
                .build();

        complaint2=Complaint.builder()
                .complaintId(2)
                .complaintType("houseKeeping")
                .userId(1)
                .orderId(1)
                .description("dirty floor")
                .build();
    }

    @Test
    public void testGetAllComplaints_returnListOfComplaints(){
        when(complaintService.getAllComplaints()).thenReturn(List.of(complaint1,complaint2));
        complaints=complaintController.allComplaints();
        assertEquals(2,complaints.getBody().size());
        assertEquals(HttpStatus.OK,complaints.getStatusCode());
    }

    @Test
    public void testGetDrinksAndSnacksComplaints_returnListOfComplaints(){
        when(complaintService.getAllDrinksAndSnacksComplaints()).thenReturn(List.of(complaint1));
        complaints=complaintController.drinksAndSnacksComplaints();
        assertEquals(HttpStatus.OK,complaints.getStatusCode());
        assertEquals(1,complaints.getBody().size());

    }

    @Test
    public void testGetHouseKeepingComplaints_returnListOfComplaints(){
        when(complaintService.getAllHouseKeepingComplaints()).thenReturn(List.of(complaint2));
        complaints=complaintController.houseKeepingComplaints();
        assertEquals(HttpStatus.OK,complaints.getStatusCode());
        assertEquals(1,complaints.getBody().size());
    }

    @Test
    public void testMakeComplaint_returnMessage(){
        when(complaintService.makeComplaint(complaint1)).thenReturn(makeComplaintMessage);
        actualMessage=complaintController.makeComplaints(complaint1);
        assertEquals(makeComplaintMessage,actualMessage.getBody());
    }


    @Test
    public void testDeleteComplaint_returnMessage(){
        when(complaintService.resolveComplaint(complaint1.getComplaintId()))
                .thenReturn("Complaint is resolved.. "+complaint1.getComplaintId());
        actualMessage=complaintController.deleteComplaint(complaint1.getComplaintId());
        assertEquals("Complaint is resolved.. "+complaint1.getComplaintId(),actualMessage.getBody());
    }


}
