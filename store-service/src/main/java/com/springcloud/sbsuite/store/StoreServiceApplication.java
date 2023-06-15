package com.springcloud.sbsuite.store;

import com.springcloud.sbsuite.event.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class StoreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreServiceApplication.class, args);
	}

	@KafkaListener(topics = "orderTopic", groupId = "storeOrderTopicId")
	public void handleNotification(OrderPlacedEvent orderPlacedEvent) {
		log.info("StoreServiceApplication:: Received Order Notification for Product - {}", orderPlacedEvent.getProductId());
	}

}
