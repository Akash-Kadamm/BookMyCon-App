package com.dbMigration.demo.kafka.producer;

import com.dbMigration.demo.payload.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class AddressProducer {

    private static final Logger logger = LoggerFactory.getLogger(AddressProducer.class);


    @Value("${spring.kafka.address-topic-name}")
    private String addressTopicName;


    private KafkaTemplate<String, Address> kafkaTemplate;


    /*
     * Address Producer Constructor.
     *
     * @param Kafka Template object
     *
     * */
    public AddressProducer(KafkaTemplate<String, Address> kafkaTemplate) {
        super();
        this.kafkaTemplate = kafkaTemplate;
    }

    /*
     * Sending Address record to Kafka Broker.
     *
     * @param Address object
     *
     * */
    public void sendRecord(Address address) {

        logger.info("Sending user address record :" + address);

        Message<Address> message = MessageBuilder
                .withPayload(address)
                .setHeader(KafkaHeaders.TOPIC, addressTopicName)
                .build();
        kafkaTemplate.send(message);
    }

}
