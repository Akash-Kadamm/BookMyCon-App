package com.bookmycon.service;

import com.bookmycon.model.Guest;
import com.bookmycon.model.User;
import com.bookmycon.repository.GuestRepository;
import com.bookmycon.utils.ResponseMessage;
import com.bookmycon.utils.StorageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    private StorageService storageService;

    static Logger logger=Logger.getLogger(GuestService.class);

    public Guest save(Guest guest, MultipartFile thumbnail) {
        String fileName = storageService.store(thumbnail);
        guest.setThumbnail(fileName);
        return guestRepository.save(guest);
    }

    public List<Guest> findAllGuests() {
        return guestRepository.findAll();
    }

    public Guest findByGuestName(String name) {
        return guestRepository.findByGuestName(name);
    }

    public List<Guest> getAllGuestByUserId(int userId) {
        return guestRepository.getGuestByUserId(userId);
    }
    public String deleteByGuestId(int guestId) {
        guestRepository.deleteById(guestId);
        return "Guest deleted successfully";
    }

    /*
     * Update Guest Profile
     *
     * @param Guest
     * @return  Map<message,object>
     *
     * */
    public Map<String, Object> updateGuestProfile(Guest guest) {
        Map<String, Object> response = new HashMap<>();

        logger.debug("Geting old Guest by its name ");
        Guest oldGuest=guestRepository.findByGuestName(guest.getGuestName());
        logger.info("old guest object: "+oldGuest);

        logger.info("Setting all values");
        oldGuest.setGuestCompany(guest.getGuestCompany());
        oldGuest.setGuestEmail(guest.getGuestEmail());
        oldGuest.setGuestMobileNo(guest.getGuestMobileNo());
        oldGuest.setThumbnail(guest.getThumbnail());

        Guest newGuest=guestRepository.save(oldGuest);

        if(newGuest!= null) {
            logger.debug("Guest is updated");
            response.put("Guest",newGuest);
        }else {
            logger.error("Failed to update profile");
//            response.put("message", ResponseMessage.USER_FAILED_TO_UPDATE_PROFILE.getMessage());
            response.put("message" , ResponseMessage.USER_FAILED_TO_UPDATE_PROFILE.getMessage());
        }
        return response;

    }
}
