package com.ioffice.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ioffice.demo.model.Account;
import com.ioffice.demo.repo.mysql.MysqlAccountRepo;
import com.ioffice.demo.repo.postgresql.PostgresqlAccountRepo;

@SpringBootApplication
public class DataBaseMigrationAppApplication   {
	

	public static void main(String[] args) {
		SpringApplication.run(DataBaseMigrationAppApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		
//		List<Account> accounts=mysqlRepo.findAll();
//		
//		postgresqlRepo.saveAll(accounts);
//		
//		System.out.println("migration is  Done....");
//		
//	}

}
