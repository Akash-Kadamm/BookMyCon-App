package com.dbMigration.demo.mysql;

import com.dbMigration.demo.payload.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MysqlDepartmentRepo
        extends JpaRepository<Department,Integer> {
}
