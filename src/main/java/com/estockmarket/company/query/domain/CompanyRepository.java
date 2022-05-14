package com.estockmarket.company.query.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyRepository extends CrudRepository<Company, String>{
	
	Optional<Company> findByCompanyCode(String companyCode);
	@Modifying
	Long deleteByCompanyCode(String companyCode);
}
