package com.ioffice.demo.service;

import com.ioffice.demo.model.Company;
import com.ioffice.demo.model.User;
import com.ioffice.demo.repo.mysql.MysqlCompanyRepo;
import com.ioffice.demo.repo.postgresql.PostgresqlCompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private MysqlCompanyRepo mysqlCompanyRepo;

    @Autowired
    private PostgresqlCompanyRepo postgresqlCompanyRepo;


    public List<Company> getAllUsersToBeMigrate(String companyName){
       return mysqlCompanyRepo.fetchUsers(companyName);
    }

}
