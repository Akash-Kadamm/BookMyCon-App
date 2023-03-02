package com.ioffice.demo.repo.mysql;

import com.ioffice.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MysqlUserRepo
        extends JpaRepository<User,Integer> {

    @Query(value = "select * from users " +
            "inner join company on" +
            " users.company = company.company_id " +
            "where  company_name=?1",
            nativeQuery = true)
    List<User> getAllUsersByCompanyName(String companyName);

}
