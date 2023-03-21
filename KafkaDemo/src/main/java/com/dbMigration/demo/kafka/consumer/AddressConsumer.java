package com.dbMigration.demo.kafka.consumer;

import com.dbMigration.demo.payload.Address;
import com.dbMigration.demo.service.AddressService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AddressConsumer {


    private static final Logger logger = Logger.getLogger(AddressConsumer.class);

    @Autowired
    private AddressService addressService;


    /*
     * Creating Kafka Listener which listen Address record.
     *
     * @param Address
     *
     * */
    @KafkaListener(
            topics = "${spring.kafka.address-topic-name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consumeRecord(Address address) {
        logger.info("Consuming user Address :" + address);
        addressService.saveAddress(address);
    }


}
