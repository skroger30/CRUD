package com.example.main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	private String empName;
//	To implement soft-delete
	private boolean isActive = true;
	private long mobileNo;

}
