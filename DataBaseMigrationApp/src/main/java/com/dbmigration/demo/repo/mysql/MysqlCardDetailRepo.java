package com.dbmigration.demo.repo.mysql;

import com.dbmigration.demo.model.CardDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MysqlCardDetailRepo extends
        JpaRepository<CardDetail,Integer> {
}
