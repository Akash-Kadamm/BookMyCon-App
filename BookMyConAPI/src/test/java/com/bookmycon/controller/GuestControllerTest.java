package com.bookmycon.controller;

import com.bookmycon.dto.GuestRequestDTO;
import com.bookmycon.model.Guest;
import com.bookmycon.model.User;
import com.bookmycon.service.GuestService;
import com.bookmycon.utils.ResponseMessage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import com.bookmycon.dto.GuestRequestDTO;
//import org.springframework.web.multipart.MultipartFile;
import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class GuestControllerTest {

    @Mock
    GuestService guestService;

    @InjectMocks
    GuestController guestController;

    Map<String , Object> response = new HashMap<>();

    Guest guest;

//    GuestRequestDTO guestRequestDTO;
//
//    MultipartFile thumbnail = null;

    @Before
    public void setUp()
    {
        setData();
        setMock();
    }

    private void setData() {
        guest = new Guest (1, "Purvi" , "BMW", "purvi@gmail.com", "9865457852", "e3da01b141884ca0a4d01568551bdf2f",new User(1, "Akash Kadam", "akashkadcybage.com", "ak@12", "user", "7038967693","e3da01b141884ca0a4d01568551bdf2f"));
    }

    private void setMock() {
        when(guestService.save(guest,null)).thenReturn((Guest) response);
        when(guestService.updateGuestProfile(guest)).thenReturn(response);
    }

    @Test
    public void testShowAllGuest()
    {
        List<Guest> list = new ArrayList<>();
        list.add(guest);
        when(guestService.findAllGuests()).thenReturn(list);
        ResponseEntity<List<Guest>> actual = guestController.getAllGuest();
        assertEquals(list,actual.getBody());
    }

    @Test
    public void testUpdateProfile_ConditionGettingGuestNull() {
        response.put("message",ResponseMessage.USER_FAILED_TO_UPDATE_PROFILE.getMessage());
        ResponseEntity<Object> actual=guestController.updateGuestProfile(null);
        Assert.assertEquals(response, actual.getBody());
    }

//    @Test
//    public void testAddGuest() throws Exception {
//        // create a GuestRequestDTO object with some data
//        GuestRequestDTO guestRequestDTO = new GuestRequestDTO();
//        guestRequestDTO.setGuestName("John Doe");
//        guestRequestDTO.setGuestEmail("johndoe@example.com");
//        guestRequestDTO.setThumbnail("thumbnail.png");
//
//        // create a Guest object to return from the guestService.save() method
//        Guest newGuest = new Guest();
//        newGuest.setGuestId(1L);
//        newGuest.setGuestName("John Doe");
//        newGuest.setGuestEmail("johndoe@example.com");
//        newGuest.setThumbnail("thumbnail.png");
//
//        // use Mockito to mock the guestService.save() method
//        when(guestService.save(any(Guest.class), anyString())).thenReturn(newGuest);
//
//        // call the addGuest() method of the guestController
//        ResponseEntity<Guest> responseEntity = guestController.addGuest(guestRequestDTO);
//
//        // verify that the guestService.save() method was called with the correct parameters
//        verify(guestService).save(argThat(guest -> guest.getGuestName().equals("John Doe") && guest.getGuestEmail().equals("johndoe@example.com")), eq("thumbnail.png"));
//
//        // verify that the responseEntity contains the correct data
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(newGuest, responseEntity.getBody());
//    }


    @Test
    public void testAddGuest() {
        GuestRequestDTO guestRequestDTO = new GuestRequestDTO();
        guestRequestDTO.setGuestName("John Doe");
        guestRequestDTO.setGuestEmail("johndoe@example.com");
       // guestRequestDTO.setThumbnail("thumbnail.jpg");

        Guest guest = new Guest();
        guest.setGuestName("John Doe");
        guest.setGuestEmail("johndoe@example.com");
        guest.setThumbnail("thumbnail.jpg");

        when(guestService.save(any(Guest.class), any(org.springframework.web.multipart.MultipartFile.class))).thenReturn(guest);

        ResponseEntity<Guest> response = guestController.addGuest(guestRequestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John Doe", response.getBody().getGuestName());
        assertEquals("johndoe@example.com", response.getBody().getGuestEmail());
        assertEquals("thumbnail.jpg", response.getBody().getThumbnail());

        verify(guestService, times(1)).save(any(Guest.class), any(org.springframework.web.multipart.MultipartFile.class));
    }

//    @Test
//    public void testAddGuest() throws Exception {
//        // Mock the guest request DTO
//        GuestRequestDTO guestRequestDTO = new GuestRequestDTO();
//        guestRequestDTO.setGuestName("John Doe");
//        //guestRequestDTO.setThumbnail(new byte[] {0x01, 0x02, 0x03});
//
//        // Mock the guest entity returned by the guest service
//        Guest guestEntity = new Guest();
//        guestEntity.setGuestId(1L);
//        guestEntity.setGuestName("John Doe");
//        //guestEntity.setThumbnail(new byte[] {0x01, 0x02, 0x03});
//
//        // Mock the guest service's save() method to return the guest entity
//        when(guestService.save()).thenReturn(guestEntity);
//
//        // Call the addGuest() method and check the response
//        ResponseEntity<Guest> response = guestController.addGuest(guestRequestDTO);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(guestEntity, response.getBody());
//
//        // Verify that the guest service's save() method was called with the correct arguments
//        verify(guestService).save(argThat(guest -> guest.getGuestName().equals("John Doe")), eq(new byte[] {0x01, 0x02, 0x03}));
//    }
}
