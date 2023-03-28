package com.dbmigration.demo.controller;

import com.dbmigration.demo.model.CardDetail;
import com.dbmigration.demo.service.CardDetailService;
import com.dbmigration.demo.utility.ResponseMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.hamcrest.CoreMatchers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class CardDetailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CardDetailService cardDetailService;

    CardDetail cardDetail;

    @BeforeEach
    public void setUp() {
        cardDetail = CardDetail.builder()
                .cardDetailId(1)
                .cardHolderName("Akash Kadam")
                .cardNumber("1234123412341234")
                .build();
    }

    @Test
    @DisplayName("Fetch card details from mysql database.")
    public void givenCardDetailId_whenFetchCardDetailsFromOldDb_thanReturnCardDetail() throws Exception {
        given(cardDetailService.getCardDetailByIdFromMysqlDataBase(anyInt())).willReturn(cardDetail);

        ResultActions response = mockMvc.perform(get("/cardDetails/fetchCardDetail/{cardDetailId}", cardDetail.getCardDetailId()));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cardDetailId", CoreMatchers.is(cardDetail.getCardDetailId())))
                .andExpect(jsonPath("$.cardHolderName", CoreMatchers.is(cardDetail.getCardHolderName())))
                .andExpect(jsonPath("$.cardNumber", CoreMatchers.is(cardDetail.getCardNumber())));
    }

    @Test
    @DisplayName("Test for Delete card details")
    public void givenCardDetailsId_whenDeleteCardDetails_thanReturnMessage() throws Exception {
        given(cardDetailService.deleteCardDetail(anyInt())).willReturn(ResponseMessage.CARD_DETAILS_ARE_DELETED.getMessage());

        ResultActions response = mockMvc.perform(delete("/cardDetails/deleteCardDetail/{cardDetailId}", cardDetail.getCardDetailId()));

        response.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test for Saving Card details in postgresql database.")
    public void givenCardDetail_whenSaveCardDetails_thanReturnMessage() throws Exception {
        given(cardDetailService.saveCardDetails(any(CardDetail.class))).willReturn(ResponseMessage.CARD_DETAILS_SAVED.getMessage());

        ResultActions response = mockMvc.perform(post("/cardDetails/saveCardDetail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cardDetail)));

        response.andDo(print())
                .andExpect(status().isCreated());

    }

}
