package com.dev.rest.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dev.rest.dao.companyDao;
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
			throw new IllegalArgumentException("Duplicate company ID!");
		else if (companyDao.save(company).equals(null))
			throw new Exception("Failed to save company!");
		else
			return true;
	}

	@Override
	public boolean addCompanyJSON(Company companyJson) throws Exception {
		Collection<Employee> invalidEmployees = new ArrayList<Employee>();
		for (Employee employee : companyJson.getEmployees()) { // A company must have unique employees
			try {
				employeeService.saveEmployeeToDB(employee);
			} catch (IllegalArgumentException duplicateIdException) {
				invalidEmployees.add(employee);
			} catch (Exception genericException) {
				genericException.printStackTrace();
				invalidEmployees.add(employee);
			}
		}
		
		companyJson.getEmployees().removeAll(invalidEmployees);
		if (companyJson.getEmployees().isEmpty()) // A company must have employees
			return false;
		
		try {
			saveCompanyToDB(companyJson);
		} catch (IllegalArgumentException duplicateIdException) {
			throw duplicateIdException;
		} catch (Exception genericException) {
			throw genericException;
		}
		return true;
	}

	@Override
	public Company getCompanyById(int companyId) {
		return companyDao.findById(companyId).orElse(null);
	}
}
