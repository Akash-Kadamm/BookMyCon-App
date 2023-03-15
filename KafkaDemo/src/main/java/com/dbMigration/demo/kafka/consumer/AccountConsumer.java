package com.dbMigration.demo.kafka.consumer;

import com.dbMigration.demo.payload.Account;
import com.dbMigration.demo.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class AccountConsumer {

    private static final Logger logger = LoggerFactory.getLogger(AccountConsumer.class);


    @Autowired
    private AccountService accountService;


    /*
     * Creating Kafka Listener which listen Account record.
     *
     * @param Account
     *
     * */
    @KafkaListener(topics = "${spring.kafka.account-topic-name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void accountConsumer(Account account) {
        logger.info("Message consume : " + account);
        accountService.saveAccountInPostgresqlDataBase(account);
    }

}
