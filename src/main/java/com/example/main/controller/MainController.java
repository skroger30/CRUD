package com.example.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.model.Employee;
import com.example.main.services.EmployeeServices;


@RestController 
public class MainController {

	@Autowired
	EmployeeServices emps;
	
	@RequestMapping(value = "/register",method = RequestMethod.POST )
	public String registerEmp(@RequestBody() Employee emp) {
		emps.registerEmployee(emp);
		
		System.out.println("Employee Name="+ emp.getEmpName());
		return "Registered Successfully.!!!";
	}
	
	@RequestMapping(value = "/getAllData",method = RequestMethod.GET )
	public List<Employee> getAllData() {
		List<Employee> allEmp = emps.getEmpData();
		return allEmp;
	}
}
