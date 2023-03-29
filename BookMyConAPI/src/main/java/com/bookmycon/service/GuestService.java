package com.bookmycon.service;

import com.bookmycon.model.Guest;
import com.bookmycon.repository.GuestRepository;
import com.bookmycon.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import org.apache.log4j.Logger;

@Service
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    private StorageService storageService;

    Logger logger=Logger.getLogger(GuestService.class);

    public Guest save(Guest guest, MultipartFile thumbnail) {
        String fileName = storageService.store(thumbnail);
        guest.setThumbnail(fileName);

        return guestRepository.save(guest);
    }

    public List<Guest> findAllGuests() {
        logger.info("Retrieving list of guests!!");
        return guestRepository.findAll();
    }

    public Guest findByGuestName(String name) {
        logger.info("Finding guest by Name: ");
        return guestRepository.findByGuestName(name);
    }

    public List<Guest> getAllGuestByUserId(int userId) {
        logger.info("Get all guest by userId: " + userId);
        return guestRepository.getGuestByUserId(userId);
    }

    public String deleteByGuestId(int guestId) {
        logger.info("Delete guest by using guestId:" + guestId);
        guestRepository.deleteById(guestId);
        return "Guest deleted successfully";
    }

}
