package com.example.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.main.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
