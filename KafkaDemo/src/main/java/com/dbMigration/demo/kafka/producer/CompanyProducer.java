package com.dbMigration.demo.kafka.producer;

import com.dbMigration.demo.payload.Company;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class CompanyProducer {

    private static final Logger logger = Logger.getLogger(CompanyProducer.class);


    @Value("${spring.kafka.company-topic-name}")
    private String companyTopicName;

    private KafkaTemplate<String, Company> kafkaTemplate;


    /*
     * Company Producer Constructor.
     *
     * @param Kafka Template object
     *
     * */
    public CompanyProducer(KafkaTemplate<String, Company> kafkaTemplate) {
        super();
        this.kafkaTemplate = kafkaTemplate;
    }

    /*
     * Sending Company record to Kafka Broker.
     *
     * @param Company object
     *
     * */
    public void sendRecord(Company company) {
        logger.info("Sending user company record :" + company.getCompanyName());

        Message<Company> message = MessageBuilder
                .withPayload(company)
                .setHeader(KafkaHeaders.TOPIC, companyTopicName)
                .build();

        kafkaTemplate.send(message);
    }

}
