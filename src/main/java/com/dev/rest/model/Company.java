package com.dev.rest.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Company {

	@Id
	private int id;
	@Column
	@NotBlank(message = "Company name is mandatory")
	private String name;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Employee> employees = new ArrayList<>();

	public Company() {

	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", employees=" + employees + "]";
	}
}
