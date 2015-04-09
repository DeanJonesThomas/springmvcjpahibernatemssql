package com.java.service;

import java.util.List;

import com.java.model.Employee;

/**
 * @author 
 *
 */
public interface EmployeeService {
	
	public void addEmployee(Employee employee);

	public List<Employee> listEmployeess();
	
	public Employee getEmployee(int empid);
	
	public void deleteEmployee(Employee employee);
}
