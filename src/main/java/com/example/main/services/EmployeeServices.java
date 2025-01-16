package com.example.main.services;

import java.util.List;

import com.example.main.model.Employee;

public interface EmployeeServices {

	public List<Employee> registerEmployee(List<Employee> emp);
	public List<Employee> getEmpData();
	public void deleteEmpoloyee(int empId);
	public Employee getDetails(int empId);
	public Employee registerSingleEmployee(Employee emp);
}
