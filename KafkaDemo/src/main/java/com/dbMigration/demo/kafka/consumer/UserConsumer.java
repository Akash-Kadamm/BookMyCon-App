package com.dbMigration.demo.kafka.consumer;

import com.dbMigration.demo.payload.User;
import com.dbMigration.demo.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserConsumer {

    private static final Logger logger = Logger.getLogger(UserConsumer.class);

    @Autowired
    private UserService userService;

    /*
     * Creating Kafka Listener which listen User record.
     *
     * @param User
     *
     * */
    @KafkaListener(topics = "${spring.kafka.user-topic-name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consumeRecord(User user) {
        logger.info("Consuming User record...:" + user.getUserName());
        userService.saveUserInMysql(user);
        userService.saveUserInPostgresql(user);
    }


}
