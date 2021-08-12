package com.cflx.dao;

import java.util.List;

import com.cflx.model.Employee;

public interface EmployeeDao {

	Employee getEmployeeById(String id);

	List<Employee> getEmployee();

	Employee createEmployee(Employee emp);

	Employee updateEmployee(Employee emp);

	String deleteEmployee(String id);

}
