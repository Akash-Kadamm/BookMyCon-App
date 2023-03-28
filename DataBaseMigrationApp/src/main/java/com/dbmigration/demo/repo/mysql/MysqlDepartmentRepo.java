package com.dbmigration.demo.repo.mysql;

import com.dbmigration.demo.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MysqlDepartmentRepo
        extends JpaRepository<Department,Integer> {
}
