package com.ioffice.demo.repo.postgresql;

import com.ioffice.demo.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgresqlCompanyRepo
        extends JpaRepository<Company,Integer> {
}
