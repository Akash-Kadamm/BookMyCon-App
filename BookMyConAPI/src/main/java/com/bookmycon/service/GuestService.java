package com.bookmycon.service;

import com.bookmycon.model.Guest;
import com.bookmycon.repository.GuestRepository;
import com.bookmycon.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    private StorageService storageService;

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

}
