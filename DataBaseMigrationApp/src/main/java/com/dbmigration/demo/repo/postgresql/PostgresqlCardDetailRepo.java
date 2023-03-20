package com.dbmigration.demo.repo.postgresql;

import com.dbmigration.demo.model.CardDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresqlCardDetailRepo extends
        JpaRepository<CardDetail,Integer> {
}
