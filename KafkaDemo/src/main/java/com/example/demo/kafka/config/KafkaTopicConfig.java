package com.example.demo.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

	
	@Value(value="${spring.kafka.topic-name}")
	private String kafkaTopicName;
	
	
	/*
	 * Creating new Topic.
	 * 
	 * @param
	 * @return NewTopic object
	 * 
	 * */
	@Bean
	public NewTopic newTopic() {
		return TopicBuilder
				.name(kafkaTopicName)
				.build();
	}
	
}
