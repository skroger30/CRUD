package com.example.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String registerEmp(@RequestBody() List<Employee> emp) {
		emps.registerEmployee(emp);
		return "Registered Successfully.!!!";
	}
	
	@RequestMapping(value = "/getAll",method = RequestMethod.GET )
	@Cacheable(value="employeeInfo") 
	public List<Employee> getAllData() {
		List<Employee> allEmp = emps.getEmpData();
		return allEmp;
	}
	
	@RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
	public void deleteEmp(@PathVariable("id") int id) {
			emps.deleteEmpoloyee(id);
	}
	
	@RequestMapping(value = "/update",method = RequestMethod.PUT )
	public String updateEmp(@RequestBody() List<Employee> emp) {
		emps.registerEmployee(emp);
		return "Upadates Saved Successfully.!!!";
	}
}
