package com.example.main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data // Optional to @Getter and @Setter

//@Builder
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int empId;
	
	@NotEmpty(message = "Employee name should not be blank.") // Employee Name should not be null or empty.
	private String empName;
	
//	To implement soft-delete
	private boolean isActive = true;
	private long mobileNo;

}
