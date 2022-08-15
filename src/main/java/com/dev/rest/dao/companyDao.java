package com.dev.rest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.dev.rest.model.Company;

@Repository
public interface companyDao extends CrudRepository<Company, Integer> {
	
}
