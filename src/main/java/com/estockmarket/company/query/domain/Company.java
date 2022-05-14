package com.estockmarket.company.query.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.estockmarket.company.common.dto.StockExchnage;
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
@Table(name = "company")
public class Company extends BaseEntity {

	@Id
	private String id;
	@Column(unique = true)
	private String companyCode;
	private String companyName;
	private String companyCEO;
	private double companyTurnover;
	private String website;
	private String stockExng;
	private String createdBy;
	private Date dateCreated;
	private boolean isActive;
	private double currentStockPrice;
}
