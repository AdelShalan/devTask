package com.dev.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dev.rest.dao.companyDao;
import com.dev.rest.exception.ApiRequestException;
import com.dev.rest.model.Company;

@Service
public class CompanyServiceImpl implements companyService {
	@Autowired
	companyDao companyDao;

	public Company saveCompanyToDB(Company company) throws ApiRequestException {
		if (companyDao.existsById(company.getId()))
			throw new ApiRequestException("Duplicate company ID!");
		return companyDao.save(company);
	}

	@Override
	public Company addCompanyJSON(Company companyJson) {
		try {
			return saveCompanyToDB(companyJson);
		} catch (ApiRequestException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Company getCompanyById(int companyId) {
		return companyDao.findById(companyId).orElse(null);
	}
}
