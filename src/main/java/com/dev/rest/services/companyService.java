package com.dev.rest.services;

import com.dev.rest.model.Company;

public interface companyService {
	public boolean addCompanyJSON(String json);
	public Company getCompany(int id);
}
