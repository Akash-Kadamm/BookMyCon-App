package com.dbMigration.demo.controller;

import com.dbMigration.demo.kafka.producer.AddressProducer;
import com.dbMigration.demo.payload.Address;
import com.dbMigration.demo.service.AddressService;
import com.dbMigration.demo.utility.ResponseMessage;
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
public class AddressControllerTest {

    @MockBean
    private AddressService addressService;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AddressProducer addressProducer;

    Address address;

    @BeforeEach
    public void SetUp() {
        address = Address.builder()
                .addressId(1)
                .localLandmark("Akurdi")
                .homeNumber("302")
                .cityName("pune")
                .pinCode("431601")
                .build();
    }

    @Test
    @DisplayName("Test for get Address by addressId.")
    public void givenAddressId_whenGetAddressByAddressId_thanReturnAddress() throws Exception {
        BDDMockito.given(addressService.getAddressById(BDDMockito.anyInt())).willReturn(address);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/address/fetchAddress/{addressId}", address.getAddressId()));

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.addressId",
                        CoreMatchers.is(address.getAddressId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cityName",
                        CoreMatchers.is(address.getCityName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.homeNumber",
                        CoreMatchers.is(address.getHomeNumber())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pinCode",
                        CoreMatchers.is(address.getPinCode())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.localLandmark",
                        CoreMatchers.is(address.getLocalLandmark())));
    }

    @Test
    @DisplayName("Test for Save Address in postgresql database.")
    public void givenAddress_whenSaveAddress_thanReturnMessage() throws Exception {
        BDDMockito.given(addressService.saveAddress(BDDMockito.any(Address.class))).willReturn(ResponseMessage.ADDRESS_RECORD_SAVED);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/address/saveAddress")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(address))
        );

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
