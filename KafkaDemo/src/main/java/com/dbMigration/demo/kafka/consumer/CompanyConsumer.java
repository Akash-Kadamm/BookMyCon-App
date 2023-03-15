package com.dbMigration.demo.kafka.consumer;

import com.dbMigration.demo.payload.Company;
import com.dbMigration.demo.service.CompanyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CompanyConsumer {

    private static final Logger logger = Logger.getLogger(CompanyConsumer.class);

    @Autowired
    private CompanyService companyService;

    /*
     * Creating Kafka Listener which listen Company record.
     *
     * @param Company
     *
     * */
    @KafkaListener(
            topics = "${spring.kafka.company-topic-name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consumeRecord(Company company) {
        logger.info("Consuming company record..:" + company.getCompanyName());
        companyService.saveCompany(company);
    }


}
