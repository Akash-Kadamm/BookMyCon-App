package com.example.demo.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.example.demo.dbMRepo.MysqlAccountRepo;
import com.example.demo.kafka.producer.AccountProducer;
import com.example.demo.payload.Account;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {

	@Mock
	private MysqlAccountRepo mRepo;
	
	@InjectMocks
	private AccountController accountController;
	
	@Mock
	private AccountProducer accountProducer;
	
	private Account account;
	
	private ResponseEntity<String> message;
	
	@Before
	public void setData() {
	  	account = new Account();
	  	account.setCustomerName("Akash");
	  	account.setIsMigrate(true);
	  	account.setId(1);
	  	
	    message.ok("Account record is send to kafka :"+account);
	}

	@Test()
	public void testAddAccount() {
		when(mRepo.save(account)).thenReturn(account);
		
		//Account actualAccount =accountController.addAccount(account);
		ResponseEntity<String> actual=accountController.addAccount(account);
		verify(accountProducer,times(1));
		assertEquals(message, actual);
	}

}
