package com.dev.rest.services;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dev.rest.dao.companyDao;
import com.dev.rest.model.Company;

@Service
public class AddGetCompanyService implements companyService {
	@Autowired
	companyDao companyDao;
	@Autowired
	private employeeService employeeService;

	public boolean addCompany(Company company) {
		if (companyDao.save(company) != null) 
			return true;
		else
			return false;
	}
	
	@Override
	public boolean addCompanyJSON(String json) {
		Company company = new Company();
		try {
			JSONObject jsonObject = new JSONObject(json);
			company.setName(jsonObject.getString("company name"));
			company.setEmployees(employeeService.addEmployees(jsonObject.getJSONArray("employees")));
			return addCompany(company);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Company getCompany(int companyId) {
		Company company = companyDao.findById(companyId).orElse(null);
		return company;
	}
}
