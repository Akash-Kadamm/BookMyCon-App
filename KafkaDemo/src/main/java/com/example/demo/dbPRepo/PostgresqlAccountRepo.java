package com.example.demo.dbPRepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.payload.Account;



public interface PostgresqlAccountRepo extends
                    JpaRepository<Account, Integer> {

}
