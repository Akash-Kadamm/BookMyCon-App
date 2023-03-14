package com.dbMigration.demo.service;

import com.dbMigration.demo.mysql.MysqlDepartmentRepo;
import com.dbMigration.demo.payload.Department;
import com.dbMigration.demo.postgresql.PostgresqlDepartmentRepo;
import com.dbMigration.demo.utility.ResponseMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class DepartmentService {
    @Autowired
    private MysqlDepartmentRepo mysqlDepartmentRepo;

    @Autowired
    private PostgresqlDepartmentRepo postgresqlDepartmentRepo;

    private static Logger logger = Logger.getLogger(DepartmentService.class);


    /*
     * Fetch department by its id.
     *
     * @param department Id.
     * @return Department
     * */
    public Department getDepartmentById(int departmentId) {
        return mysqlDepartmentRepo.findById(departmentId).get();
    }

    /*
     * Save Department in postgresql database.
     *
     * @param Department
     * @return String message
     *  */
    public ResponseMessage saveDepartment(Department department) {
        postgresqlDepartmentRepo.save(department);
        return ResponseMessage.DEPARTMENT_RECORD_SAVED;
    }

    public Department getDepartmentFromPostgresql(int departmentId) {
        return postgresqlDepartmentRepo.findById(departmentId).get();
    }
}
