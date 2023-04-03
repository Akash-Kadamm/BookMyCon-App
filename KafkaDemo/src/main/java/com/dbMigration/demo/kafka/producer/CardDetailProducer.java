package com.dbMigration.demo.kafka.producer;

import com.dbMigration.demo.payload.CardDetail;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class CardDetailProducer {

    private static final Logger logger=Logger.getLogger(CardDetailProducer.class);

    @Value("${spring.kafka.cardDetail-topic-name}")
    private String cardTopicName;

    private KafkaTemplate<String, CardDetail> kafkaTemplate;


    /*
     * CardDetails Producer Constructor.
     *
     * @param Kafka Template object
     *
     * */
    public CardDetailProducer(KafkaTemplate<String, CardDetail> kafkaTemplate) {
        super();
        this.kafkaTemplate = kafkaTemplate;
    }

    /*
     * Sending Card details record to Kafka Broker.
     *
     * @param CardDetails object
     *
     * */
    public void sendRecord(CardDetail cardDetail) {
        logger.info("Sending user card detail record :" + cardDetail.getCardHolderName());

        Message<CardDetail> message = MessageBuilder
                .withPayload(cardDetail)
                .setHeader(KafkaHeaders.TOPIC, cardTopicName)
                .build();

        kafkaTemplate.send(message);
    }


}
