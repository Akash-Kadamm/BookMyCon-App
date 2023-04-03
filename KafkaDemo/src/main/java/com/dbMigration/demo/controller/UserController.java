package com.dbMigration.demo.controller;

import com.dbMigration.demo.dbMigrationservice.UserMigrationService;
import com.dbMigration.demo.dto.UserDetailsDTO;
import com.dbMigration.demo.payload.User;
import com.dbMigration.demo.service.UserService;
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
    public ResponseEntity<List<User>> fetchAllUsers(){
        return new ResponseEntity<>(
                userService.getAllUsers(),
                HttpStatus.OK
        );
    }

    @GetMapping("/getUser/{userId}")
    public ResponseEntity<User> getUserByUserId(@PathVariable int userId){
        return new ResponseEntity<>(
                userService.getUserByUserId(userId),
                HttpStatus.OK
        );
    }

    @GetMapping("/migrateUser/{userId}")
    public ResponseEntity<String> migrateUserByUserId(@PathVariable int userId){
        return new ResponseEntity<>(
                userMigrationService.userMigrationServiceByUserId(userId),
                HttpStatus.OK
        );
    }

    @GetMapping("/getUserToBeMigrateByCompanyName/{companyId}")
    public ResponseEntity<List<User>> getUserToBeMigrateByCompanyName(@PathVariable int companyId){
        return new ResponseEntity<>(
                userService.getAllUsersByCompanyId(companyId),
                HttpStatus.OK
        );
    }

    @GetMapping("/migrateUsers/{companyName}")
    public ResponseEntity<String> migrateUserByCompanyName(@PathVariable String companyName){
        return  new ResponseEntity<>(
                userMigrationService.migrationServiceByCompanyName(companyName),
                HttpStatus.OK
        );
    }

    @GetMapping("fetchAllDetailsOfUser/{userId}")
    public ResponseEntity<UserDetailsDTO> getAllDetailsOfUser(@PathVariable int userId) {
        return new ResponseEntity<>(
                userMigrationService.fetchAllUserDetails(userId),
                HttpStatus.OK
        );
    }

    @DeleteMapping("deleteAllDetailsOfUser/{userId}")
    public ResponseEntity<String> deleteUserDetails(@PathVariable int userId){
        return new ResponseEntity<>(
                userMigrationService.deleteAllDetailsOfUser(userId),
                HttpStatus.OK
        );
    }


}
