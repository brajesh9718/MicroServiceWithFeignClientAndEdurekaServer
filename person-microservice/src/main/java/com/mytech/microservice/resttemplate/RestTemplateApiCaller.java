package com.mytech.microservice.resttemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.person.client.EmployeeDto;

@RestController
@RequestMapping("/restTemp")
public class RestTemplateApiCaller {

	@GetMapping("/")
    public List<EmployeeDto> getAllEmployees() {
        System.out.println("Calling EmployeeMicroservice using RestTemplate !!");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<EmployeeDto>> response = restTemplate.exchange(
                "http://localhost:1111/employeesObjectList/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<EmployeeDto>>() {
                });
        List<EmployeeDto> EmployeeDtos = response.getBody();
        return EmployeeDtos;
    }
	
    @GetMapping("{id}")
    public EmployeeDto getEmployeeById(@PathVariable("id") int id) {
        Map<String, String> uriParams = new HashMap<String, String>();
        uriParams.put("id", String.valueOf(id));
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:1111/{id}")
                .buildAndExpand(uriParams)
                .toUri();
        System.out.println(uri);
        RestTemplate restTemplate = new RestTemplate();
        EmployeeDto forEntity = restTemplate.getForObject(uri, EmployeeDto.class);
        return forEntity;
    }
    
    @PostMapping
    public ResponseEntity addEmployeeDto(@RequestBody EmployeeDto EmployeeDto) {
        System.out.println("Add EmployeeDto");
        System.out.println(EmployeeDto.toString());
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<EmployeeDto> request = new HttpEntity<>(EmployeeDto);
        ResponseEntity exchange = restTemplate
                .exchange("http://localhost:1111/addEmployee/", HttpMethod.POST, request, String.class);
        return exchange;
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity deleteEmployeeDto(@PathVariable int id) {
        System.out.println("delete EmployeeDto");
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", String.valueOf(id));
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("http://localhost:1111/EmployeeDto/{id}", params);
        return new ResponseEntity("EmployeeDto Deleted successfully", HttpStatus.OK);
    }
}
