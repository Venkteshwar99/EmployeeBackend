package com.Employee.Service;

import java.util.List;

import com.Employee.Model.Employee;

public interface EmployeeService {
	
	public List<Employee> getEmp();

	public Employee addEmp(Employee  employee);

	public Object updateEmp(Employee  employee);
	
}