package com.dev.rest.services;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dev.rest.dao.companyDao;
import com.dev.rest.model.Company;
import com.dev.rest.model.Employee;

@Service
public class companyService {
	@Autowired
	companyDao dao;
	@Autowired
	private employeeService employeeService;

	public boolean addCompany(Company c) {
		dao.save(c);
		return true;
	}

	public Company getCompany(int id) {
		Company company = dao.findById(id).orElse(null);
		return company;
	}

	public boolean saveEmployees(JSONArray array, Company c) throws JSONException {
		ArrayList<Employee> employees = new ArrayList<>();
		JSONObject o = null;
		Employee e;

		for (int i = 0; i < array.length(); i++) {
			e = new Employee(c);
			o = array.getJSONObject(i);
			e.setName(o.getString("name"));
			e.setAge(o.getInt("age"));
			employees.add(e);
			c.getEmployees().add(e);
			employeeService.addEmployee(e);
		}
		return true;
	}
}
