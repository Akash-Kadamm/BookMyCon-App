package com.dbmigration.demo.controller;

import com.dbmigration.demo.model.Department;
import com.dbmigration.demo.service.DepartmentService;
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
    public ResponseEntity<String> saveDepartment(@RequestBody Department department) {
        return new ResponseEntity<>(
          departmentService.saveDepartment(department),
          HttpStatus.CREATED
        );
    }
}
