package com.dev.rest.services;

import com.dev.rest.model.Company;

public interface companyService {
	public Company addCompanyJSON(Company companyJson);
	public Company getCompanyById(int id);
}
