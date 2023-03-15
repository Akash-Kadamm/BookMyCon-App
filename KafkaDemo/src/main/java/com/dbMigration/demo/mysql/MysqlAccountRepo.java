package com.dbMigration.demo.mysql;

import java.util.List;

import com.dbMigration.demo.payload.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


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
