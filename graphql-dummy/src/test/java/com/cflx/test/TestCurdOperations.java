package com.cflx.test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.cflx.graphql.GraphQLManager;
import com.cflx.model.Employee;

import graphql.ExecutionResult;

public class TestCurdOperations {

	private GraphQLManager graphQLManager;

	@BeforeMethod
	public void init() {
		graphQLManager = new GraphQLManager();
	}

	@Test
	public void testLookupAllEmployee() {
		String querry = "query lookupEmployee{lookupEmployee{id name city}}";
		ExecutionResult result = graphQLManager.getData(querry, Collections.emptyMap());
		System.out.println(result);

	}

	@Test
	public void testLookupEmployeeById() {
		String querry = "query lookupEmployeeByID($id:String!){lookupEmployeeByID(id:$id){id name}}";
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("id", 4);
		ExecutionResult result = graphQLManager.getData(querry, parm);
		System.out.println(result);

	}

	@Test
	public void testCreateEmployee() {
		String querry = "mutation createEmployee($emp:EmployeeInput!){createEmployee(emp:$emp){id name city}}";
		Map<String, Object> parm = new HashMap<String, Object>();
		Map<String, Object> emp = new HashMap<>();
		emp.put("id", "10");
		emp.put("name", "Shubham");
		emp.put("city", "Jaipur");
		parm.put("emp", emp);
		ExecutionResult result = graphQLManager.getData(querry, parm);
		System.out.println(result);
	}

	@Test
	public void testUpdateEmployee() {
		String querry = "mutation updateEmployee($emp:EmployeeInput!){updateEmployee(emp:$emp){id name city}}";
		Map<String, Object> parm = new HashMap<String, Object>();
		Map<String, Object> emp = new HashMap<>();
		emp.put("id", "1");
		emp.put("name", "Ravi");
		emp.put("city", "Pune");
		parm.put("emp", emp);
		ExecutionResult result = graphQLManager.getData(querry, parm);
		System.out.println(result);
	}

	@Test
	public void testDeleteEmployee() {
		String querry = "mutation deleteEmployee($id:String!){deleteEmployee(id:$id)}";
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("id", 1);
		ExecutionResult result = graphQLManager.getData(querry, parm);
		System.out.println(result);
	}

}
