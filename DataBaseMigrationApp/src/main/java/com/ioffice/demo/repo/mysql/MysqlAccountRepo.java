package com.ioffice.demo.repo.mysql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ioffice.demo.model.Account;

public interface MysqlAccountRepo extends 
                 JpaRepository<Account, Integer> {
	
	/*
	 * Retrieve Accounts record from mysql database which not migrate to postgresql.
	 * 
	 * @param
	 * @return List
	 * */
	@Query(value = "select * from accounts where is_migrate=0",nativeQuery = true)
	public List<Account> getAllAccountsForMigration();

}
