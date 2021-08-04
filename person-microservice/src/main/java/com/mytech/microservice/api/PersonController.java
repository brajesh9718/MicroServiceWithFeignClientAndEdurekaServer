package com.mytech.microservice.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mytech.microservice.feign.client.FeignClientDemo;
import com.person.client.EmployeeDto;

@RestController
public class PersonController {

	@Value("${number}")
	private String number;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	FeignClientDemo feignClient;

	@Bean
	RestTemplate resTemplate() {
		return new RestTemplate();
	}

	@RequestMapping("/callemployee")
	String sayHello() {
		return "Here I am going to call Employee Microservice From Person MicroService using Feign Client ------- > "
				+ feignClient.getEmployeeMsg();
	}

	@RequestMapping("/employees/{id}")
	String getEmployeeAgeById(@PathVariable int id) {
		System.out.println("Inside getEmployeeAgeById ........");
		return feignClient.getEmloyeeById(id);
	}

	@RequestMapping("/employeesStringList")
	ArrayList<String> getEmployees() {
		return feignClient.getEmployeeList();
	}

	@GetMapping("/employeesObjectList")
	ArrayList<EmployeeDto> getEmployeesObjectList() {
		return feignClient.getEmployeesObjectList();
	}

	// MicroService communication using RestTemplate
	@RequestMapping("/employee/{id}")
	String getHelloFromEmployee(@PathVariable int id) {
		System.out.println("Employee id is :" + id);
		Map<String, Integer> parameters = new HashMap<String, Integer>();
		parameters.put("id", id);
		String response = restTemplate.getForObject("http://localhost:1111/{id}", String.class, parameters);
		return response;
	}
}
