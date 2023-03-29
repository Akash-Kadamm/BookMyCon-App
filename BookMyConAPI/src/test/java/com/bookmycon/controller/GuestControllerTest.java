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
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

    GuestRequestDTO guestRequestDTO;

    MultipartFile thumbnail = null;

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

//    @Test
//    public void testUpdateProfile_ConditionGuestUpdateSuccessfully() {
//        response.put("message", ResponseMessage.USER_UPDATED_SUCCESSFULLY.getMessage());
//        ResponseEntity<Object> actual=guestController.updateGuestProfile(guest.getGuestName());
//        assertEquals(response, actual.getBody());
//        Assert.assertEquals(response, actual.getBody());
//    }

    @Test
    public void testUpdateProfile_ConditionGettingGuestNull() {
        response.put("message",ResponseMessage.USER_FAILED_TO_UPDATE_PROFILE.getMessage());
        ResponseEntity<Object> actual=guestController.updateGuestProfile(null);
        Assert.assertEquals(response, actual.getBody());
    }
}
