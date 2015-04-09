package com.java.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 
 *
 */
@Entity
@Table(name="employee")
public class Employee implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "emp_id")
	private Integer empId;
	
	@Column(name="emp_Name")
	private String empName;
	
	@Column(name="emp_Address")
	private String empAddress;
	
	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpAddress() {
		return empAddress;
	}

	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}

	

	
}
