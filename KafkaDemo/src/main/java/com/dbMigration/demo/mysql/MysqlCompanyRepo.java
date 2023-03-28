package com.dbMigration.demo.mysql;

import com.dbMigration.demo.payload.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MysqlCompanyRepo
        extends JpaRepository<Company,Integer> {

    @Query(value = "select * from company where company_name=?1",nativeQuery = true)
    Company fetchCompanyDetails(String companyName);
}
