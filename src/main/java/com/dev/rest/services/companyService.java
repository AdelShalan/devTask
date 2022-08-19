package com.dev.rest.services;

import com.dev.rest.model.Company;

public interface companyService {
	public boolean addCompanyJSON(Company companyJson) throws Exception;
	public Company getCompanyById(int id);
}
