package com.estockmarket.company.query.api.queries;

import java.util.Date;

import com.estockmarket.cqrscore.queries.BaseQuery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FindStockPriceBetweenIntervalsQuery extends BaseQuery {

	private String companyCode;
	private Date startDate;
	private Date endDate;

}
