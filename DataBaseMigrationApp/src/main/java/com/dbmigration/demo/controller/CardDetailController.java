package com.dbmigration.demo.controller;

import com.dbmigration.demo.model.CardDetail;
import com.dbmigration.demo.service.CardDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/cardDetails")
public class CardDetailController {
    @Autowired
    private CardDetailService cardDetailService;

    @GetMapping("/fetchCardDetail/{cardDetailId}")
    public ResponseEntity<CardDetail> fetchCardDetailFromOldDb(@PathVariable int cardDetailId) {
        return new ResponseEntity<>(
                cardDetailService.getCardDetailByIdFromMysqlDataBase(cardDetailId),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/deleteCardDetail/{cardDetailId}")
    public ResponseEntity<String> deleteCardDetail(@PathVariable int cardDetailId) {
        return new ResponseEntity<>(
                cardDetailService.deleteCardDetail(cardDetailId),
                HttpStatus.OK
        );
    }

    @PostMapping("/saveCardDetail")
    public ResponseEntity<String> saveCardDetail(@RequestBody CardDetail cardDetail) {
        return new ResponseEntity<>(
          cardDetailService.saveCardDetails(cardDetail),
          HttpStatus.CREATED
        );
    }




}
