package com.Employee.Exception;

public class EmployeeException extends RuntimeException {

  public EmployeeException(String message) {
    super(message);
  }

  public static EmployeeException notFoundException(long l) {
    return new EmployeeException("Employee not found with ID: " + l);
  }

  public static EmployeeException updateException(long l) {
    return new EmployeeException("Employee ID already exists: " + l);
  }

  public static EmployeeException NoContentException() {
    return new EmployeeException("No Content found");
  }
}
