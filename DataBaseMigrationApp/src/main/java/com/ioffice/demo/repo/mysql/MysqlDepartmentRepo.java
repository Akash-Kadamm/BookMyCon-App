package com.ioffice.demo.repo.mysql;

import com.ioffice.demo.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MysqlDepartmentRepo
        extends JpaRepository<Department,Integer> {
}
