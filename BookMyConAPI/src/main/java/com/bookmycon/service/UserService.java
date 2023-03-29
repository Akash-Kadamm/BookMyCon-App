package com.bookmycon.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookmycon.utils.StorageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import com.bookmycon.model.User;
import com.bookmycon.repository.LoginRepository;
import com.bookmycon.repository.UserRepository;
import com.bookmycon.utils.ResponseMessage;
import org.springframework.web.multipart.MultipartFile;

@CacheConfig(cacheNames = "users")
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginRepository loginRepo;

    static Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    private StorageService storageService;

    @Autowired
    private CacheManager cacheManager;


    /*
     * Register new user
     *
     * @param User
     * @return Map<message,object>
     * @throws generalize Exception
     *
     * */
    public Map<String, Object> userRegistration(User user, MultipartFile thumbnail) {
        Map<String, Object> response = new HashMap<>();
        try {
            logger.debug("User register");
            logger.info("user object:" + user);
            String fileName = storageService.store(thumbnail);
            user.setThumbnail(fileName);
            cacheManager.getCache("users").clear();
            User result = userRepository.save(user);
            response.put("user", result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Failed to register");
            response.put("message", ResponseMessage.USER_ADDED_FAILED.getMessage());
        }
        return response;

    }


    /*
     * Retrieves all users which are register
     *
     * @param
     * @return List of users
     * @throws SQLException
     *
     * */
    @Cacheable("users")
    public List<User> showAllUser() {
        List<User> users = new ArrayList<>();
        try {
            logger.debug("Retrieve all users ");
            users = userRepository.findAll();
            return users;
        } catch (Exception e) {
            logger.error("Failed to retrieve all users");
            e.printStackTrace();
        }
        return users;
    }


    /*
     * Update User Profile
     *
     * @param User
     * @return  Map<message,object>
     *
     * */
    @CachePut(value = "users",key = "#user.userId")
    public Map<String, Object> updateUserProfile(User user) {
        Map<String, Object> response = new HashMap<>();
        logger.info("Clearing old cache.");
        cacheManager.getCache("users").clear();
        /*cacheManager.getCacheNames()
                .parallelStream()
                .forEach(n -> cacheManager.getCache(n).clear());
        */
        logger.debug("Geting old usre by its id ");
        User oldUser = userRepository.findByUserId(user.getUserId());
        logger.info("old user object: " + oldUser);
        logger.info("Setting all values");
        oldUser.setUserContact(user.getUserContact());
        oldUser.setUserName(user.getUserName());
        oldUser.setUserPassword(user.getUserPassword());
        logger.info("Updating user in database.");
        User newUser = userRepository.save(oldUser);
        if (newUser != null) {
            logger.debug("User is updated");
            response.put("user", newUser);
        } else {
            logger.error("Failed to update profile");
            response.put("message", ResponseMessage.USER_FAILED_TO_UPDATE_PROFILE.getMessage());
        }
        return response;
    }

    public User findByUserEmail(String email) {
        logger.info("User from data base.");
        return loginRepo.findByUserEmail(email);
    }

    @Cacheable("users")
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            logger.debug("Retrieve all users ");
            logger.info("getting users from database.");
            users = userRepository.findAll();
            return users;
        } catch (Exception e) {
            logger.error("Failed to retrieve all users");
            e.printStackTrace();
        }
        return users;
    }

}
