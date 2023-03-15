package com.dbMigration.demo.kafka.producer;

import com.dbMigration.demo.payload.Department;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class DepartmentProducer {

    private static final Logger logger = Logger.getLogger(DepartmentProducer.class);

    @Value("${spring.kafka.department-topic-name}")
    private String departmentTopicName;


    private KafkaTemplate<String, Department> kafkaTemplate;


    /*
     * Department Producer Constructor.
     *
     * @param Kafka Template object
     *
     * */
    public DepartmentProducer(KafkaTemplate<String, Department> kafkaTemplate) {
        super();
        this.kafkaTemplate = kafkaTemplate;
    }


    /*
     * Sending Department record to Kafka Broker.
     *
     * @param Department object
     *
     * */
    public void sendRecord(Department department) {
        logger.info("Sending user department record :" + department.getDepartmentName());
        Message<Department> message = MessageBuilder
                .withPayload(department)
                .setHeader(KafkaHeaders.TOPIC, departmentTopicName)
                .build();

        kafkaTemplate.send(message);
    }
}
