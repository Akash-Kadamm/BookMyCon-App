package com.dbmigration.demo.controller;

import java.util.List;

import com.dbmigration.demo.model.Account;
import com.dbmigration.demo.service.AccountService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin("*")
@RequestMapping("/Account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Value("${isMigration.complete}")
	private boolean canStop;
	private static Logger logger=Logger.getLogger(AccountController.class);

	/*
	 * Retrieve all Accounts from Mysql database 
	 * 
	 * @param
	 * @return List 
	 * 
	 * */
	@GetMapping("/mysqlAccount")
	public ResponseEntity<List<Account>> getAllAccountsFromMysql(){
		return new ResponseEntity<>(
				accountService.getAllAccountsFromMysqlDataBase(),
				HttpStatus.OK
		);
	}
	
	/*
	 * Retrieve all Accounts from Postgresql database 
	 * 
	 * @param
	 * @return List 
	 * 
	 * */
	@GetMapping("/postgresqlAccount")
	public ResponseEntity<List<Account>> getAllAccountsFromPostgresql() {
		return new ResponseEntity<>(
		accountService.getAllAccountsFromPostgresqlDataBase(),
		HttpStatus.OK
		);
	}

	/*
	 * Create New Account record and dump into both database.
	 * 
	 * @param Account
	 * @return 
	 * 
	 * */
	@PostMapping("/createAccount")
	public ResponseEntity<String> createAccount(@RequestBody Account account,boolean canStop) {
      return new ResponseEntity<>(
		accountService.createAccount(account,canStop),
		HttpStatus.CREATED
	  );
	}
}
