package com.estockmarket.company.query.api.queries;

import com.estockmarket.cqrscore.queries.BaseQuery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindCompanyByCodeQuery extends BaseQuery {

	private String companyCode;
}
