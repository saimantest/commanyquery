package com.estockmarket.company.query.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.estockmarket.cqrscore.domain.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "stock_price")
public class StockPrice extends BaseEntity{

	@Id
	private String id;
	private String companyCode;
	private Date dateCreated;
	private double currentStockPrice;

}
