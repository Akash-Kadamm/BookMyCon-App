package com.ioffice.demo.repo.postgresql;

import com.ioffice.demo.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgresqlAddressRepo
        extends JpaRepository<Address,Integer> {

}
