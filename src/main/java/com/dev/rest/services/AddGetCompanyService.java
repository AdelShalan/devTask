package com.dev.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dev.rest.dao.companyDao;
import com.dev.rest.exception.ApiRequestException;
import com.dev.rest.model.Company;
import com.dev.rest.model.Employee;

@Service
public class AddGetCompanyService implements companyService {
	@Autowired
	companyDao companyDao;
	@Autowired
	private employeeService employeeService;

	public boolean saveCompanyToDB(Company company) throws Exception {
		if (companyDao.existsById(company.getId()))
			throw new ApiRequestException("Duplicate company ID!");
		else if (companyDao.save(company).equals(null))
			throw new ApiRequestException("Failed to save company!");
		else
			return true;
	}

	@Override
	public boolean addCompanyJSON(Company companyJson) throws Exception {
		try {
			for (Employee employee : companyJson.getEmployees()) {
				employeeService.saveEmployeeToDB(employee);
			}
			saveCompanyToDB(companyJson);
			return true;
		} catch (ApiRequestException e) {
			throw e;
		}
	}

	@Override
	public Company getCompanyById(int companyId) {
		return companyDao.findById(companyId).orElse(null);
	}
}
