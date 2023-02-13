package com.ioffice.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ioffice.demo.model.Account;
import com.ioffice.demo.repo.mysql.MysqlAccountRepo;
import com.ioffice.demo.repo.postgresql.PostgresqlAccountRepo;

class AccountControllerTest {
	
	@InjectMocks
	private AccountController accountController;

	private MysqlAccountRepo mysqlRepo;
	
	@Mock
	private PostgresqlAccountRepo postgresqlRepo;
	
	private List<Account> accounts= new ArrayList<>();
	
	
	@BeforeEach
	void setUp() throws Exception {
		mysqlRepo=mock(MysqlAccountRepo.class);
	   Account account1=new Account(1, "Akash Kadam", false);
		accounts.add(account1);
	}

	@Test
	void testGetAllFromMysql() {
	  when(mysqlRepo.findAll()).thenReturn(accounts);
	  List<Account> actual= accountController.getAllFromMysql();
	  assertEquals(accounts.size(), actual.size());
	  
	}


}
