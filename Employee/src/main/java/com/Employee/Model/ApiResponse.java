package com.Employee.Model;

public class ApiResponse {

  private Employee employee;
  private Department department;

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public ApiResponse(Employee employee, Department department) {
    super();
    this.employee = employee;
    this.department = department;
  }

  public ApiResponse() {}
}
