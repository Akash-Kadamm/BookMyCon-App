package com.example.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.dbMRepo.MysqlAccountRepo;
import com.example.demo.kafka.producer.AccountProducer;
import com.example.demo.payload.Account;

@Service
@EnableScheduling
public class MigrationService {
	
	private static final Logger logger= LoggerFactory.getLogger(MigrationService.class);
	

	@Autowired
	private AccountService accountService;

	@Autowired
	private AccountProducer accountProducer;
	
	
	/*
	 * Migration Service is responsible for migrating older data in postgresql database.
	 * set migrated value is true and save the record in mysql DataBase.
	 * Kafka message is send.
	 * 
	 * @param
	 * @return 
	 * 
	 * */
	@Scheduled(cron = "5 * * ? * *")
    public void migrationService() {
		logger.info("Migration Service is Executed.....");
		List<Account> accounts= accountService.getAllAccountsToBeMigrate();
        accounts.stream()
        .forEach((account) ->{
        	logger.info("Migrating Account Record :"+account);
        	account.setIsMigrate(true);
			accountService.saveAccountInMysqlDataBase(account);
        	accountProducer.sendRecord(account);
        	logger.info("Kafka Message is send.....");
        } );
	}
}
