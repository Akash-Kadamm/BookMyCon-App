package com.bookmycon.service;


import com.bookmycon.model.UserLayout;
import com.bookmycon.repository.UserLayoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

@Service
public class UserLayoutService {


    @Autowired
    UserLayoutRepository userLayoutRepository;

    Logger logger=Logger.getLogger(UserLayoutService.class);

    public List<UserLayout> findAll() {
        logger.info("Finding all user layouts...");
        return userLayoutRepository.findAll();
    }

    public Optional<UserLayout> findById(String id) {
        logger.info("Finding user layout with ID " + id);
        return userLayoutRepository.findById(id);
    }

    public void addUserLayout(UserLayout userLayout) {
        logger.info("Adding new user layout: " + userLayout);
        System.out.println(userLayout);
        userLayoutRepository.save(userLayout);
    }

    public Optional<UserLayout> getUserLayoutById(String userLayout) {
        logger.info("Getting user layout with ID " + userLayout);
        return userLayoutRepository.findById(userLayout);
    }

    public void deleteUserLayout(String id) {
        logger.info("Deleting user layout with ID " + id);
        userLayoutRepository.deleteById(id);
    }

    public void editUserLayout(String id, UserLayout userLayout) {
        logger.info("Editing user layout with ID {}"  + id);
        userLayoutRepository.save(userLayout);
    }


}
