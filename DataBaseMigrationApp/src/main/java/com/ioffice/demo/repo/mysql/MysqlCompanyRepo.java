package com.ioffice.demo.repo.mysql;

import com.ioffice.demo.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MysqlCompanyRepo
        extends JpaRepository<Company,Integer> {

    @Query(value = "select * from company where company_name=?1",nativeQuery = true)
    List<Company> fetchUsers(String companyName);
}
