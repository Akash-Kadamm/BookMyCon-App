package com.dbMigration.demo.kafka.producer;

import com.dbMigration.demo.payload.Account;
import com.dbMigration.demo.payload.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class UserProducer {

    private static final Logger logger = LoggerFactory.getLogger(UserProducer.class);


    @Value("${spring.kafka.user-topic-name}")
    private String userTopicName;

    private KafkaTemplate<String, User> kafkaTemplate;


    /*
     * User Producer Constructor.
     *
     * @param Kafka Template object
     *
     * */
    public UserProducer(KafkaTemplate<String, User> kafkaTemplate) {
        super();
        this.kafkaTemplate = kafkaTemplate;
    }

    /*
     * Sending User record to Kafka Broker.
     *
     * @param User object
     *
     * */
    public void sendRecord(User user) {
        logger.info("Sending User Record :" + user.getUserName());
        Message<User> message = MessageBuilder
                .withPayload(user)
                .setHeader(KafkaHeaders.TOPIC, userTopicName)
                .build();
        kafkaTemplate.send(message);

    }
}
