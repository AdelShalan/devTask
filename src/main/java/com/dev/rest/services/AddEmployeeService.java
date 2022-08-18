package com.dev.rest.services;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dev.rest.dao.employeeDao;
import com.dev.rest.model.Employee;

@Service
public class AddEmployeeService implements employeeService {
	@Autowired
	employeeDao employeeDao;

	@Override
	public boolean addEmployee(Employee employee) {
		if (employeeDao.save(employee) != null)
			return true;
		else
			return false;
	}
	
	@Override
	public ArrayList<Employee> addEmployees(JSONArray jsonArray) throws JSONException {
		ArrayList<Employee> employeesArray = new ArrayList<>();
		JSONObject jsonObject;
		Employee employee;
		
		for (int i = 0; i < jsonArray.length(); i++) {
			employee = new Employee();
			jsonObject = jsonArray.getJSONObject(i);
			employee.setName(jsonObject.getString("name"));
			employee.setAge(jsonObject.getInt("age"));
			employeesArray.add(employee);
			addEmployee(employee);
		}
		return employeesArray;
	}
	
}