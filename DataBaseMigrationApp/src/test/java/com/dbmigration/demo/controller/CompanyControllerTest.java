package com.dbmigration.demo.controller;

import com.dbmigration.demo.model.Company;
import com.dbmigration.demo.service.CompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.List;

@WebMvcTest
public class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CompanyService companyService;

    Company company;

    @BeforeEach
    public void setUp(){
        company=Company.builder()
                .companyId(1)
                .companyName("Cybage Software Pvt. Ltd. Pune")
                .companyAddress("Pune")
                .build();
    }

    @Test
    @DisplayName("Test for get all users to be migrated by there company name.")
    public void givenListOfCompany_whenGetAllUsers_thanResponseEntity() throws Exception{
        String companyName="Cybage Software Pvt. Ltd. Pune";
        BDDMockito.given(companyService.getAllUsersToBeMigrate(BDDMockito.anyString())).willReturn(List.of(company));

        ResultActions response=mockMvc.perform(MockMvcRequestBuilders.get("/company/getAllUsers/{companyName}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(companyName))
        );

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}
