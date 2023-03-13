package com.dbMigration.demo.postgresql;

import com.dbMigration.demo.payload.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgresqlAddressRepo
        extends JpaRepository<Address,Integer> {

}
