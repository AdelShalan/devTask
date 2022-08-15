package com.dev.rest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.dev.rest.model.Employee;

@Repository
public interface employeeDao extends CrudRepository<Employee, Integer> {

}
