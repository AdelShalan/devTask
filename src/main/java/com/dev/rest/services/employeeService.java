package com.dev.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dev.rest.dao.employeeDao;
import com.dev.rest.model.Employee;

@Service
public class employeeService {
	@Autowired
	employeeDao dao;

	public boolean addEmployee(Employee e) {
		if (dao.save(e) != null)
			return true;
		else
			return false;
	}

}