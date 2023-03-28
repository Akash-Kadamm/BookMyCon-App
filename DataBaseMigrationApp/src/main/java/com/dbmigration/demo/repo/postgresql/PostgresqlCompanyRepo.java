package com.dbmigration.demo.repo.postgresql;

import com.dbmigration.demo.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgresqlCompanyRepo
        extends JpaRepository<Company,Integer> {
}
