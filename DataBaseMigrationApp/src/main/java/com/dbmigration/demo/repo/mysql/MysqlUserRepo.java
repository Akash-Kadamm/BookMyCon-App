package com.dbmigration.demo.repo.mysql;

import com.dbmigration.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MysqlUserRepo
        extends JpaRepository<User,Integer> {

    @Query(value = "select * from users where company_id=?1 and is_migrate=0",
            nativeQuery = true)
     List<User> fetchUsersByCompanyId(int companyId);
}
