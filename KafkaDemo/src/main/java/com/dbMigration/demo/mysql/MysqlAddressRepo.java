package com.dbMigration.demo.mysql;

import com.dbMigration.demo.payload.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MysqlAddressRepo
        extends JpaRepository<Address, Integer> {
}
