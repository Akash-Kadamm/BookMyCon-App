package com.ioffice.demo.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ioffice.demo.model.Account;
import com.ioffice.demo.repo.mysql.MysqlAccountRepo;
import com.ioffice.demo.repo.postgresql.PostgresqlAccountRepo;
import com.ioffice.demo.service.DataBaseMIgrationService;

@RestController
public class AccountController {

	@Autowired
	private MysqlAccountRepo mysqlRepo;
	@Autowired
	private PostgresqlAccountRepo postgresqlRepo;
	
	private static Logger logger=Logger.getLogger(AccountController.class);
	
	@Value("${isMigration.complete}")
	private Boolean canStop;
	
	
	@GetMapping("/mysqlAccount")
	public List<Account> getAllFromMysql(){
		return mysqlRepo.findAll();
	}
	
	@GetMapping("/postgresqlAccount")
	public List<Account> getAllFromPostgresql(){
		return postgresqlRepo.findAll();
	}
	
	
	@PostMapping("/createaccount")
	public void createAccount(@RequestBody Account account) {
		logger.info(" Create new account..."+account);

		logger.info("setting IsMigrated true");
	   DataBaseMIgrationService.setFlag(account);
	 
	   
	   // check until  data is completely migrate or not.
	   // if yes than no need to dump the data in mysql database.
	   if(!canStop) {
		   mysqlRepo.save(account);
		   logger.info("Save in mysql database "+account);
	   }
	   
	   
	   postgresqlRepo.save(account);
	   logger.info("Save in postgresql database "+account);
	   
	   
	}
}
