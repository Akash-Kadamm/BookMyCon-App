package com.example.demo.dbMRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.payload.Account;


public interface MysqlAccountRepo extends 
                 JpaRepository<Account, Integer> {
	
	
	@Query(value = "select * from accounts where is_migrate=0",nativeQuery = true)
	public List<Account> getAllAccountsForMigration();

}
