package com.example.demo.service;

import com.example.demo.dbMRepo.MysqlAccountRepo;
import com.example.demo.dbPRepo.PostgresqlAccountRepo;
import com.example.demo.payload.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private MysqlAccountRepo mysqlAccountRepo;
    @Autowired
    private PostgresqlAccountRepo postgresqlAccountRepo;

    public List<Account> getAllAccountsToBeMigrate(){
     return mysqlAccountRepo.getAllAccountsForMigration();
    }

    public void saveAccountInMysqlDataBase(Account account){
        mysqlAccountRepo.save(account);
    }

    public void saveAccountInPostgresqlDataBase(Account account){
        postgresqlAccountRepo.save(account);
    }

}
