package com.estockmarket.company.query.infra.handlers;

import java.util.Date;
import java.util.UUID;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estockmarket.company.common.events.CompanyDeletedEvent;
import com.estockmarket.company.common.events.CompanyRegisteredEvent;
import com.estockmarket.company.common.events.StockPriceAddedEvent;
import com.estockmarket.company.query.domain.Company;
import com.estockmarket.company.query.domain.CompanyRepository;
import com.estockmarket.company.query.domain.StockPrice;
import com.estockmarket.company.query.domain.StockPriceRepository;

@Service
public class CompanyEventHandler implements EventHandler {
	
	private final Logger logger = Logger.getLogger(CompanyEventHandler.class.getName());

	@Autowired
	CompanyRepository companyRepo;

	@Autowired
	StockPriceRepository stockRepo;

	@Override
	public void on(CompanyRegisteredEvent event) {
		logger.info("CompanyRegisteredEvent: "+event);
		try {
			Company comapny = Company.builder().id(UUID.randomUUID().toString()).companyCode(event.getCompanyCode())
					.companyCEO(event.getCompanyCEO()).companyName(event.getCompanyName())
					.companyTurnover(event.getCompanyTurnover()).createdBy(event.getCreatedBy()).dateCreated(new Date())
					.website(event.getWebsite()).stockExng(event.getStockExng().toString()).isActive(true).build();

			StockPrice stockprice = StockPrice.builder().id(UUID.randomUUID().toString()).companyCode(event.getId())
					.currentStockPrice(event.getCurrentStockPrice()).dateCreated(new Date()).build();

			companyRepo.save(comapny);
			stockRepo.save(stockprice);
		} catch (Exception e) {
			logger.info("Exception while CompanyRegisteredEvent: "+ e.getMessage());
		}

	}

	@Override
	public void on(StockPriceAddedEvent event) {
		try {
			Company comapny = companyRepo.findByCompanyCode(event.getId()).get();
			comapny.setCurrentStockPrice(event.getCurrentStockPrice());

			StockPrice stockprice = StockPrice.builder().id(UUID.randomUUID().toString())
					.companyCode(event.getId()).currentStockPrice(event.getCurrentStockPrice())
					.dateCreated(new Date()).build();
			companyRepo.save(comapny);
			stockRepo.save(stockprice);
		} catch (Exception e) {
			logger.info("Exception while StockPriceAddedEvent: "+ e.getMessage());
		}
	}

	@Override
	@Transactional
	public void on(CompanyDeletedEvent event) {
		try {
			companyRepo.deleteByCompanyCode(event.getId());
			stockRepo.deleteByCompanyCode(event.getId());
		} catch (Exception e) {
			logger.info("Exception while CompanyDeletedEvent: "+ e.getMessage());
		}
	}

}
