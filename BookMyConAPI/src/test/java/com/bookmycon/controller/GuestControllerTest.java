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
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
//import com.bookmycon.dto.GuestRequestDTO;
//import org.springframework.web.multipart.MultipartFile;
import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class GuestControllerTest {

    @Mock
    GuestService guestService;

    @InjectMocks
    GuestController guestController;

    Map<String , Object> response = new HashMap<>();

    Guest guest;

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

  /*  @Test
    public void testAddGuest() {
        GuestRequestDTO guestRequestDTO = new GuestRequestDTO();
        Guest newGuest = new Guest();
        byte[] thumbnail = new byte[0];
        ResponseEntity<Guest> expectedResponse = new ResponseEntity<>(newGuest, HttpStatus.OK);

        // Mock the guestService.save method
        when(guestService.save(eq(GuestRequestDTO.toEntity(guestRequestDTO)), eq(thumbnail)))
                .thenReturn(newGuest);

        // Call the method to be tested
        ResponseEntity<Guest> actualResponse = guestController.addGuest(guestRequestDTO);

        // Verify that the guestService.save method was called with the expected parameters
        verify(guestService, times(1)).save(eq(GuestRequestDTO.toEntity(guestRequestDTO)), eq(thumbnail));

        // Assert that the actual response matches the expected response
        assertEquals(expectedResponse, actualResponse);
    }*/

//    @Test
//    public void testAddGuest() {
//        GuestRequestDTO guestRequestDTO = new GuestRequestDTO();
//        Guest newGuest = new Guest();
//        MultipartFile thumbnail = new MockMultipartFile("thumbnail", new byte[0]);
//        ResponseEntity<Guest> expectedResponse = new ResponseEntity<>(newGuest, HttpStatus.OK);
//
//        // Mock the guestService.save method
//        when(guestService.save(eq(GuestRequestDTO.toEntity(guestRequestDTO)), eq(thumbnail))
//                .getGuestName());
//
//        // Call the method to be tested
//        ResponseEntity<Guest> actualResponse = guestController.addGuest(guestRequestDTO);
//
//        // Verify that the guestService.save method was called with the expected parameters
//        verify(guestService, times(1)).save(eq(GuestRequestDTO.toEntity(guestRequestDTO)), eq(thumbnail));
//
//        // Assert that the actual response matches the expected response
//        assertEquals(expectedResponse, actualResponse);
//    }
//    @Test
//    public void testGetAllGuest() {
//        // Prepare test data
//    Guest guest1 = new Guest();
//        guest.setGuestId(1);
//        guest.setGuestName("John");
//    Guest guest2 = new Guest();
//        guest2.setGuestId(2);
//        guest2.setGuestName("Jane");
//    List<Guest> guestList = Arrays.asList(guest1, guest2);
//
//    // Mock the behavior of guestService.findAllGuests() to return the guestList
//        Mockito.when(guestService.findAllGuests()).thenReturn(guestList);
//
//    // Call the method being tested
//    ResponseEntity<List<Guest>> response = guestController.getAllGuest();
//
//    // Assert the response
//        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
//        Assert.assertEquals(guestList, response.getBody());
//}

//    @Test
//    public void testGeneratePassFile() throws IOException {
//        // Mocking guest details
//        Guest guest = new Guest();
//        guest.setGuestName("John Doe");
//        guest.setGuestId(30);
//        // Mocking guestService.findByGuestName() method
//        Mockito.when(guestService.findByGuestName(Mockito.anyString())).thenReturn(guest);
//
//        // Mocking HttpServletResponse methods
//        Mockito.doNothing().when(response).setContentType(Mockito.anyString());
//        Mockito.doNothing().when(response).setHeader(Mockito.anyString(), Mockito.anyString());
//        Mockito.doNothing().when(response).getOutputStream();
//
//        // Calling the method under test
//        guestController.generatePassFile(response, "John Doe");
//
//        // Verifying HttpServletResponse methods
//        Mockito.verify(response, Mockito.times(1)).setContentType(Mockito.anyString());
//        Mockito.verify(response, Mockito.times(1)).setHeader(Mockito.anyString(), headerValueCaptor.capture());
//        Mockito.verify(response, Mockito.times(1)).getOutputStream();
//
//        // Asserting header value
//        String headerValue = headerValueCaptor.getValue();
//        Assert.assertNotNull(headerValue);
//        Assert.assertTrue(headerValue.contains("Guest Pass Generation"));
//    }
}










