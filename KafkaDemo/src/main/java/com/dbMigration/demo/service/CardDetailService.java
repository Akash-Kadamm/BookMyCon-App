package com.dbMigration.demo.service;

import com.dbMigration.demo.mysql.MysqlCardDetailRepo;
import com.dbMigration.demo.payload.CardDetail;
import com.dbMigration.demo.postgresql.PostgresqlCardDetailRepo;
import com.dbMigration.demo.utility.Decryption;
import com.dbMigration.demo.utility.Encryption;
import com.dbMigration.demo.utility.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardDetailService {

    @Autowired
    private MysqlCardDetailRepo mysqlCardDetailRepo;

    @Autowired
    private PostgresqlCardDetailRepo postgresqlCardDetailRepo;

    public CardDetail getCardDetailByIdFromPostgresqlDataBase(int cardDetailId) {
        Optional<CardDetail> savedCardDetail = postgresqlCardDetailRepo.findById(cardDetailId);
        if (savedCardDetail.isEmpty()) {
            return null;
        }
        CardDetail newCard=savedCardDetail.get();
        newCard.setCardNumber(Decryption.decryptionOfCardNumber(newCard.getCardNumber()));
        return newCard;
    }

    public CardDetail getCardDetailByIdFromMysqlDataBase(int cardDetailId) {
        Optional<CardDetail> savedCardDetail = mysqlCardDetailRepo.findById(cardDetailId);
        if (savedCardDetail.isEmpty()){
            return null;
        }
        return savedCardDetail.get();
    }

    public String saveCardDetails(CardDetail cardDetail) {
        cardDetail.setCardNumber(Encryption.encryptCardNumber(cardDetail.getCardNumber()));
        CardDetail savedCard = postgresqlCardDetailRepo.save(cardDetail);
        if (savedCard == null) {
            return ResponseMessage.CARD_DETAILS_NOT_SAVED.getMessage();
        }
        return ResponseMessage.CARD_DETAILS_SAVED.getMessage();
    }

    public String deleteCardDetail(int cardDetailId) {
        Optional<CardDetail> savedCard =postgresqlCardDetailRepo.findById(cardDetailId);
        if (savedCard.isEmpty()) {
            return ResponseMessage.CARD_DETAILS_NOT_FOUND.getMessage();
        }
        postgresqlCardDetailRepo.deleteById(savedCard.get().getCardDetailId());
        return ResponseMessage.CARD_DETAILS_ARE_DELETED.getMessage();
    }

    public String updateCardDetail(CardDetail cardDetail) {
        Optional<CardDetail> savedCard=mysqlCardDetailRepo.findById(cardDetail.getCardDetailId());
        if (savedCard.isEmpty()) {
            return ResponseMessage.CARD_DETAILS_NOT_FOUND.getMessage();
        }
        savedCard.get().setCardNumber(cardDetail.getCardNumber());
        savedCard.get().setCardHolderName(cardDetail.getCardHolderName());
        mysqlCardDetailRepo.save(savedCard.get());
        return ResponseMessage.CARD_DETAILS_UPDATED.getMessage();
    }

}
