package com.cflx.graphql;

import java.util.List;
import java.util.Map;

import com.cflx.dao.EmployeeDao;
import com.cflx.dao.impl.EmployeeDaoImpl;
import com.cflx.model.Employee;

import graphql.schema.DataFetcher;

public class GraphQLDataFetcherImpl {

	private EmployeeDao employeeDao;

	public GraphQLDataFetcherImpl() {
		this.employeeDao = new EmployeeDaoImpl();

	}

	public DataFetcher getLookupEmployeeByID() {
		return lookupEmployeeByID;
	}

	public DataFetcher getLookupEmployee() {
		return lookupEmployee;
	}

	public DataFetcher createEmployee() {
		return createEmployee;
	}

	public DataFetcher updateEmployee() {
		return updateEmployee;
	}

	public DataFetcher deleteEmployee() {
		return deleteEmployee;
	}

	DataFetcher lookupEmployeeByID = env -> {
		System.out.println("Executing lookupEmployeeByID DataFetcher");
		Map<String, Object> parameters = env.getArguments();
		Employee employee = employeeDao.getEmployeeById((String) parameters.get("id"));
		System.out.println("Employee :: " + employee);
		System.out.println("Executing lookupEmployeeByID DataFetcher");
		return employee;
	};

	DataFetcher lookupEmployee = env -> {
		System.out.println("Executing lookupEmployee DataFetcher");
		Map<String, Object> parameters = env.getArguments();
		List<Employee> employees = employeeDao.getEmployee();
		System.out.println("Employee :: " + employees);
		System.out.println("Executing lookupEmployee DataFetcher");
		return employees;
	};

	DataFetcher createEmployee = env -> {
		System.out.println("Executing createEmployee DataFetcher");
		Map<String, Object> parameters = env.getArguments();
		System.out.println(">>>>>> parms :: " + parameters);
		Map<String, Object> employeeMap = (Map<String, Object>) parameters.get("emp");
		System.out.println("Employee Map :: {}" + employeeMap);
		Employee emp = new Employee(employeeMap.get("id").toString(), employeeMap.get("name").toString(),
				employeeMap.get("city").toString());
		Employee employees = employeeDao.createEmployee(emp);
		System.out.println("Employee :: " + employees);
		System.out.println("Executing createEmployee DataFetcher");
		return employees;
	};

	DataFetcher updateEmployee = env -> {
		System.out.println("Executing updateEmployee DataFetcher");
		Map<String, Object> parameters = env.getArguments();
		System.out.println(">>>>>> parms :: " + parameters);
		Map<String, Object> employeeMap = (Map<String, Object>) parameters.get("emp");
		System.out.println("Employee Map :: {}" + employeeMap);
		Employee emp = new Employee(employeeMap.get("id").toString(), employeeMap.get("name").toString(),
				employeeMap.get("city").toString());
		Employee employee = employeeDao.updateEmployee(emp);
		System.out.println("Employee :: " + employee);
		System.out.println("Executing updateEmployee DataFetcher");
		return employee;
	};

	DataFetcher deleteEmployee = env -> {
		System.out.println("Executing deleteEmployee DataFetcher");
		Map<String, Object> parameters = env.getArguments();
		String msg = employeeDao.deleteEmployee((String) parameters.get("id"));
		System.out.println("Message :: " + msg);
		System.out.println("Executing deleteEmployee DataFetcher");
		return msg;
	};

}
