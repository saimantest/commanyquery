package com.estockmarket.company.query.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockPriceRepository extends CrudRepository<StockPrice, String> {
	//select * from stock_price where company_code="CTZ" and date_created between "2022-05-02 00:22:47" AND "2022-05-02 00:26:30";

	@Query("SELECT a FROM StockPrice a WHERE a.companyCode = ?1 and a.dateCreated between ?2 and ?3 order by a.dateCreated")
	List<StockPrice> findByDateCreatedBetween(String companyCode, Date start, Date end);
	@Modifying
	Long deleteByCompanyCode(String companyCode);

}
