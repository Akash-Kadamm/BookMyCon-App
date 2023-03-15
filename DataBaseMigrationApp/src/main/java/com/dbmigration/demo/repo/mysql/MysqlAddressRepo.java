package com.dbmigration.demo.repo.mysql;

import com.dbmigration.demo.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MysqlAddressRepo
        extends JpaRepository<Address, Integer> {
}
