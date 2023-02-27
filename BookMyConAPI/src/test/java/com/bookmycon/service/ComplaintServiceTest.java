package com.bookmycon.service;

import com.bookmycon.model.Complaint;
import com.bookmycon.repository.ComplaintRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ComplaintServiceTest {

    @Mock
    private ComplaintRepository complaintRepository;
    @InjectMocks
    private ComplaintService complaintService;

    Complaint complaint1;

    Complaint complaint2;



    @Before
    public void setUp(){
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
    public void testGetAllComplaints_getListOfComplaints(){
        when(complaintRepository.findAll()).thenReturn(List.of(complaint1,complaint2));
        List<Complaint> actualComplaints=complaintService.getAllComplaints();
        assertEquals(2,actualComplaints.size());
    }

    @Test
    public void testGetDrinksAndSnacksComplaints_getListOfComplaints(){
        when(complaintRepository.drinksAndSnacksComplaints()).thenReturn(List.of(complaint1));
        List<Complaint> drinksAndSnacksComplaints=complaintService.getAllDrinksAndSnacksComplaints();
        assertEquals(1,drinksAndSnacksComplaints.size());
        assertEquals("drinksAndSnacks",drinksAndSnacksComplaints.get(0).getComplaintType());
    }


    @Test
    public void testGetHouseKeepingComplaints_getListOfComplaints(){
        when(complaintRepository.houseKeepingComplaints()).thenReturn(List.of(complaint2));
        List<Complaint> houseKeepingComplaints=complaintService.getAllHouseKeepingComplaints();
        assertEquals(1,houseKeepingComplaints.size());
        assertEquals("houseKeeping",houseKeepingComplaints.get(0).getComplaintType());
    }

    @Test
    public void testMakeComplaints_getMessage(){
        when(complaintRepository.save(complaint1)).thenReturn(complaint1);
        String actualMessage=complaintService.makeComplaint(complaint1);
        verify(complaintRepository,times(1)).save(complaint1);
        assertEquals("Complaint Send..",actualMessage);
    }

    @Test
    public void testResolveComplaint_() {
        String actualMessage= complaintService.resolveComplaint(complaint1.getComplaintId());
        verify(complaintRepository,times(1)).deleteById(anyInt());
        assertEquals("Complaint is resolved.. "+complaint1.getComplaintId(),actualMessage);
    }

}
