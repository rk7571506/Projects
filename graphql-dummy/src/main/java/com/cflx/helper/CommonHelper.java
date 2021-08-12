package com.cflx.helper;

import java.util.HashMap;
import java.util.Map;

import com.cflx.model.Employee;

public class CommonHelper {

	private static Map<String, Employee> dummyDataMap;

	static {
		dummyDataMap = new HashMap<String, Employee>();
		dummyDataMap.put("1", new Employee("1", "Ravi Ranjan", "HMH"));
		dummyDataMap.put("2", new Employee("2", "Rahul", "Alwar"));
		dummyDataMap.put("3", new Employee("3", "Parag", "Bikaner"));
		dummyDataMap.put("4", new Employee("4", "Amit", "Pali"));
		dummyDataMap.put("5", new Employee("5", "Neeraj", "HMH"));
		dummyDataMap.put("6", new Employee("6", "Vinod", "HMH"));
	}

	public static Map<String, Employee> getDummyData() {
		return dummyDataMap;
	}

}
