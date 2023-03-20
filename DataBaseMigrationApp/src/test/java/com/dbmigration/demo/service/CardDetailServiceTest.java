package com.dbmigration.demo.service;

import com.dbmigration.demo.encryptanddecrypt.Encryption;
import com.dbmigration.demo.model.CardDetail;
import com.dbmigration.demo.repo.mysql.MysqlCardDetailRepo;
import com.dbmigration.demo.repo.postgresql.PostgresqlCardDetailRepo;

import static org.assertj.core.api.Assertions.*;

import com.dbmigration.demo.utility.ResponseMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.BDDMockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CardDetailServiceTest {
    @Mock
    private MysqlCardDetailRepo mysqlCardDetailRepo;
    @Mock
    private PostgresqlCardDetailRepo postgresqlCardDetailRepo;

    @InjectMocks
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
    @DisplayName("Test for Get card details from postgresql database positive scenario.")
    public void givenCardDetailId_whenGetCardDetailsFromPostgresql_thanReturnCardDetail() {
        given(postgresqlCardDetailRepo.findById(anyInt())).willReturn(Optional.of(cardDetail));
        CardDetail savedCardDetail = cardDetailService.getCardDetailByIdFromPostgresqlDataBase(cardDetail.getCardDetailId());
        assertThat(savedCardDetail.getCardDetailId()).isEqualTo(1);
        assertThat(savedCardDetail.getCardHolderName()).isEqualTo("Akash Kadam");
    }

    @Test
    @DisplayName("Test for Get card details from postgresql database negative scenario.")
    public void givenCardDetailId_whenGetCardDetailsFromPostgresql_thanReturnNull() {
        given(postgresqlCardDetailRepo.findById(anyInt())).willReturn(Optional.empty());
        CardDetail savedCardDetail = cardDetailService.getCardDetailByIdFromPostgresqlDataBase(cardDetail.getCardDetailId());
        assertThat(savedCardDetail).isEqualTo(null);
    }

    @Test
    @DisplayName("Test for Get card details from mysql database positive scenario.")
    public void givenCardDetailId_whenGetCardDetailFromMysql_thanReturnCardDetail() {
        given(mysqlCardDetailRepo.findById(anyInt())).willReturn(Optional.of(cardDetail));
        CardDetail savedCardDetail = cardDetailService.getCardDetailByIdFromMysqlDataBase(cardDetail.getCardDetailId());
        assertThat(savedCardDetail.getCardDetailId()).isEqualTo(1);
        assertThat(savedCardDetail.getCardHolderName()).isEqualTo("Akash Kadam");
    }

    @Test
    @DisplayName("Test for Get card details from mysql database negative scenario.")
    public void givenCardDetailId_whenGetCardDetailFromMysql_thanReturnNull() {
        given(mysqlCardDetailRepo.findById(anyInt())).willReturn(Optional.empty());
        CardDetail savedCardDetail = cardDetailService.getCardDetailByIdFromMysqlDataBase(cardDetail.getCardDetailId());
        assertThat(savedCardDetail).isEqualTo(null);
    }

    @Test
    @DisplayName("Test For save card details in postgresql database positive scenario")
    public void givenCardDetail_whenSaveCardDetail_thanReturnPositiveMessage() {
        given(postgresqlCardDetailRepo.save(any(CardDetail.class))).willReturn(cardDetail);
        cardDetail.setCardNumber(Encryption.encryptCardNumber(cardDetail.getCardNumber()));
        String message = cardDetailService.saveCardDetails(cardDetail);
        assertThat(message).isEqualTo(ResponseMessage.CARD_DETAILS_SAVED.getMessage());
    }

    @Test
    @DisplayName("Test For save card details in postgresql database negative scenario")
    public void givenCardDetail_whenSaveCardDetail_thanReturnNegativeMessage() {
        given(postgresqlCardDetailRepo.save(any(CardDetail.class))).willReturn(null);
        cardDetail.setCardNumber(Encryption.encryptCardNumber(cardDetail.getCardNumber()));
        String message = cardDetailService.saveCardDetails(cardDetail);
        assertThat(message).isEqualTo(ResponseMessage.CARD_DETAILS_NOT_SAVED.getMessage());
    }

    @Test
    @DisplayName("Test for delete card details from postgresql database positive scenario.")
    public void givenCardDetailId_whenDeleteCardDetail_thanReturnPositiveMessage() {
        given(postgresqlCardDetailRepo.findById(anyInt())).willReturn(Optional.of(cardDetail));
        willDoNothing().given(postgresqlCardDetailRepo).deleteById(anyInt());
        String message = cardDetailService.deleteCardDetail(cardDetail.getCardDetailId());
        assertThat(message).isEqualTo(ResponseMessage.CARD_DETAILS_ARE_DELETED.getMessage());
    }

    @Test
    @DisplayName("Test for delete card details from postgresql database negative scenario.")
    public void givenCardDetailId_whenDeleteCardDetail_thanReturnNegativeMessage() {
        given(postgresqlCardDetailRepo.findById(anyInt())).willReturn(Optional.empty());
        String message = cardDetailService.deleteCardDetail(cardDetail.getCardDetailId());
        assertThat(message).isEqualTo(ResponseMessage.CARD_DETAILS_NOT_FOUND.getMessage());
    }

    @Test
    @DisplayName("Test for Update card details Positive scenario.")
    public void givenCardDetail_whenUpdateCardDetails_thanReturnPositiveMessage() {
        given(mysqlCardDetailRepo.findById(anyInt())).willReturn(Optional.of(cardDetail));
        given(mysqlCardDetailRepo.save(any(CardDetail.class))).willReturn(cardDetail);
        String message = cardDetailService.updateCardDetail(cardDetail);
        assertThat(message).isEqualTo(ResponseMessage.CARD_DETAILS_UPDATED.getMessage());
    }

    @Test
    @DisplayName("Test for Update card details negative scenario.")
    public void givenCardDetail_whenUpdateCardDetails_thanReturnNegativeMessage() {
        given(mysqlCardDetailRepo.findById(anyInt())).willReturn(Optional.empty());
        String message = cardDetailService.updateCardDetail(cardDetail);
        assertThat(message).isEqualTo(ResponseMessage.CARD_DETAILS_NOT_FOUND.getMessage());
    }


}
