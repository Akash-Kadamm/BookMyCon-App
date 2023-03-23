package com.bookmycon.repository;

import com.bookmycon.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bookmycon.model.User;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserId(int id);

}
