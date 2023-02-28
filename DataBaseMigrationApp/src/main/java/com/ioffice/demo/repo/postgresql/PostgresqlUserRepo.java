package com.ioffice.demo.repo.postgresql;

import com.ioffice.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgresqlUserRepo
        extends JpaRepository<User,Integer> {
}
