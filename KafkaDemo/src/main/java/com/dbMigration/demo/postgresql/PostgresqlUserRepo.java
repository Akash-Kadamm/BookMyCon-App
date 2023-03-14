package com.dbMigration.demo.postgresql;

import com.dbMigration.demo.payload.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgresqlUserRepo
        extends JpaRepository<User,Integer> {
}
