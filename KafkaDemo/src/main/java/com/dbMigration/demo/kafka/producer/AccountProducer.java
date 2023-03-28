package com.dbMigration.demo.kafka.producer;

import com.dbMigration.demo.payload.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
public class AccountProducer {

    private static final Logger logger = LoggerFactory.getLogger(AccountProducer.class);


    @Value("${spring.kafka.account-topic-name}")
    private String accountTopicName;

    private KafkaTemplate<String, Account> kafkaTemplate;


    /*
     * Account Producer Constructor.
     *
     * @param Kafka Template object
     *
     * */
    public AccountProducer(KafkaTemplate<String, Account> kafkaTemplate) {
        super();
        this.kafkaTemplate = kafkaTemplate;
    }


    /*
     * Sending Account record to Kafka Broker.
     *
     * @param Account object
     *
     * */
    public void sendRecord(Account account) {
        logger.info("Sending Record...:" + account);
        Message<Account> message = MessageBuilder
                .withPayload(account)
                .setHeader(KafkaHeaders.TOPIC, accountTopicName)
                .build();
        kafkaTemplate.send(message);
    }
}
