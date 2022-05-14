package com.estockmarket.company.query.dto;

import java.util.List;

import com.estockmarket.company.common.dto.BaseResponse;
import com.estockmarket.cqrscore.domain.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class CompanyLookupResponse extends BaseResponse {

	private List<BaseEntity> data;

	public CompanyLookupResponse(String message) {
		super(message);
	}

}
