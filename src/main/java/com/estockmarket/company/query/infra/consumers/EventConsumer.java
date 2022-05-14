package com.estockmarket.company.query.infra.consumers;

import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

import com.estockmarket.company.common.events.CompanyDeletedEvent;
import com.estockmarket.company.common.events.CompanyRegisteredEvent;
import com.estockmarket.company.common.events.StockPriceAddedEvent;

public interface EventConsumer {
	
	void consume(@Payload CompanyRegisteredEvent event, Acknowledgment ack);
	void consume(@Payload StockPriceAddedEvent event, Acknowledgment ack);
	void consume(@Payload CompanyDeletedEvent event, Acknowledgment ack);
}
