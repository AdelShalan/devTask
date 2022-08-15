package com.dev.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dev.rest.dao.companyDao;
import com.dev.rest.model.Company;

@Service
public class companyService {
	@Autowired
	companyDao dao;
	
	public void addCompany(Company c){
		dao.save(c);
	}
	
	public Company getCompany(int id){
		Company company = dao.findById(id).orElse(null);
		return company;
	}
}
