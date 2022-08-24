package com.dev.rest.services;

import com.dev.rest.exception.ApiRequestException;
import com.dev.rest.model.Company;

public interface companyService {
	public Company saveCompanyToDB(Company companyJson) throws ApiRequestException, Exception;
	public Company getCompanyById(int id);
}
