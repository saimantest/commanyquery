package com.estockmarket.company.query.api.queries;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estockmarket.company.query.domain.Company;
import com.estockmarket.company.query.domain.CompanyRepository;
import com.estockmarket.company.query.domain.StockPrice;
import com.estockmarket.company.query.domain.StockPriceRepository;
import com.estockmarket.cqrscore.domain.BaseEntity;

@Service
public class CompanyQueryHandler implements QueryHandler {

	private final Logger logger = Logger.getLogger(CompanyQueryHandler.class.getName());

	@Autowired
	private CompanyRepository companyRepo;

	@Autowired
	private StockPriceRepository stockRepo;

	@Override
	public List<BaseEntity> handle(FindAllCompaniesQuery query) {
		Iterable<Company> companies = companyRepo.findAll();
		List<BaseEntity> companyList = new ArrayList<>();
		companies.forEach(companyList::add);
		logger.info("Company list size: " + companyList.size());
		return companyList;
	}

	@Override
	public List<BaseEntity> handle(FindCompanyByCodeQuery query) {
		Optional<Company> company = companyRepo.findByCompanyCode(query.getCompanyCode());
		if (company.isEmpty())
			return null;
		List<BaseEntity> companyList = new ArrayList<>();
		companyList.add(company.get());
		logger.info(String.format("Company with %s company code: %s", query.getCompanyCode(), company.get()));
		return companyList;
	}

	@Override
	public List<BaseEntity> handle(FindStockPriceBetweenIntervalsQuery query) {
		Iterable<StockPrice> stocks = stockRepo.findByDateCreatedBetween(query.getCompanyCode(), query.getStartDate(),
				query.getEndDate());
		List<BaseEntity> stockPrices = new ArrayList<>();
		stocks.forEach(stockPrices::add);
		logger.info("stockPrices list size: " + stockPrices.size());
		return stockPrices;
	}

}
