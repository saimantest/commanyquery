package com.estockmarket.company.query.api.controller;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estockmarket.company.common.exception.BusinessException;
import com.estockmarket.company.query.api.queries.FindAllCompaniesQuery;
import com.estockmarket.company.query.api.queries.FindCompanyByCodeQuery;
import com.estockmarket.company.query.api.queries.FindStockPriceBetweenIntervalsQuery;
import com.estockmarket.company.query.dto.CompanyLookupResponse;
import com.estockmarket.cqrscore.domain.BaseEntity;
import com.estockmarket.cqrscore.infra.QueryDispatcher;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1.0/market")
public class CompanyLookupController {

	private static final String SUCCESS = "success";

	private final Logger logger = Logger.getLogger(CompanyLookupController.class.getName());

	@Autowired
	private QueryDispatcher queryDispatcher;

	@Operation(summary = "findAllCompanies")
	@GetMapping("/company/getall")
	public ResponseEntity<CompanyLookupResponse> findAllCompanies() {
		logger.info("Getting all comapanies: ");
		
			FindAllCompaniesQuery query = new FindAllCompaniesQuery();
			List<BaseEntity> result = queryDispatcher.send(query);
			logger.info("Dispatched FindAllCompaniesQuery: ");
			CompanyLookupResponse response = CompanyLookupResponse.builder().data(result).message(SUCCESS).build();
			return new ResponseEntity<CompanyLookupResponse>(response, HttpStatus.OK);
	}

	@Operation(summary = "findAllCompanywithCode")
	@GetMapping("/company/info/{companyCode}")
	public ResponseEntity<CompanyLookupResponse> findCompanywithCode(@PathVariable("companyCode") String companyCode) {
		logger.info("Finding comapany with : " + companyCode);

			FindCompanyByCodeQuery query = FindCompanyByCodeQuery.builder().companyCode(companyCode).build();
			List<BaseEntity> result = queryDispatcher.send(query);
			logger.info("Dispatched FindAllCompaniesQuery: ");
			CompanyLookupResponse response = CompanyLookupResponse.builder().data(result).message(SUCCESS).build();
			return new ResponseEntity<CompanyLookupResponse>(response, HttpStatus.OK);
	}

	@Operation(summary = "findStockPriceBetweenInterval")
	@GetMapping("/stock/get/{companyCode}/{startDate}/{endDate}")
	public ResponseEntity<CompanyLookupResponse> findStockPriceBetweenInterval(
			@PathVariable("companyCode") String companyCode, @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
		logger.info("findStockPriceBetweenInterval : ");
		
			FindStockPriceBetweenIntervalsQuery query = FindStockPriceBetweenIntervalsQuery.builder()
					.companyCode(companyCode).startDate(startDate).endDate(endDate).build();
			logger.info("findStockPriceBetweenInterval : "+query);
			List<BaseEntity> result = queryDispatcher.send(query);
			logger.info("Dispatched FindAllCompaniesQuery: ");
			CompanyLookupResponse response = CompanyLookupResponse.builder().data(result).message(SUCCESS).build();
			return new ResponseEntity<CompanyLookupResponse>(response, HttpStatus.OK);
	}

}
