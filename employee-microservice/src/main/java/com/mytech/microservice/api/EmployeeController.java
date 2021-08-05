package com.mytech.microservice.api;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.client.Employee;

@RestController
public class EmployeeController {
	
	@Autowired
	Environment env;
	
	@GetMapping("/")
	public String getMsg() {
		return "Welcome to the Employee MicroService.....";
	}
	

	@RequestMapping("/{id}")
	String sayHello(@PathVariable int id) {
		if (id == 2) {
			return "The Given ID is 2";
		}
		return "Hello from Employee microservice " + env.getProperty("number");
	}

	@RequestMapping("/employeesStringList")
	ArrayList<String> getEmployeesStringList() {
		ArrayList<String> employees = new ArrayList<>();
		Employee employee1 = new Employee(101, "Ram");
		Employee employee2 = new Employee(102, "Shyam");
		employees.add("employee1");
		employees.add("employee2");
		System.out.println(employees);
		return employees;
	}

	@GetMapping("/employeesObjectList")
	ArrayList<Employee> getEmployeesObjectList() {
		ArrayList<Employee> employees = new ArrayList<>();
		Employee employee1 = new Employee(101, "Ram");
		Employee employee2 = new Employee(102, "Shyam");
		employees.add(employee1);
		employees.add(employee2);
		System.out.println(employees);
		return employees;
	}
}
