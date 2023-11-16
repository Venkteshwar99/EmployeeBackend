package com.Employee.Exception;

public class EmployeeException  extends RuntimeException{
	
	public EmployeeException(String message) {
	       super(message);
	   }
	   public static EmployeeException notFoundException(int employeeId) {
	       return new EmployeeException("Employee not found with ID: " + employeeId);
	   }
	   
	   public static EmployeeException updateException(int employeeId) {
	       return new EmployeeException("Employee ID already exists: " + employeeId);
	   }
	   
}
