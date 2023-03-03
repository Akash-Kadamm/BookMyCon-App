package com.dbmigration.demo.repo.postgresql;

import com.dbmigration.demo.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgresqlDepartmentRepo
        extends JpaRepository<Department,Integer> {
}
