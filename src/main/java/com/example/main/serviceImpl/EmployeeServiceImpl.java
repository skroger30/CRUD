package com.example.main.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.main.exceptionHandler.MobileNumberExistsException;
import com.example.main.exceptionHandler.ResourceNotFoundException;
import com.example.main.model.Employee;
import com.example.main.repository.EmployeeRepository;
import com.example.main.services.EmployeeServices;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import jakarta.annotation.PostConstruct;

@Service
public class EmployeeServiceImpl implements EmployeeServices {

	@Autowired
	private EmployeeRepository empRepo;
	
	@Override
	public List<Employee> registerEmployee(List<Employee> empList) {
		// TODO Auto-generated method stub		
		 empRepo.saveAll(empList);
		List<Employee>  list  = getAllCache.getIfPresent("Employees");
		if(Objects.isNull(list))
			getAllCache.put("Employees", empList);
		else {
			list.addAll(empList);
			getAllCache.put("Employees", list);
		}
		return empList;
	}
 
	@Override
	public List<Employee> getEmpData() {
		// TODO Auto-generated method stub
        if(getAllCache.getIfPresent("Employees") !=  null){
            List<Employee>  list  = getAllCache.getIfPresent("Employees");
            System.out.println("we are retrieving the employee from the cache");
            return list;
        }
        
		List<Employee>allEmpls =empRepo.findAll();
		getAllCache.put("Employees", allEmpls);
		System.out.println("List of employees fetched from DB and inserted in cache.");
		return allEmpls;
	}

	@Override
	public void deleteEmpoloyee(int empId) {
		// TODO Auto-generated method stub
		empRepo.deleteById(empId);
	}

//	Caching Implementation - Creating Cache Object
	private Cache<String, List<Employee>> getAllCache;
	
	@PostConstruct
	private void initCache() {
		getAllCache = Caffeine.newBuilder()
                .maximumSize(100)
                .build();    
	}

	@Override
	public Employee getDetails(int empId) {
		// TODO Auto-generated method stub
//		return empRepo.findById(empId).get();//to eliminate Optional return type.
//		Calling Custom-Exception Class.
		return empRepo.findById(empId)
				.orElseThrow( ()-> new ResourceNotFoundException("Employee", "id", empId));
	}

	@Override
	public Employee registerSingleEmployee(Employee emp) {
		// TODO Auto-generated method stub
		Employee empByMob = empRepo.findByMobileNo(emp.getMobileNo());
		if(!Objects.isNull(empByMob)) {
			 throw new MobileNumberExistsException("Employee with same mobile number already exists.");
		}			 
		
		empRepo.save(emp);
		return emp;
	}
	
}
