package com.dbMigration.demo.controller;


import com.dbMigration.demo.payload.Address;
import com.dbMigration.demo.service.AddressService;
import com.dbMigration.demo.utility.ResponseMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    private static Logger logger=Logger.getLogger(AddressController.class);

    @GetMapping("/fetchAddress/{addressId}")
    public ResponseEntity<Address> getAddress(@PathVariable int addressId){
        return new ResponseEntity<>(
                addressService.getAddressById(addressId),
                HttpStatus.OK
        );
    }

    @PostMapping("/saveAddress")
    public ResponseEntity<ResponseMessage> saveAddress(@RequestBody Address address) {
        return new ResponseEntity<>(
                addressService.saveAddress(address),
                HttpStatus.CREATED
        );
    }
}
