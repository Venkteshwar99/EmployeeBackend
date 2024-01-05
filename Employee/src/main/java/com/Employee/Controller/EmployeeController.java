package com.Employee.Controller;

import com.Employee.Helper.PdfGenerator;
import com.Employee.Model.Employee;
import com.Employee.Service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller handling employee-related operations. This class defines REST endpoints for managing
 * employee informations.
 */
@CrossOrigin("*")
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
      summary = "Fetch all Active & InActive Employees",
      description =
          "Fetches all Active & InActive Employee entities and their data from data source")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
  @GetMapping(path = "/findAll")
  public ResponseEntity<List<Employee>> getAllEmp() throws Exception {
    List<Employee> list = employeeService.getAllEmp();
    return ResponseEntity.ok(list);
  }

  /**
   * Retrieves a list of all employees.
   *
   * @return A list of employees.
   */
  @Operation(
      summary = "Fetch all Active Employees",
      description = "Fetches all Active Employee entities and their data from data source")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
  @GetMapping(path = "/findAllActive")
  public ResponseEntity<List<Employee>> getAllActiveEmp() throws Exception {
    List<Employee> list = employeeService.getAllActiveEmp();
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
   * Soft Deletes an employee by their ID.
   *
   * @param id The ID of the employee to be deleted.
   * @return True response.
   */
  @Operation(
      summary = "Soft Delete a Employee by ID",
      description = "Soft Delete a Employee object by specifying its ID.")
  @DeleteMapping(path = "/deleteActive/{id}")
  public ResponseEntity<String> softDeleteEmp(@PathVariable("id") long id) {
    try {
      boolean emp = employeeService.softDeleteEmployee(id);
      return ResponseEntity.status(HttpStatus.OK)
          .body(emp + " Employee Soft Deleted Successfully: " + id);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  /**
   * Retrieves an Active employee by their ID.
   *
   * @param id The ID of the employee to retrieve.
   * @return The retrieved employee.
   */
  @Operation(
      summary = "Retrieve a Active Employee by ID",
      description = "Get a Active Employee object by specifying its ID.")
  @GetMapping(path = "/getActiveEmp/{id}")
  public ResponseEntity<Object> getActiveEmpById(@PathVariable("id") long id) {
    try {
      return ResponseEntity.status(HttpStatus.OK).body(employeeService.getActiveEmpById(id));
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
      Optional<List<Employee>> empByName = employeeService.getEmpByName(name);
      return ResponseEntity.status(HttpStatus.OK).body(empByName);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  /**
   * Retrieves employees with page.
   *
   * @return The retrieved employees with Pagination.
   */
  @Operation(
      summary = "Fetch Employees By Pagitnation",
      description = "Fetches Employees with Pagination")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
  @GetMapping(path = "/page")
  public ResponseEntity<Page<Employee>> getPageDetails(Pageable p) throws Exception {

    Page<Employee> pages = employeeService.getPageDetails(p);

    return ResponseEntity.ok(pages);
  }

  /**
   * Add or update an employee's photo.
   *
   * @param id The ID of the employee.
   * @param file The photo file to upload.
   * @return A ResponseEntity with a message indicating the result of the operation.
   */
  @Operation(
      summary = "Add or update an employee's photo",
      description = "Uploads Employee's Image")
  @PostMapping(path = "/photo/{id}")
  public ResponseEntity<String> uploadEmployeePhoto(
      @PathVariable Long id, @RequestParam("file") MultipartFile file) throws Exception {
    try {
      Optional<Employee> optionalEmployee = employeeService.getEmpById(id);

      if (optionalEmployee.isPresent()) {
        Employee employee = optionalEmployee.get();
        if (file != null && !file.isEmpty()) {
          byte[] photoBytes = file.getBytes();
          employee.setPhoto(photoBytes);
          employeeService.setOrUpdateImageEmp(employee);
          return ResponseEntity.ok("Photo uploaded successfully!");
        } else {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is required");
        }
      } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found with Id:" + id);
      }
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading photo");
    }
  }

  /**
   * Get an employee's photo.
   *
   * @param id The ID of the employee.
   * @return A ResponseEntity with the employee's photo as a byte array.
   */
  @Operation(summary = "Get an employee's photo")
  @GetMapping("/photo/{id}")
  public ResponseEntity<?> getEmployeePhoto(@PathVariable Long id) throws Exception {

    try {
      Optional<Employee> optionalEmployee = employeeService.getEmpById(id);
      if (optionalEmployee.isPresent()) {
        Employee employee = optionalEmployee.get();
        byte[] photo = employee.getPhoto();
        //        System.out.println("GettingPhoto:----->" + photo);
        if (photo == null) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND)
              .body("Photo Not Present or is Deleted");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG); // media type based on image format
        return new ResponseEntity<>(photo, headers, HttpStatus.OK);
      } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("Employee Not found with Id: " + id);
      }
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error getting photo");
    }
  }

  /**
   * Delete an employee's photo.
   *
   * @param id The ID of the employee.
   * @return A ResponseEntity with a message indicating the result of the operation.
   */
  @DeleteMapping("/photo/{id}")
  @Operation(summary = "Delete an employee's photo")
  public ResponseEntity<String> deleteEmployeePhoto(@PathVariable Long id) throws Exception {
    try {

      Optional<Employee> optionalEmployee = employeeService.getEmpById(id);
      if (optionalEmployee.isPresent()) {

        Employee employee = optionalEmployee.get();

        if (employee.getPhoto() == null) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Photo is Not Present");
        }
        employee.setPhoto(null);
        employeeService.setOrUpdateImageEmp(employee);
        return ResponseEntity.ok("Photo deleted successfully");
      } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("Employee Not found with Id: " + id);
      }
    } catch (Exception e) {

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting photo");
    }
  }

  /**
   * Generates an Pdf file.
   *
   * @param HttpServletResponse.
   * @return A ResponseEntity with a Pdf file indicating the result of the operation.
   */
  @GetMapping("/export-to-pdf")
  public void generatePdfFile(HttpServletResponse response) throws Exception {

    response.setContentType("application/pdf");

    SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
    String currentDateTime = dateFormat.format(new Date());

    String headerkey = "Content-Disposition";
    String headervalue = "attachment; filename=Employee" + currentDateTime + ".pdf";

    response.setHeader(headerkey, headervalue);
    List<Employee> listofEmployees = employeeService.getAllEmp();
    PdfGenerator generator = new PdfGenerator();

    generator.generate(listofEmployees, response);
  }

  @PatchMapping("/update-status/{id}")
  public ResponseEntity<Boolean> updateEmployeeStatus(
      @PathVariable Long id, @RequestBody Map<String, Boolean> status) throws Exception {

    Optional<Employee> optionalEmployee =
        employeeService.getAllEmp().stream()
            .filter(employee -> employee.getEmpId() == id)
            .findFirst();

    if (optionalEmployee.isPresent()) {
      Employee employee = optionalEmployee.get();
      employee.setActive(status.get("active"));
      employeeService.setOrUpdateImageEmp(employee);
      return ResponseEntity.status(HttpStatus.OK)
          .body(employeeService.getEmpById(id).get().isActive());
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
