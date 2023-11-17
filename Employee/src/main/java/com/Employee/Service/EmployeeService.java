package com.Employee.Service;

import com.Employee.Model.Employee;
import java.util.List;

public interface EmployeeService {

  public List<Employee> getAllEmp() throws Exception;

  public Employee addEmp(Employee employee);

  public Object updateEmp(Employee updatedEmployee, long id) throws Exception;

  public String deleteEmp(long id);

  public Object getEmpById(long id) throws Exception;
}
