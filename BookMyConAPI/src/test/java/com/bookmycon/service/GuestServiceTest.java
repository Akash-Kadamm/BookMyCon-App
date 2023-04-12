package com.bookmycon.service;

import com.bookmycon.model.Guest;
import com.bookmycon.model.User;
import com.bookmycon.repository.GuestRepository;
import com.bookmycon.utils.ResponseMessage;
import com.bookmycon.utils.StorageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GuestServiceTest {

    @Mock
    GuestRepository guestRepository;

    @InjectMocks
    GuestService guestService;

    @Mock
    StorageService storageService;

    Guest guest;

    MultipartFile thumbnail;

    @Before
    public void setUp() {
        guest = new Guest(1, "Purvi", "BMW", "purvi@gmail.com", "9865457852", "e3da01b141884ca0a4d01568551bdf2f", new User(1, "Akash Kadam", "akashkadcybage.com", "ak@12", "user", "7038967693", "e3da01b141884ca0a4d01568551bdf2f"));

    }

    @Test
    public void testShowAllGuest() {
        List<Guest> guestList = new ArrayList<>();
        Guest guest1 = new Guest(1, "Purvi", "BMW", "purvi@gmail.com", "9865457852", "e3da01b141884ca0a4d01568551bdf2f", new User(1, "Akash Kadam", "akashkadcybage.com", "ak@12", "user", "7038967693", "e3da01b141884ca0a4d01568551bdf2f"));
        guestList.add(guest);
        guestList.add(guest1);
        when(guestRepository.findAll()).thenReturn(guestList);
        List<Guest> actual = guestService.findAllGuests();
        assertEquals(guestList, actual);
    }

    @Test(expected = Exception.class)
    public void testShowAllGuest_ConditionException() {
        when(guestRepository.findAll()).thenReturn(null);
        List<Guest> actual = guestService.findAllGuests();
        actual.size();
    }

    @Test
    public void testUpdateGuestProfile_ConditionUpdated() {
        when(guestRepository.findByGuestName(guest.getGuestName())).thenReturn(guest);
        System.out.println(guest);
        when(guestRepository.save(guest)).thenReturn(guest);
        Map<String, Object> actual = guestService.updateGuestProfile(guest);
//        assertEquals(guest, actual.get("guest"));
    }

    @Test
    public void testUpdateGuestProfile_ConditionFailedToUpdated() {
        when(guestRepository.findByGuestName(guest.getGuestName())).thenReturn(guest);
        when(guestRepository.save(guest)).thenReturn(null);
        Map<String, Object> actual = guestService.updateGuestProfile(guest);
        assertEquals(ResponseMessage.USER_FAILED_TO_UPDATE_PROFILE.getMessage(), actual.get("message"));
    }

    @Test
    public void testSave_ThanReturnGuest() {
        thumbnail=null;
        when(storageService.store(thumbnail)).thenReturn("asfgausgfgs");
        when(guestRepository.save(ArgumentMatchers.any(Guest.class))).thenReturn(guest);
        Guest actualGuest=guestService.save(guest,thumbnail);
        assertEquals(guest.getGuestName(),actualGuest.getGuestName());
    }

    @Test
    public void testFindByGuestName() {
        when(guestRepository.findByGuestName(ArgumentMatchers.anyString())).thenReturn(guest);
        Guest actualGuest=guestService.findByGuestName("Purvi");
        assertEquals(guest.getGuestName(),actualGuest.getGuestName());
    }

    @Test
    public void testGetAllGuestByUserId() {
        int userId=1;
        when(guestRepository.getGuestByUserId(ArgumentMatchers.anyInt())).thenReturn(List.of(guest));
        List<Guest> actualGuest=guestService.getAllGuestByUserId(userId);
        assertEquals(1,actualGuest.size());
    }

    @Test
    public void testDeleteByGuestId() {
        int userId=1;
        String message="Guest deleted successfully";
       String actualMessage= guestService.deleteByGuestId(userId);
        verify(guestRepository,times(1)).deleteById(userId);
        assertEquals(message,actualMessage);
    }
}



