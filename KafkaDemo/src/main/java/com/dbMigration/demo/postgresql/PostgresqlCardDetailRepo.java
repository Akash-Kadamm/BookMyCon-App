package com.dbMigration.demo.postgresql;

import com.dbMigration.demo.payload.CardDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresqlCardDetailRepo extends
        JpaRepository<CardDetail,Integer> {
}
