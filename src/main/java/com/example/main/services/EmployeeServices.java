package com.example.main.services;

import java.util.List;

import com.example.main.model.Employee;

public interface EmployeeServices {

	public void registerEmployee(Employee emp);

	public List<Employee> getEmpData();
}
