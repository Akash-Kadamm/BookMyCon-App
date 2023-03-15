package com.dbmigration.demo.service;

import com.dbmigration.demo.model.Address;
import com.dbmigration.demo.repo.mysql.MysqlAddressRepo;
import com.dbmigration.demo.repo.postgresql.PostgresqlAddressRepo;
import com.dbmigration.demo.utility.ResponseMessage;
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
public class AddressServiceTest {
    @Mock
    private MysqlAddressRepo mysqlAddressRepo;
    @Mock
    private PostgresqlAddressRepo postgresqlAddressRepo;
    @InjectMocks
    private AddressService addressService;

    Address address;

    @BeforeEach
    public void setUp(){
       address=Address.builder()
               .addressId(1)
               .cityName("Pune")
               .pinCode("431601")
               .homeNumber("678")
               .localLandmark("kalyani nager")
               .build();
    }

    @Test
    @DisplayName("Test for fetch address by addressId.")
    public void givenAddressId_whenGetAddressById_thanAddressObject(){
        int addressId=1;
        BDDMockito.given(mysqlAddressRepo.findById(BDDMockito.anyInt())).willReturn(Optional.of(address));
        Address savedAddress=addressService.getAddressById(addressId);
        Assertions.assertThat(savedAddress.getAddressId()).isEqualTo(1);
        Assertions.assertThat(savedAddress.getCityName()).isEqualTo("Pune");
        Assertions.assertThat(savedAddress.getHomeNumber()).isEqualTo("678");
        Assertions.assertThat(savedAddress.getPinCode()).isEqualTo("431601");
    }

    @Test
    @DisplayName("Test for Save Address in postgresql database")
    public void givenAddressObject_whenSaveAddress_thanMessage(){
        BDDMockito.given(postgresqlAddressRepo.save(BDDMockito.any(Address.class))).willReturn(address);
        ResponseMessage message=addressService.saveAddress(address);
        Assertions.assertThat(message).isEqualTo(ResponseMessage.ADDRESS_RECORD_SAVED);
    }
    @Test
    @DisplayName("Test for get Address from postgresql Database.")
    public void givenAddressId_whenGetAddressFromPostgresql_thanReturnAddress(){
        BDDMockito.given(postgresqlAddressRepo.findById(BDDMockito.anyInt())).willReturn(Optional.of(address));
        Address savedAddress =addressService.getAddressFromPostgresql(address.getAddressId());
        Assertions.assertThat(savedAddress.getAddressId()).isEqualTo(1);
        Assertions.assertThat(savedAddress.getCityName()).isEqualTo("Pune");
        Assertions.assertThat(savedAddress.getHomeNumber()).isEqualTo("678");
        Assertions.assertThat(savedAddress.getPinCode()).isEqualTo("431601");
    }

}
