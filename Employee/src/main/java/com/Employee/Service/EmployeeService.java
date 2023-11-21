package com.Employee.Service;

import com.Employee.Model.Employee;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

  public List<Employee> getAllEmp() throws Exception;

  public Employee addEmp(Employee employee);

  public Object updateEmp(Employee updatedEmployee, long id) throws Exception;

  public String deleteEmp(long id);

  public Object getEmpById(long id) throws Exception;

  public Optional<Employee> getEmpByName(String name);

  public Page<Employee> getPageDetails(Pageable p);
}
