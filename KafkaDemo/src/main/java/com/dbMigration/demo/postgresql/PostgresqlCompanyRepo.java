package com.dbMigration.demo.postgresql;

import com.dbMigration.demo.payload.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgresqlCompanyRepo
        extends JpaRepository<Company,Integer> {
}
