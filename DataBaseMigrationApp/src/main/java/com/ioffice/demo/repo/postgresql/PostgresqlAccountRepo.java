package com.ioffice.demo.repo.postgresql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ioffice.demo.model.Account;

public interface PostgresqlAccountRepo extends
                    JpaRepository<Account, Integer> {

}
