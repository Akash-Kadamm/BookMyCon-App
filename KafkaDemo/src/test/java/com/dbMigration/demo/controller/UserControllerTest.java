package com.dbMigration.demo.controller;

import com.dbMigration.demo.dbMigrationservice.UserMigrationService;
import com.dbMigration.demo.payload.User;
import com.dbMigration.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@WebMvcTest
public class UserControllerTest {
    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserMigrationService userMigrationService;

    User user;

    @BeforeEach
    public void setUp() {
        user=User.builder()
                .userId(1)
                .userEmail("akashkad@cybage.com")
                .userName("Akash Kadam")
                .userPassword("ak@123")
                .userContactNumber("7038967693")
                .addressId(1)
                .companyId(1)
                .isMigrate(false)
                .build();
    }

    @Test
    @DisplayName("Test for Get All Users.")
    public void given_whenGetAllUsers_thanReturnListOfUsers() throws  Exception{
        BDDMockito.given(userService.getAllUsers()).willReturn(List.of(user));

        ResultActions response=mockMvc.perform(MockMvcRequestBuilders.get("/users/getAllUsers"));

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    @DisplayName("Test for Get User By userId")
    public void givenUserId_whenGetUserByUserId_thanReturnUser() throws Exception{
        BDDMockito.given(userService.getUserByUserId(BDDMockito.anyInt())).willReturn(user);

        ResultActions response=mockMvc.perform(MockMvcRequestBuilders.get("/users/getUser/{userId}",user.getUserId()));

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId",
                        CoreMatchers.is(user.getUserId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userPassword",
                        CoreMatchers.is(user.getUserPassword())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName",
                        CoreMatchers.is(user.getUserName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userEmail",
                        CoreMatchers.is(user.getUserEmail())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userContactNumber",
                        CoreMatchers.is(user.getUserContactNumber())));

    }

    @Test
    @DisplayName("Test for migrate user by user Id.")
    public void givenUserId_whenMigrateUserByUserId_thanReturnMessage() throws Exception {
           BDDMockito.given(userMigrationService.userMigrationServiceByUserId(BDDMockito.anyInt())).willReturn("User is MIgrated..");

           ResultActions response=mockMvc.perform(MockMvcRequestBuilders.get("/users/migrateUser/{userId}",user.getUserId()));

           response.andDo(MockMvcResultHandlers.print())
                   .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    @DisplayName("Test for Get users to be migrated by company name.")
    public void givenCompanyName_whenGetUsersToBeMigratedByCompanyName_thanReturnListOfUsers() throws Exception{

        int companyId=1;
        BDDMockito.given(userService.getAllUsersByCompanyId(BDDMockito.anyInt())).willReturn(List.of(user));

        ResultActions response=mockMvc.perform(MockMvcRequestBuilders.get("/users/getUserToBeMigrateByCompanyName/{companyId}",companyId));

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()",CoreMatchers.is(1)));
    }

    @Test
    @DisplayName("Test for migrate user data by company name.")
    public void givenCompanyName_whenMigrateByCompanyName_thanReturnMessage() throws Exception{

        String companyName="cybage";
        BDDMockito.given(userMigrationService.migrationServiceByCompanyName(BDDMockito.anyString())).willReturn("All Users Migrated");

        ResultActions response=mockMvc.perform(MockMvcRequestBuilders.get("/users/migrateUsers/{companyName}",companyName));

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }




}
