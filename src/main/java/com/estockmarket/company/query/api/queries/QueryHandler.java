package com.estockmarket.company.query.api.queries;

import java.util.List;

import com.estockmarket.cqrscore.domain.BaseEntity;

public interface QueryHandler {
	
	List<BaseEntity> handle(FindAllCompaniesQuery query);
	List<BaseEntity> handle(FindCompanyByCodeQuery query);
	List<BaseEntity> handle(FindStockPriceBetweenIntervalsQuery query);

}
