package com.dev.rest.services;

import com.dev.rest.model.Employee;

public interface employeeService {
	public boolean saveEmployeeToDB(Employee employee) throws Exception;
}
