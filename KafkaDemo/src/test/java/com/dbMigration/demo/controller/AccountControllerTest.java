package com.dbMigration.demo.controller;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.http.ResponseEntity;

import com.dbMigration.demo.mysql.MysqlAccountRepo;
import com.dbMigration.demo.kafka.producer.AccountProducer;
import com.dbMigration.demo.payload.Account;


public class AccountControllerTest {

	@Mock
	private MysqlAccountRepo mRepo;
	
	@InjectMocks
	private AccountController accountController;
	
	@Mock
	private AccountProducer accountProducer;
	
	private Account account;
	
	private ResponseEntity<String> message;
	

	public void setData() {
	  	account = new Account();
	  	account.setCustomerName("Akash");
	  	account.setIsMigrate(true);
	  	account.setId(1);
	  	
	    message.ok("Account record is send to kafka :"+account);
	}


	public void testAddAccount() {
		when(mRepo.save(account)).thenReturn(account);
		
		//Account actualAccount =accountController.addAccount(account);
		ResponseEntity<String> actual=accountController.addAccount(account);
		verify(accountProducer,times(1));
		//assertEquals(message, actual);
	}

}
