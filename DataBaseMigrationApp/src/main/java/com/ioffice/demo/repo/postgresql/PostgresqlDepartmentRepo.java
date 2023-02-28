package com.ioffice.demo.repo.postgresql;

import com.ioffice.demo.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgresqlDepartmentRepo
        extends JpaRepository<Department,Integer> {
}
