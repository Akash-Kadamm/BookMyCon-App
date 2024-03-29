package com.dbMigration.demo.service;


import com.dbMigration.demo.mysql.MysqlCompanyRepo;
import com.dbMigration.demo.payload.Company;
import com.dbMigration.demo.postgresql.PostgresqlCompanyRepo;
import com.dbMigration.demo.utility.ResponseMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    private MysqlCompanyRepo mysqlCompanyRepo;

    @Autowired
    private PostgresqlCompanyRepo postgresqlCompanyRepo;

    private static Logger logger = Logger.getLogger(CompanyService.class);

    /*
     * fetch All Users which belongs to same company.
     *
     * @param company name
     * @return Company
     * */
    public Company getCompanyByCompanyName(String companyName) {
        return mysqlCompanyRepo.fetchCompanyDetails(companyName);
    }

    /*
     * Save Company in Postgresql Database.
     *
     * @param Company
     * @return Company
     * */
    public Company saveCompany(Company company) {
        return postgresqlCompanyRepo.save(company);
    }

    /*
     * Fetch All Companies.
     *
     * @param
     * @return List of Company
     * */
    public List<Company> getAllCompany() {
        return mysqlCompanyRepo.findAll();
    }

    /*
     * delete Company by its Id from mysql Database.
     *
     * @param company Id.
     * @return String message
     * */
    public ResponseMessage deleteCompany(int companyId) {
        mysqlCompanyRepo.deleteById(companyId);
        return ResponseMessage.COMPANY_RECORD_DELETED;
    }

    /*
     * Fetch company details by id.
     *
     * @param company Id.
     * @return Company
     * */
    public Company getByCompanyId(int companyId) {
        Optional<Company> savedCompany = mysqlCompanyRepo.findById(companyId);
        if (savedCompany.isEmpty()) {
            return null;
        }
        return savedCompany.get();
    }

    /**/
    public Company getCompanyFromPostgresql(int companyId) {
        Optional<Company> savedCompany = postgresqlCompanyRepo.findById(companyId);
        if (savedCompany.isEmpty()) {
            return null;
        }
        return savedCompany.get();
    }

}
