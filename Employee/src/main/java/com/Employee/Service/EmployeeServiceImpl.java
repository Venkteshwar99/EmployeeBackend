package com.Employee.Service;

import com.Employee.Exception.EmployeeException;
import com.Employee.Model.ApiResponse;
import com.Employee.Model.Department;
import com.Employee.Model.Employee;
import com.Employee.Repository.EmployeeDao;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/** Service managing employee-related business logic. */
@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired EmployeeDao dao;

  @Autowired RestTemplate restTemplate;

  /**
   * getAllEmp() method Retrieves a list of all employees.
   *
   * @return A list of employees.
   */
  @Override
  public List<ApiResponse> getAllEmp() throws Exception {
    try {

      List<Employee> employees = dao.findAll();

      List<ApiResponse> responses = new ArrayList<>();

      for (Employee employee : employees) {
        long deptId = employee.getDeptId();
        Department dept =
            restTemplate.getForObject(
                "http://Department-Service/api/dept/getDept/" + deptId, Department.class);
        System.out.println("Department: " + dept);

        ApiResponse apiResponse = new ApiResponse(employee, dept);
        responses.add(apiResponse);
      }
      return responses;
    } catch (Exception e) {
      e.getMessage();
      throw new Exception("Error while retriving employees list");
    }
  }

  /**
   * getAllActiveEmp() method Retrieves a list of all employees.
   *
   * @return A list of Active employees.
   * @throws Exception
   */
  @Override
  public List<ApiResponse> getAllActiveEmp() throws Exception {
    try {
      List<Employee> list = dao.findAllByIsActiveTrue();

      List<ApiResponse> responses = new ArrayList<>();

      for (Employee employee : list) {
        long deptId = employee.getDeptId();
        Department dept =
            restTemplate.getForObject(
                "http://Department-Service/api/dept/getDept/" + deptId, Department.class);
        System.out.println("Department: " + dept);

        ApiResponse apiResponse = new ApiResponse(employee, dept);
        responses.add(apiResponse);
      }
      return responses;
    } catch (Exception e) {
      e.getMessage();
      throw new Exception("Error while retriving employees list");
    }
  }

  /**
   * addEmp method Creates a new employee.
   *
   * @param employee The employee to be created.
   * @return The created employee.
   */
  @Override
  public Employee addEmp(Employee employee) {

    if (!dao.existsById(employee.getEmpId())) {

      Department dept =
          restTemplate.getForObject(
              "http://Department-Service/api/dept/getDept/" + employee.getDeptId(),
              Department.class);

      employee.setEmpId(generateUniqueID());
      employee.setFullName();
      employee.setDeptId(dept.getDeptId());
      employee.setActive(true);
      employee.setCreated(LocalDateTime.now());
      employee.setUpdated(LocalDateTime.now());

      return dao.save(employee);

    } else {
      throw EmployeeException.updateException(employee.getEmpId());
    }
  }

  /**
   * updateEmp method Updates an existing employee.
   *
   * @param id The ID of the employee to be updated.
   * @param updatedEmployee The updated employee details.
   * @return The updated employee.
   */
  public Employee updateEmp(Employee updatedEmployee, long id) throws Exception {

    Employee employee = dao.findById(id).orElseThrow(() -> EmployeeException.notFoundException(id));

    Department dept =
        restTemplate.getForObject(
            "http://Department-Service/api/dept/getDept/" + updatedEmployee.getDeptId(),
            Department.class);

    System.out.println(dept.getDeptId());
    employee.setFirstName(updatedEmployee.getFirstName());
    employee.setLastName(updatedEmployee.getLastName());
    employee.setFullName();

    employee.setDeptId(dept.getDeptId());
    employee.setEmpRole(updatedEmployee.getEmpRole());
    employee.setActive(updatedEmployee.isActive());
    employee.setEmail(updatedEmployee.getEmail());
    employee.setLocation(updatedEmployee.getLocation());
    if (employee.getCreated() == null) {
      employee.setCreated(LocalDateTime.now());
    }
    employee.setUpdated(LocalDateTime.now());

    return dao.save(employee);
  }

  /**
   * softDeleteEmployee method Deletes an employee by setting isAcrive to false.
   *
   * @param id The ID of the employee to be soft deleted.
   * @return True If the employee with the given ID is found & soft deleted,false otherwise.
   */
  public boolean softDeleteEmployee(long id) throws Exception {
    Optional<Employee> opEmployee = getEmpById(id);

    if (opEmployee.isPresent()) {
      Employee employee = opEmployee.get();
      employee.setActive(false);
      dao.save(employee);
      return true;
    } else {
      return false;
    }
  }

  /**
   * deleteEmp method Deletes an employee by their ID.
   *
   * @param id The ID of the employee to be deleted.
   * @throws EmployeeNotFoundException If the employee with the given ID is not found.
   */
  public String deleteEmp(long id) {
    if (dao.existsById(id)) {
      dao.deleteById(id);
      return "Employee: " + id + " Deleted";
    } else {
      throw EmployeeException.notFoundException(id);
    }
  }

  /**
   * getActiveEmpById method Retrieves an Active employee by their ID.
   *
   * @param id The ID of the employee to retrieve.
   * @return The retrieved Active employee.
   */
  @Override
  public ApiResponse getActiveEmpById(long id) throws Exception {
    try {

      Employee activeEmp =
          dao.findByIdAndIsActiveTrue(id)
              .orElseThrow(() -> new EmployeeException("Employee Not found or is In Active"));

      ResponseEntity<Department> responseEntity =
          restTemplate.getForEntity(
              "http://Department-Service/api/dept/getDept/" + activeEmp.getDeptId(),
              Department.class);

      Department dept = responseEntity.getBody();
      ApiResponse apiResponse = new ApiResponse();
      apiResponse.setEmployee(activeEmp);
      apiResponse.setDepartment(dept);

      return apiResponse;
    } catch (Exception e) {
      throw new EmployeeException(e.getMessage());
    }
  }

  /**
   * getEmpById method Retrieves an employee by their ID.
   *
   * @param id The ID of the employee to retrieve.
   * @return The retrieved employee.
   */
  @Override
  public Optional<Employee> getEmpById(long id) throws Exception {
    Optional<Employee> emp = dao.findById(id);
    if (emp.isPresent()) {
      return emp;
    } else {
      throw new Exception("Employee Not found with Id:" + id);
    }
  }

  /**
   * getEmpByName method Retrieves an employee by their name.
   *
   * @param name
   * @return The retrieved employee.
   * @throws Exception
   */
  @Override
  public List<ApiResponse> getEmpByName(String name) throws Exception {
    try {
      Optional<List<Employee>> op = dao.empName(name);
      List<ApiResponse> responses = new ArrayList<>();

      for (Employee employee : op.get()) {
        long deptId = employee.getDeptId();
        Department dept =
            restTemplate.getForObject(
                "http://Department-Service/api/dept/getDept/" + deptId, Department.class);
        System.out.println("Department: " + dept);

        ApiResponse apiResponse = new ApiResponse(employee, dept);
        responses.add(apiResponse);
      }
      return responses;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new Exception(
          "Employee Not found with name: " + name, EmployeeException.NoContentException());
    }
  }

  /**
   * getPageDetails method retrieves employees with pagination.
   *
   * @param pageable
   * @return The retrieved employees.
   */
  @Override
  public Page<Employee> getPageDetails(Pageable p) {
    Page<Employee> pages = dao.findAll(p);
    System.out.println("Page Size:" + pages.getSize());
    if (pages.getSize() <= 0) {
      throw new EmployeeException("No result");
    } else {
      return pages;
    }
  }

  @Override
  public Employee setOrUpdateImageEmp(Employee employee) {
    if (dao.existsById(employee.getEmpId())) {
      return dao.save(employee);
    } else {
      throw EmployeeException.updateException(employee.getEmpId());
    }
  }

  /**
   * generateUniqueID() method generates unique Employee ID. This method retrives maximum employee
   * ID from Dao and increments it to get a new Employee ID
   *
   * @return A unique Employee ID.
   */
  private long generateUniqueID() {
    Long empId = dao.findMaxEmployeeId();
    return (empId != null) ? empId + 1 : 1;
  }
}
