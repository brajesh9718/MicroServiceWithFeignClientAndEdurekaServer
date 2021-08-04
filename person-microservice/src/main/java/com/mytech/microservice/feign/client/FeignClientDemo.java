package com.mytech.microservice.feign.client;

import java.util.ArrayList;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.person.client.EmployeeDto;

@FeignClient(url = "localhost:1111",value="employee-microservice")
public interface FeignClientDemo {

	@GetMapping
	String getEmployeeMsg();
	
	@GetMapping("/{id}")
	String getEmloyeeById(@PathVariable int id);
	
	@GetMapping("/employeesStringList")
	ArrayList<String> getEmployeeList();
	
	@GetMapping("/employeesObjectList")
	ArrayList<EmployeeDto> getEmployeesObjectList();
}
