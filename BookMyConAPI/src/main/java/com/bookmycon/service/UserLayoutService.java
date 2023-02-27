package com.bookmycon.service;


import com.bookmycon.model.UserLayout;
import com.bookmycon.repository.UserLayoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserLayoutService {




    @Autowired
    UserLayoutRepository userLayoutRepository;


    public List<UserLayout> findAll() {
        return userLayoutRepository.findAll();
    }

    public Optional<UserLayout> findById(String id) {
        return userLayoutRepository.findById(id);
    }

    public void addUserLayout(UserLayout userLayout) {

        System.out.println(userLayout);
        userLayoutRepository.save(userLayout);
    }

    public Optional<UserLayout> getUserLayoutById(String userLayout) {
        return userLayoutRepository.findById(userLayout);
    }

    public void deleteUserLayout(String id) {
        userLayoutRepository.deleteById(id);
    }

    public void editUserLayout(String id, UserLayout userLayout) {
        userLayoutRepository.save(userLayout);
    }







}
