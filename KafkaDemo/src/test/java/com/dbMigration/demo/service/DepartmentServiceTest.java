package com.dbMigration.demo.service;


import com.dbMigration.demo.mysql.MysqlDepartmentRepo;
import com.dbMigration.demo.payload.Department;
import com.dbMigration.demo.postgresql.PostgresqlDepartmentRepo;
import com.dbMigration.demo.utility.ResponseMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    private MysqlDepartmentRepo mysqlDepartmentRepo;

    @Mock
    private PostgresqlDepartmentRepo postgresqlDepartmentRepo;

    @InjectMocks
    private DepartmentService departmentService;

    Department department;

    @BeforeEach
    public void setUp(){
        department=Department.builder()
                .departmentId(1)
                .departmentName("QA")
                .build();
    }

    @Test
    @DisplayName("Test for fetch department by departmentId")
    public void givenDepartmentId_whenGetDepartmentById_thanDepartmentObject(){
        BDDMockito.given(mysqlDepartmentRepo.findById(BDDMockito.anyInt())).willReturn(Optional.of(department));
        Department savedDepartment=departmentService.getDepartmentById(1);
        Assertions.assertThat(savedDepartment.getDepartmentId()).isEqualTo(1);
        Assertions.assertThat(savedDepartment.getDepartmentName()).isEqualTo("QA");
    }

    @Test
    @DisplayName("Test for save department")
    public void givenDepartmentObject_whenSaveDepartment_thanDepartmentObject(){
        BDDMockito.given(postgresqlDepartmentRepo.save(BDDMockito.any(Department.class))).willReturn(department);
        ResponseMessage message=departmentService.saveDepartment(department);
        Assertions.assertThat(message).isEqualTo(ResponseMessage.DEPARTMENT_RECORD_SAVED);
    }
    @Test
    @DisplayName("Test for Department from postgresql Database.")
    public void givenDepartmentId_whenGetDepartmentFromPostgresql_thanReturnDepartment(){
        BDDMockito.given(postgresqlDepartmentRepo.findById(BDDMockito.anyInt())).willReturn(Optional.of(department));
        Department savedDepartment=departmentService.getDepartmentFromPostgresql(department.getDepartmentId());
        Assertions.assertThat(savedDepartment.getDepartmentId()).isEqualTo(1);
        Assertions.assertThat(savedDepartment.getDepartmentName()).isEqualTo("QA");
    }

}
