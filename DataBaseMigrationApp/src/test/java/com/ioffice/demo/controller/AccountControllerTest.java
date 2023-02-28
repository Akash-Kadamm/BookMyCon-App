package com.ioffice.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ioffice.demo.service.AccountService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import com.ioffice.demo.model.Account;
import com.ioffice.demo.repo.mysql.MysqlAccountRepo;
import com.ioffice.demo.repo.postgresql.PostgresqlAccountRepo;
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
class AccountControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	@MockBean
	private AccountService accountService;


	Account account1;
	Account account2;

	@BeforeEach
	public void setUp(){
		account1=Account.builder()
				.id(1)
				.customerName("Akash Kadam")
				.isMigrate(false)
				.build();
		account2=Account.builder()
				.id(2)
				.customerName("Ajit Hirave")
				.isMigrate(false)
				.build();
	}

	@Test
	@DisplayName("test for get list  Account from mysql database ")
	public void givenListOfAccountObject_whenGetAllAccountsFromMysql_thanReturnResponseEntity() throws Exception{
		List<Account> accounts=new ArrayList<>(List.of(account1,account2));
		BDDMockito.given(accountService.getAllAccountsFromMysqlDataBase()).willReturn(accounts);

		ResultActions response=mockMvc.perform(MockMvcRequestBuilders.get("/Account/mysqlAccount"));

		response.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(accounts.size())));
	}
	@Test
	@DisplayName("test for get list pf Account from postgresql database ")
	public void givenListOfAccountObject_whenGetAllAccountsFromPostgresql_thanReturnResponseEntity() throws Exception{
		List<Account> accounts=new ArrayList<>(List.of(account1,account2));
		BDDMockito.given(accountService.getAllAccountsFromPostgresqlDataBase()).willReturn(accounts);

		ResultActions response=mockMvc.perform(MockMvcRequestBuilders.get("/Account/postgresqlAccount"));

		response.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.size()",
						CoreMatchers.is(accounts.size())));
	}

    @Test
	@DisplayName("test for create Account ")
	public void givenAccountObject_whenCreateAccount_thanReturnResponseEntity() throws Exception{
		boolean flag= false;
		BDDMockito.given(accountService.createAccount(ArgumentMatchers.any(Account.class),BDDMockito.anyBoolean()))
				.willAnswer((invocation)->{
					return invocation.getArgument(0);
				});
		ResultActions response=mockMvc.perform(MockMvcRequestBuilders.post("/Account/createAccount")
				.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(account1))
				);
		response.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id",
				CoreMatchers.is(account1.getId())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.customerName",
						CoreMatchers.is(account1.getCustomerName())));
	}
}
