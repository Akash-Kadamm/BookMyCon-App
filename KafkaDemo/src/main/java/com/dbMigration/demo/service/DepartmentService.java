package com.dbMigration.demo.service;

import com.dbMigration.demo.mysql.MysqlDepartmentRepo;
import com.dbMigration.demo.payload.Department;
import com.dbMigration.demo.postgresql.PostgresqlDepartmentRepo;
import com.dbMigration.demo.utility.ResponseMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
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
        Optional<Department> savedDepartment = mysqlDepartmentRepo.findById(departmentId);
        if (savedDepartment.isEmpty()) {
            return null;
        }
        return savedDepartment.get();
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
        Optional<Department> savedDepartment = postgresqlDepartmentRepo.findById(departmentId);
        if (savedDepartment.isEmpty()) {
            return null;
        }
        return savedDepartment.get();
    }

    public String deleteDepartment(int departmentId) {
        postgresqlDepartmentRepo.deleteById(departmentId);
        return ResponseMessage.DEPARTMENT_RECORD_DELETED.getMessage();
    }
}
