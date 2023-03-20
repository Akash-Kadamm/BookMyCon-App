package com.dbmigration.demo.controller;

import com.dbmigration.demo.model.Department;
import com.dbmigration.demo.service.DepartmentService;
import com.dbmigration.demo.utility.ResponseMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
public class DepartmentControllerTest {

    @MockBean
    private DepartmentService departmentService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    Department department;

    @BeforeEach
    public void setUp() {
        department = Department.builder()
                .departmentId(1)
                .departmentName("QA")
                .build();
    }

    @Test
    @DisplayName("Test for get Department By departmentId.")
    public void givenDepartmentId_whenGetDepartmentByDepartmentId_thanReturnDepartment() throws Exception {
        BDDMockito.given(departmentService.getDepartmentById(BDDMockito.anyInt())).willReturn(department);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/department/fetchDepartment/{departmentId}", department.getDepartmentId()));

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentId",
                        CoreMatchers.is(department.getDepartmentId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentName",
                        CoreMatchers.is(department.getDepartmentName())));
    }

    @Test
    @DisplayName("Test for Save Department in postgresql database.")
    public void givenDepartment_whenSaveDepartment_thanReturnMessage() throws Exception {
        BDDMockito.given(departmentService.saveDepartment(BDDMockito.any(Department.class))).willReturn(ResponseMessage.DEPARTMENT_RECORD_SAVED);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/department/saveDepartment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(department))
        );

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    @DisplayName("Test for fetch Department from postgresql database")
    public void givenDepartmentId_whenGetDepartmentFromPostgresql_thanReturnDepartment() throws Exception {
        BDDMockito.given(departmentService.getDepartmentFromPostgresql(BDDMockito.anyInt())).willReturn(department);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/department/fetchDepartmentFromNewDb/{departmentId}", department.getDepartmentId()));

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentId",
                        CoreMatchers.is(department.getDepartmentId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentName",
                        CoreMatchers.is(department.getDepartmentName())));

    }

}
