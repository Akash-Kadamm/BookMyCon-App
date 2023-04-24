package com.bookmycon.controller;

import com.bookmycon.dto.GuestRequestDTO;
import com.bookmycon.model.Guest;
import com.bookmycon.model.User;
import com.bookmycon.repository.GuestRepository;
import com.bookmycon.repository.UserRepository;
import com.bookmycon.service.GuestService;
import com.bookmycon.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class GuestControllerTest {

    @Mock
    GuestService guestService;

    @InjectMocks
    GuestController guestController;

    @Mock
    GuestRepository guestRepository;
    @Mock
    UserRepository userRepository;

    GuestRequestDTO guestRequestDTO;

    Map<String, Object> response = new HashMap<>();

    Guest guest;

    User user;
    MultipartFile thumbnail = null;
    HttpServletResponse servletResponse;

    @Before
    public void setUp() {
        setData();
        setMock();
    }

    private void setData() {
        guest = new Guest(1, "Purvi", "BMW", "purvi@gmail.com", "9865457852", "e3da01b141884ca0a4d01568551bdf2f", new User(1, "Akash Kadam", "akashkadcybage.com", "ak@12", "user", "7038967693", "e3da01b141884ca0a4d01568551bdf2f"));
        guestRequestDTO = new GuestRequestDTO(1, "Akash Kadam", "akashkad@cybage.com", "ak@123", "user", "7038967693", thumbnail, user);
    }

    private void setMock() {
        when(guestService.updateGuestProfile(guest)).thenReturn(response);
        when(guestRepository.save(guest)).thenReturn(guest);
    }


    @Test
    public void testGetAllGuests() {
        when(guestService.findAllGuests()).thenReturn(List.of(guest));
        ResponseEntity<?> actualResult = guestController.getAllGuest();
        assertEquals(HttpStatus.OK, actualResult.getStatusCode());
    }

    @Test
    public void testGetAllGuestByUserId() {
        User user = new User();
        user.setUserId(1);

        List<Guest> guests = new ArrayList<>();
        Guest guest1 = new Guest();
        guest1.setGuestId(1);
        guest1.setGuestName("John");
        guest1.setUsers(user);
        guests.add(guest1);

        Guest guest2 = new Guest();
        guest2.setGuestId(2);
        guest2.setGuestName("Jane");
        guest2.setUsers(user);
        guests.add(guest2);

        when(userRepository.findByUserId(1)).thenReturn(user);
        when(guestService.getAllGuestByUserId(1)).thenReturn(guests);

        ResponseEntity<List<Guest>> responseEntity = guestController.getAllGuest(1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
        assertEquals("John", responseEntity.getBody().get(0).getGuestName());
        assertEquals("Jane", responseEntity.getBody().get(1).getGuestName());
    }


    @Test(expected = Exception.class)
    public void testGeneratePassFile_withInvalidName() throws Exception {
        String name = "prerana";
        when(guestService.findByGuestName(guest.getGuestName())).thenReturn(null);
        guestController.generatePassFile(servletResponse, name);
    }

    @Test
    public void testGetGuestByName() {
        when(guestService.findByGuestName(anyString())).thenReturn(guest);
        ResponseEntity<?> actualguest = guestController.getGuestByName("Purvi");
        assertEquals(HttpStatus.OK, actualguest.getStatusCode());
    }

    @Test
    public void testGetAllGuest() {
        when(guestService.findAllGuests()).thenReturn(List.of(guest));
        ResponseEntity<?> actualResult=guestController.getAllGuest();
        assertEquals(HttpStatus.OK,actualResult.getStatusCode());
    }

    @Test
    public void testDeleteGuest() {

        int guestId = 1;
        when(guestService.deleteByGuestId(guestId)).thenReturn("guest is deleted");
        ResponseEntity<String> response = guestController.deleteGuest(guestId);
        verify(guestService, times(1)).deleteByGuestId(guestId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Guest deleted", response.getBody());
    }

}










