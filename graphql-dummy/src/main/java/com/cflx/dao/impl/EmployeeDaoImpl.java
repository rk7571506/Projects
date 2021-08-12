package com.cflx.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cflx.dao.EmployeeDao;
import com.cflx.helper.CommonHelper;
import com.cflx.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	private Map<String, Employee> data = CommonHelper.getDummyData();

	@Override
	public Employee getEmployeeById(String id) {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("Invalid Id");
		}
		Employee emp = data.get(id);
		if (emp != null) {
			return emp;
		} else {
			return new Employee();
		}
	}

	@Override
	public List<Employee> getEmployee() {
		List<Employee> allEmployee = new ArrayList<Employee>();
		for (Map.Entry<String, Employee> emp : data.entrySet()) {
			allEmployee.add(emp.getValue());
		}
		return allEmployee;
	}

	@Override
	public Employee createEmployee(Employee emp) {
		data.put(emp.getId(), emp);
		System.out.println("Employee Map Dao :: " + data);
		return emp;
	}

	@Override
	public Employee updateEmployee(Employee emp) {
		if (data.get(emp.getId()) != null) {
			System.out.println("Employee Map Dao Before Update :: " + data);
			data.put(emp.getId(), emp);
			System.out.println("Employee Map Dao After Update :: " + data);
			return emp;
		}
		return null;
	}

	@Override
	public String deleteEmployee(String id) {
		if (data.get(id) != null) {
			data.remove(id);
			System.out.println("Employee Map Dao :: " + data);
			return "removed employee :: " + id;
		}

		return "not vaild id for remove employee ::" + id;
	}

}
