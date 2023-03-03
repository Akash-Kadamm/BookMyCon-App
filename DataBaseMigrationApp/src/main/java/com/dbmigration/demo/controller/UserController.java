package com.dbmigration.demo.controller;

import com.dbmigration.demo.migrationservice.UserMigrationService;
import com.dbmigration.demo.model.User;
import com.dbmigration.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMigrationService userMigrationService;

    @GetMapping("/getAllUsers")
    private ResponseEntity<List<User>> fetchAllUsers(){
        return new ResponseEntity<>(
                userService.getAllUsers(),
                HttpStatus.OK
        );
    }

    @GetMapping("/getUser/{userId}")
    private ResponseEntity<User> getUserByUserId(@PathVariable int userId){
        return new ResponseEntity<>(
          userService.getUserByUserId(userId),
          HttpStatus.OK
        );
    }

    @GetMapping("/migrateUser/{userId}")
    private ResponseEntity<String> migrateUserByUserId(@PathVariable int userId){
        return new ResponseEntity<>(
          userMigrationService.userMigrationServiceByUserId(userId),
          HttpStatus.OK
        );
    }
}
