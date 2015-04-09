package com.java.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.java.model.Employee;


public interface PersonRepository extends CrudRepository<Employee, Long> {
	 
	public List<Employee> findAll();
	
}
