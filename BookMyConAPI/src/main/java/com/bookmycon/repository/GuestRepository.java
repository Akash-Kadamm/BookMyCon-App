package com.bookmycon.repository;

import com.bookmycon.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Integer> {

    Guest findByGuestName(String name);

    Guest deleteByGuestId(int guestId);

    @Query(nativeQuery = true, value = "select * from guest where user_id=?1")
    List<Guest> getGuestByUserId(int userId);
}
