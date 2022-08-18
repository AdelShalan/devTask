package com.dev.rest.services;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

import com.dev.rest.model.Employee;

public interface employeeService {
	public boolean addEmployee(Employee employee);
	public ArrayList<Employee> addEmployees(JSONArray jsonArray) throws JSONException;
}
