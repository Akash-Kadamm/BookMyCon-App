package com.dbMigration.demo.controller;

import com.dbMigration.demo.payload.Department;

import com.dbMigration.demo.service.DepartmentService;
import com.dbMigration.demo.utility.ResponseMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    private static Logger logger=Logger.getLogger(DepartmentController.class);

    @GetMapping("/fetchDepartment/{departmentId}")
    public ResponseEntity<Department> getDepartment(@PathVariable int departmentId){
        return new ResponseEntity<>(
                departmentService.getDepartmentById(departmentId),
                HttpStatus.OK
        );
    }

    @PostMapping("/saveDepartment")
    public ResponseEntity<ResponseMessage> saveDepartment(@RequestBody Department department) {
        return new ResponseEntity<>(
                departmentService.saveDepartment(department),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/fetchDepartmentFromNewDb/{departmentId}")
    public ResponseEntity<Department> getDepartmentFromPostgresql(@PathVariable int departmentId){
        return new ResponseEntity<>(
                departmentService.getDepartmentFromPostgresql(departmentId),
                HttpStatus.OK
        );
    }

}
