package com.dbmigration.demo.controller;

import com.dbmigration.demo.model.Company;
import com.dbmigration.demo.service.CompanyService;
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
    @DisplayName("Test Get company by companyID.")
    public void givenCompanyId_whenGetByCompanyId_thanReturnCompany() throws Exception{
        int companyId=1;
        BDDMockito.given(companyService.getByCompanyId(BDDMockito.anyInt())).willReturn(company);

        ResultActions response=mockMvc.perform(MockMvcRequestBuilders.get("/company/fetchCompany/{companyId}",companyId));

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.companyName",
                        CoreMatchers.is(company.getCompanyName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.companyId",
                        CoreMatchers.is(company.getCompanyId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.companyAddress",
                        CoreMatchers.is(company.getCompanyAddress())));
    }
    @Test
    @DisplayName("Test for save company in postgresql database.")
    public void givenCompany_whenSaveCompany_thanReturnCompany() throws Exception{
        BDDMockito.given(companyService.saveCompany(company)).willReturn(company);

        ResultActions response=mockMvc.perform(MockMvcRequestBuilders.post("/company/saveCompany")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(company)));

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("Test for delete company by companyId.")
    public void givenCompanyId_whenDeleteCompany_thanReturnMessage() throws  Exception{
        int companyId=1;
        BDDMockito.given(companyService.deleteCompany(BDDMockito.anyInt())).willReturn(ResponseMessage.COMPANY_RECORD_DELETED);

        ResultActions response=mockMvc.perform(MockMvcRequestBuilders.delete("/company/deleteCompany/{companyId}",companyId));

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    @DisplayName("Test for get company by company name.")
    public void givenCompanyName_whenGetCompanyByCompanyName_thanReturnCompany() throws  Exception{
        String companyName=company.getCompanyName();
        BDDMockito.given(companyService.getCompanyByCompanyName(BDDMockito.anyString())).willReturn(company);

        ResultActions response=mockMvc.perform(MockMvcRequestBuilders.get("/company/getCompany/{companyName}",companyName));

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.companyName",
                        CoreMatchers.is(company.getCompanyName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.companyId",
                        CoreMatchers.is(company.getCompanyId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.companyAddress",
                        CoreMatchers.is(company.getCompanyAddress())));

    }
}
