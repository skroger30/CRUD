package com.example.main.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.main.model.Employee;
import com.example.main.repository.EmployeeRepository;
import com.example.main.services.EmployeeServices;

@Service
public class EmployeeServiceImpl implements EmployeeServices {

	@Autowired
	EmployeeRepository empRepo;
	
	@Override
	public void registerEmployee(Employee emp) {
		// TODO Auto-generated method stub
		empRepo.save(emp);
	}

	@Override
	public List<Employee> getEmpData() {
		// TODO Auto-generated method stub
		return empRepo.findAll();
	}

}
