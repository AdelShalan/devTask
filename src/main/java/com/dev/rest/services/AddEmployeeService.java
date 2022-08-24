package com.dev.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dev.rest.dao.employeeDao;
import com.dev.rest.exception.ApiRequestException;
import com.dev.rest.model.Employee;

@Service
public class AddEmployeeService implements employeeService {
	@Autowired
	employeeDao employeeDao;

	@Override
	public Employee saveEmployeeToDB(Employee employee) {
		if (employeeDao.existsById(employee.getId()))
			throw new ApiRequestException("Duplicate Employee ID! - ID = " + employee.getId());

		return employeeDao.save(employee);
	}
}