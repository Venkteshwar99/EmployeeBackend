package com.Employee.Service;

import com.Employee.Dao.EmployeeDao;
import com.Employee.Exception.EmployeeException;
import com.Employee.Model.Employee;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/** Service managing employee-related business logic. */
@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired EmployeeDao dao;

  /**
   * getAllEmp() method Retrieves a list of all employees.
   *
   * @return A list of employees.
   */
  @Override
  public List<Employee> getAllEmp() throws Exception {
    try {
      List<Employee> list = dao.findAll();
      if (!list.isEmpty()) {
        return list;
      } else {
        throw EmployeeException.NoContentException();
      }
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
      employee.setEmpId(generateUniqueID());
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
  public Object updateEmp(Employee updatedEmployee, long id) throws Exception {
    try {
      Employee employee =
          dao.findById(id).orElseThrow(() -> EmployeeException.notFoundException(id));

      employee.setEmpName(updatedEmployee.getEmpName());
      employee.setEmpDept(updatedEmployee.getEmpDept());
      employee.setEmpRole(updatedEmployee.getEmpRole());

      return dao.save(employee);
    } catch (Exception e) {
      e.getMessage();
      throw new RuntimeException("Error while updating employee");
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
   * getEmpById method Retrieves an employee by their ID.
   *
   * @param id The ID of the employee to retrieve.
   * @return The retrieved employee.
   */
  @Override
  public Object getEmpById(long id) throws Exception {
    try {
      return dao.findById(id);
    } catch (Exception e) {
      throw new Exception("Error while retriving Employee by ID", e);
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
  public Optional<Employee> getEmpByName(String name) {
    return Optional.ofNullable(
        dao.empName(name).orElseThrow(() -> new EmployeeException("Employee Not found")));
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
