package com.estockmarket.company.query.infra.handlers;

import com.estockmarket.company.common.events.CompanyDeletedEvent;
import com.estockmarket.company.common.events.CompanyRegisteredEvent;
import com.estockmarket.company.common.events.StockPriceAddedEvent;

public interface EventHandler {
	
	void on(CompanyRegisteredEvent event);
	void on(StockPriceAddedEvent event);
	void on(CompanyDeletedEvent event);

}
