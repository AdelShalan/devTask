package com.dev.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dev.rest.dao.employeeDao;
import com.dev.rest.model.Employee;

@Service
public class AddEmployeeService implements employeeService {
	@Autowired
	employeeDao employeeDao;

	@Override
	public boolean saveEmployeeToDB(Employee employee) throws Exception {
		if (employeeDao.existsById(employee.getId()))
			throw new IllegalArgumentException("Duplicate Employee ID!" + employee.toString());
		else if(employeeDao.save(employee).equals(null))
			throw new Exception("Failed to save Employee!");
		else
			return true;
	}
}