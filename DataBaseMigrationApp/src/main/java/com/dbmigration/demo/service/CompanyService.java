package com.dbmigration.demo.service;

import com.dbmigration.demo.model.Company;
import com.dbmigration.demo.repo.mysql.MysqlCompanyRepo;
import com.dbmigration.demo.repo.postgresql.PostgresqlCompanyRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private MysqlCompanyRepo mysqlCompanyRepo;

    @Autowired
    private PostgresqlCompanyRepo postgresqlCompanyRepo;

    private static Logger logger=Logger.getLogger(CompanyService.class);

    /*
    * fetch All Users which belongs to same company.
    *
    * */
    public List<Company> getAllUsersToBeMigrate(String companyName){
         return mysqlCompanyRepo.fetchUsers(companyName);
    }

    /*
    * Save Company in Postgresql Database.
    * */
    public Company saveCompany(Company company){
        return postgresqlCompanyRepo.save(company);
    }

    /*
    * Fetch All Companies.
    * */
    public List<Company> getAllCompany(){
        return mysqlCompanyRepo.findAll();
    }

    /*
    * delete Company by its Id from mysql Database.♠
    * */
    public String deleteCompany(int companyId){
        mysqlCompanyRepo.deleteById(companyId);
        return "Company is deleted from record....";
    }
}
