package com.example.demo.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.example.demo.dbPRepo.PostgresqlAccountRepo;
import com.example.demo.payload.Account;




@Service
public class AccountConsumer {

	private static final Logger logger =LoggerFactory.getLogger(AccountConsumer.class);
	
	
	@Autowired
	private PostgresqlAccountRepo pRepo;
	
	
	/*
	 * Creating Kafka Listener which listen Account record.
	 *   
	 * @param Account object
	 * 
	 * */
	@KafkaListener(topics = "${spring.kafka.account-topic-name}",
			       groupId = "${spring.kafka.consumer.group-id}")
	public void accountConsumer(Account account) {
		logger.info("Message consume : "+account);
		pRepo.save(account);
		
	}
	
}
