package com.dbmigration.demo.repo.postgresql;

import com.dbmigration.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresqlAccountRepo extends
                    JpaRepository<Account, Integer> {

}
