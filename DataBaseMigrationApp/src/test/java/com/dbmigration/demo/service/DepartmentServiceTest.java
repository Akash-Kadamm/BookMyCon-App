package com.dbmigration.demo.service;

import com.dbmigration.demo.model.Department;
import com.dbmigration.demo.repo.mysql.MysqlDepartmentRepo;
import com.dbmigration.demo.repo.postgresql.PostgresqlDepartmentRepo;
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
        String message=departmentService.saveDepartment(department);
        Assertions.assertThat(message).isEqualTo("department is saved...");
    }
}
