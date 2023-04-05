package com.dbMigration.demo.mysql;

import com.dbMigration.demo.payload.CardDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MysqlCardDetailRepo extends
        JpaRepository<CardDetail,Integer> {
}
