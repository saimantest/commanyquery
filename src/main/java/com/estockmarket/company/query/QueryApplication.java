package com.estockmarket.company.query;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.estockmarket.company.query.api.queries.FindAllCompaniesQuery;
import com.estockmarket.company.query.api.queries.FindCompanyByCodeQuery;
import com.estockmarket.company.query.api.queries.FindStockPriceBetweenIntervalsQuery;
import com.estockmarket.company.query.api.queries.QueryHandler;
import com.estockmarket.cqrscore.infra.QueryDispatcher;

@SpringBootApplication
public class QueryApplication {

	@Autowired
	private QueryDispatcher queryDispatcher;

	@Autowired
	QueryHandler queryHandler;

	public static void main(String[] args) {
		SpringApplication.run(QueryApplication.class, args);
	}

	@PostConstruct
	public void registerHandlers() {
		queryDispatcher.registerHandler(FindAllCompaniesQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindCompanyByCodeQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindStockPriceBetweenIntervalsQuery.class, queryHandler::handle);
	}
}
