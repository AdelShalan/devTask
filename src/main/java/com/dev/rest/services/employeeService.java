package com.dev.rest.services;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.rest.dao.employeeDao;
import com.dev.rest.model.Company;
import com.dev.rest.model.Employee;

@Service
public class employeeService {
	@Autowired
	employeeDao dao;

	public void addEmployee(Employee e) {
		dao.save(e);
	}

	public void addEmployees(JSONArray array, Company c) throws JSONException { //Retrieve and store employees + company FK from request json
		JSONObject o = null;
		Employee e;
		for (int i = 0; i < array.length(); i++) {
			e = new Employee(c);
			o = array.getJSONObject(i);
			e.setName(o.getString("name"));
			e.setAge(o.getInt("age"));
			dao.save(e);
			c.getEmployees().add(e);
		}
	}
}