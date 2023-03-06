package com.dbmigration.demo.service;

import com.dbmigration.demo.model.Department;
import com.dbmigration.demo.repo.mysql.MysqlDepartmentRepo;
import com.dbmigration.demo.repo.postgresql.PostgresqlDepartmentRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    private MysqlDepartmentRepo mysqlDepartmentRepo;

    @Autowired
    private PostgresqlDepartmentRepo postgresqlDepartmentRepo;

    private static Logger logger=Logger.getLogger(DepartmentService.class);


    /*
    * Fetch department by its id.
    * */
    public Department getDepartmentById(int departmentId){
        return mysqlDepartmentRepo.findById(departmentId).get();
    }

    /*
    * Save Department in postgresql database.
    *  */
    public String saveDepartment(Department department){
        postgresqlDepartmentRepo.save(department);
        return  "department is saved...";
    }
}
