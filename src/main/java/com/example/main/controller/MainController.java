package com.example.main.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.example.main.exceptionHandler.ErrorDetails;
import com.example.main.exceptionHandler.ResourceNotFoundException;
import com.example.main.model.Employee;
import com.example.main.services.EmployeeServices;


@RestController 
@RequestMapping(value = "/employee")
public class MainController {

	@Autowired
	private EmployeeServices emps;
	
	
//		POST API - 		http://localhost:9095/employee/create
//	@RequestMapping(value = "/register",method = RequestMethod.POST )
//	OR
	@PostMapping("/create") 
	public ResponseEntity<List<Employee>>  registerEmp(@RequestBody() List<Employee> emp) {
		List<Employee> savedUser = emps.registerEmployee(emp);
		return new ResponseEntity<List<Employee>>(savedUser,HttpStatus.CREATED);
	}

	@PostMapping("/register") 
	public ResponseEntity<Employee>  registerSingleEmp(@RequestBody() Employee emp) {
		Employee savedUser = emps.registerSingleEmployee(emp);
		return new ResponseEntity<Employee>(savedUser,HttpStatus.CREATED);
	}

	
//	GET API - http://localhost:9095/employee/getAll
	@GetMapping("/getAll") 
	public ResponseEntity<List<Employee>> getAllData() {
		List<Employee> allEmp = emps.getEmpData();
		return new ResponseEntity<List<Employee>>(allEmp,HttpStatus.OK);
	}
	
	
	
//	DELETE API - http://localhost:9095/employee/delete/1
	@RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
	public void deleteEmp(@PathVariable("id") int id) {
			emps.deleteEmpoloyee(id);
	}
	
	
	
	
//	PUT API - http://localhost:9095/employee/update
	@RequestMapping(value = "/update",method = RequestMethod.PUT )
	public String updateEmp(@RequestBody() List<Employee> emp) {
		emps.registerEmployee(emp);
		return "Upadates Saved Successfully.!!!";
	}
	
	
//	GET API - http://localhost:9095/employee/getData/10
	@GetMapping("/getData/{id}")
	public Employee getDetails(@PathVariable("id") int empId) {
		return emps.getDetails(empId);		
	}
	
	
//	Controller Level Exception Handling with customized error details.
//	@ExceptionHandler(ExceptionClassName.class) -  used to handle the specific exceptions 
//												    and sending the custom response to client.
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails>  handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
		 
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				exception.getMessage(),
				webRequest.getDescription(false),
				"EMPLOYEE_NOT_FOUND");		
		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
}
