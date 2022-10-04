package com.luv2cod.springboot.thymeleafdemo.service;

import java.util.List;

import com.luv2cod.springboot.thymeleafdemo.entity.Employee;

public interface EmployeeService {
	public List<Employee> findAll();
	public Employee findById(int id);
	public void save(Employee employee);
	public void deleteById(int id);
	public List<Employee> searchBy(String theName);

}
