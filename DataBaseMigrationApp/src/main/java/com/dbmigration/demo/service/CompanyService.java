package com.dbmigration.demo.service;

import com.dbmigration.demo.model.Company;
import com.dbmigration.demo.repo.mysql.MysqlCompanyRepo;
import com.dbmigration.demo.repo.postgresql.PostgresqlCompanyRepo;
import com.dbmigration.demo.utility.ResponseMessage;
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
    * @param company name
    * @return Company
    * */
    public Company getCompanyByCompanyName(String companyName){
         return mysqlCompanyRepo.fetchCompanyDetails(companyName);
    }

    /*
    * Save Company in Postgresql Database.
    *
    * @param Company
    * @return Company
    * */
    public Company saveCompany(Company company){
        return postgresqlCompanyRepo.save(company);
    }

    /*
    * Fetch All Companies.
    *
    * @param
    * @return List of Company
    * */
    public List<Company> getAllCompany(){
        return mysqlCompanyRepo.findAll();
    }

    /*
    * delete Company by its Id from mysql Database.
    *
    * @param company Id.
    * @return String message
    * */
    public ResponseMessage deleteCompany(int companyId){
        mysqlCompanyRepo.deleteById(companyId);
        return ResponseMessage.COMPANY_RECORD_DELETED;
    }

    /*
    * Fetch company details by id.
    *
    * @param company Id.
    * @return Company
    * */
    public Company getByCompanyId(int companyId){
        return mysqlCompanyRepo.findById(companyId).get();
    }

    /**/
    public Company getCompanyFromPostgresql(int companyId) {
        return postgresqlCompanyRepo.findById(companyId).get();
    }

}
