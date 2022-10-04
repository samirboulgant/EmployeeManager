package com.luv2cod.springboot.thymeleafdemo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2cod.springboot.thymeleafdemo.entity.Employee;
import com.luv2cod.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	private List<Employee> employees;
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
//	@PostConstruct
//	public void loadData() {
//		employees = employeeService.findAll();
//	}
//	
	@GetMapping("/emplist")
	public String EmployeeList(Model themodel) {
		employees = employeeService.findAll();
		themodel.addAttribute("theEmployees",employees);
		return "employees/employee-list";
	}
	@GetMapping("/employeeform")
	public String EmployeeForm(Model themodel) {
		Employee theemployees = new	Employee();
		themodel.addAttribute("employees",theemployees);
		return "employees/employeeform";
	}
	
	@PostMapping("/save")
	public String saveemployee(@ModelAttribute("employees") Employee emp) {
		employeeService.save(emp);
		return "redirect:/employees/emplist";
	}
	
	@GetMapping("/update")
	public String updateemployee(@RequestParam("empId") int id,Model model) {
		Employee theemployee = employeeService.findById(id);
		model.addAttribute("employees",theemployee);
		return "employees/employeeform";
	}
	
	@GetMapping("/delete")
	public String deleteemployee(@RequestParam("empId") int id) {
		employeeService.deleteById(id);
		return "redirect:/employees/emplist";
	}
	@GetMapping("/search")
	public String delete(@RequestParam("employeeName") String theName,
						 Model theModel) {
		
		// delete the employee
		 employees = employeeService.searchBy(theName);
		System.out.println(employees);
		
		// add to the spring model
		theModel.addAttribute("theEmployees", employees);
		
		// send to /employees/list
		return "employees/employee-list";
		
	}
	
}
