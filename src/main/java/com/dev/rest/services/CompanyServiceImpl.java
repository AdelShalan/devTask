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

	@Override
	public Company saveCompanyToDB(Company companyJson) throws ApiRequestException, Exception {
		if (companyDao.existsById(companyJson.getId()))
			throw new ApiRequestException("Duplicate company ID!");
		return companyDao.save(companyJson);
	}

	@Override
	public Company getCompanyById(int companyId) {
		return companyDao.findById(companyId).orElse(null);
	}
}
