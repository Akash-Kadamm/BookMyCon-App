package com.ioffice.demo.service;

import com.ioffice.demo.model.User;
import com.ioffice.demo.repo.mysql.MysqlUserRepo;
import com.ioffice.demo.repo.postgresql.PostgresqlUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private MysqlUserRepo mysqlUserRepo;
    @Autowired
    private PostgresqlUserRepo postgresqlUserRepo;

    public List<User> getAllUsers(){
     return  mysqlUserRepo.findAll();
    }

    public List<User> getAllUsersByCompany(String companyName){
        return  mysqlUserRepo.getAllUsersByCompanyName(companyName);
    }
}
