package com.dbMigration.demo.postgresql;

import com.dbMigration.demo.payload.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostgresqlAccountRepo extends
                    JpaRepository<Account, Integer> {

}
