package com.dbMigration.demo.kafka.consumer;

import com.dbMigration.demo.payload.CardDetail;
import com.dbMigration.demo.payload.Company;
import com.dbMigration.demo.service.CardDetailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CardDetailConsumer {

    private static final Logger logger=Logger.getLogger(CardDetailConsumer.class);

    @Autowired
    private CardDetailService cardDetailService;

    /*
     * Creating Kafka Listener which listen CardDetail record.
     *
     * @param CardDetail
     *
     * */
    @KafkaListener(
            topics = "${spring.kafka.cardDetail-topic-name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consumeRecord(CardDetail cardDetail) {
        logger.info("Consuming card Details record..:" + cardDetail.getCardHolderName());
        cardDetailService.saveCardDetails(cardDetail);
    }

}
