package com.Employee.Controller;

import com.Employee.Model.Employee;
import com.Employee.Service.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller handling employee-related operations. This class defines REST endpoints for managing
 * employee informations.
 */
@RestController
@RequestMapping(path = "/api")
public class EmployeeController {

  @Autowired EmployeeService employeeService;

  @GetMapping("/hello")
  public String Hey() {
    return "Hello";
  }

  /**
   * Retrieves a list of all employees.
   *
   * @return A list of employees.
   */
  @GetMapping(path = "/findAll")
  public List<Employee> getAllEmp() throws Exception {
    List<Employee> list = employeeService.getAllEmp();
    return list;
  }

  /**
   * Creates a new employee.
   *
   * @param employee The employee to be created.
   * @return The created employee.
   */
  @PostMapping(
      path = "/add",
      consumes = {MediaType.APPLICATION_JSON_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Object> addEmp(@RequestBody Employee employee) {
    try {
      return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.addEmp(employee));

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  /**
   * Updates an existing employee.
   *
   * @param id The ID of the employee to be updated.
   * @param updatedEmployee The updated employee details.
   * @return The updated employee.
   */
  @PutMapping(
      path = "/update/{id}",
      consumes = {MediaType.APPLICATION_JSON_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Object> upateEmp(@RequestBody Employee employee, @PathVariable long id) {
    try {
      return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateEmp(employee, id));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  /**
   * Deletes an employee by their ID.
   *
   * @param id The ID of the employee to be deleted.
   * @return A no content response.
   */
  @DeleteMapping(path = "/delete/{id}")
  public ResponseEntity<String> deletEmp(@PathVariable("id") long id) {
    try {
      return ResponseEntity.status(HttpStatus.OK).body(employeeService.deleteEmp(id));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  /**
   * Retrieves an employee by their ID.
   *
   * @param id The ID of the employee to retrieve.
   * @return The retrieved employee.
   */
  @GetMapping(path = "/getEmp/{id}" + "")
  public ResponseEntity<Object> getEmpById(@PathVariable("id") long id) {
    try {
      return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmpById(id));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }
}
