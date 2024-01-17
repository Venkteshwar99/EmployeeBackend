package com.Employee.Service;

import com.Employee.Model.ApiResponse;
import com.Employee.Model.Employee;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

  public List<ApiResponse> getAllEmp() throws Exception;

  public Employee addEmp(Employee employee);

  public Employee updateEmp(Employee updatedEmployee, long id) throws Exception;

  public String deleteEmp(long id);

  public Optional<Employee> getEmpById(long id) throws Exception;

  public List<ApiResponse> getEmpByName(String name) throws Exception;

  public Page<Employee> getPageDetails(Pageable p);

  public Employee setOrUpdateImageEmp(Employee employee);

  public boolean softDeleteEmployee(long id) throws Exception;

  ApiResponse getActiveEmpById(long id) throws Exception;

  public List<ApiResponse> getAllActiveEmp() throws Exception;
}
