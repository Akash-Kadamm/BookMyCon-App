package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dbMRepo.MysqlAccountRepo;
import com.example.demo.kafka.producer.AccountProducer;
import com.example.demo.payload.Account;


@RestController
@RequestMapping("/Account")
@CrossOrigin("*")
public class AccountController {

	
	private static final Logger logger= LoggerFactory.getLogger(AccountController.class);
    
    @Autowired
    private AccountProducer accountProducer;
    
    @Autowired
    private MysqlAccountRepo mRepo;

 
	/*
	 * Creating the new Account record and save that record in Mysql database 
	 * also send the kafka message 
	 * 
	 * @param Account 
	 * 
	 * @return Response Entity with message.
	 * 
	 * */
    @PostMapping("/addAccount")
	public ResponseEntity<String> addAccount(@RequestBody Account account){
		
    	account.setIsMigrate(true);
    	mRepo.save(account);
    	accountProducer.sendRecord(account);
		
		return ResponseEntity.ok("Account record is send to kafka :"+account);
	}
	
	

}
