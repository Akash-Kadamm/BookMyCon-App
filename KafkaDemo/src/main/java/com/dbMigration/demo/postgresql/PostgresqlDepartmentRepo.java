package com.dbMigration.demo.postgresql;

import com.dbMigration.demo.payload.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgresqlDepartmentRepo
        extends JpaRepository<Department,Integer> {
}
