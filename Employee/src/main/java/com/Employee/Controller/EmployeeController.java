package com.Employee.Controller;

import com.Employee.Model.Employee;
import com.Employee.Service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller handling employee-related operations. This class defines REST endpoints for managing
 * employee informations.
 */
@Tag(name = "Employee Controller", description = "Employee Management API's")
@RestController
@RequestMapping(path = "/api")
public class EmployeeController {

  @Autowired EmployeeService employeeService;

  /*@GetMapping("/hello")
  public String Hey() {
    return "Hello";
  }*/

  /**
   * Retrieves a list of all employees.
   *
   * @return A list of employees.
   */
  @Operation(
      summary = "Fetch all Employees",
      description = "Fetches all Employee entities and their data from data source")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
  @GetMapping(path = "/findAll")
  public ResponseEntity<List<Employee>> getAllEmp() throws Exception {
    List<Employee> list = employeeService.getAllEmp();
    return ResponseEntity.ok(list);
  }

  /**
   * Creates a new employee.
   *
   * @param employee The employee to be created.
   * @return The created employee.
   */
  @Operation(summary = "Create a Employee", description = "Creates a new Employee")
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
  @Operation(
      summary = "Update a Employee by ID",
      description = "Update a Employee object by specifying its ID.")
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
  @Operation(
      summary = "Delete a Employee by ID",
      description = "Delete a Employee object by specifying its ID.")
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
  @Operation(
      summary = "Retrieve a Employee by ID",
      description = "Get a Employee object by specifying its ID.")
  @GetMapping(path = "/getEmp/{id}")
  public ResponseEntity<Object> getEmpById(@PathVariable("id") long id) {
    try {
      return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmpById(id));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  /**
   * Retrieves an employee by their name.
   *
   * @param name
   * @return The retrieved employee.
   */
  @Operation(summary = "Search Employee by Name", description = "Search a Employees by Name.")
  @GetMapping(path = "/getEmp/name")
  public ResponseEntity<?> getEmpByName(@RequestParam("name") String name) {
    try {
      Optional<Employee> empByName = employeeService.getEmpByName(name);
      return ResponseEntity.status(HttpStatus.OK).body(empByName);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }
}
