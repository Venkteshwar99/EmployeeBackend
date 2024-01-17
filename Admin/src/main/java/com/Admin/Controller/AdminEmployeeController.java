package com.Admin.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.client.RestTemplate;

import com.Admin.Model.Employee;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Admin Employee Controller", description = "Admin Employee Management API's")
@RestController
@RequestMapping("/admin/api/emp")
public class AdminEmployeeController {

	@Autowired
	private RestTemplate restTemplate;

	@Operation(summary = "Fetch all Active Employees", description = "Fetches all Active Employee entities and their data from data source")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
	@GetMapping(path = "/findAllActive", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getAllProducts() {
		try {
			String emp = restTemplate.getForObject("http://Employee-Service/api/emp/findAll", String.class);
			return ResponseEntity.status(HttpStatus.OK).body("Result: " + emp);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error in getting all employees: " + e.getMessage());
		}

	}

	@Operation(summary = "Retrieve a Active Employee by ID", description = "Get a Active Employee object by specifying its ID")
	@GetMapping(path = "/getActiveEmp/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getActiveEmpById(@PathVariable("id") long id) {
		try {
			String Emp = restTemplate.getForObject("http://Employee-Service/api/emp/getActiveEmp/"+id,
					String.class);
			return ResponseEntity.status(HttpStatus.OK).body("Result: " + Emp);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Error In Getting Employee by Id: " + id + "\n" + e.getMessage());
		}
	}

	@Operation(summary = "Create a Employee", description = "Creates a new Employee")
	@PostMapping(path = "/add", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> addEmp(@RequestBody Employee employee) {
		try {
			Employee emp = restTemplate.postForObject("http://Employee-Service/api/emp/add", employee, Employee.class);
			return ResponseEntity.status(HttpStatus.CREATED).body("Employee Created: " + emp);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error In Creating Employee: " + e.getMessage());
		}
	}

	@Operation(summary = "Update a Employee by ID", description = "Update a Employee object by specifying its ID.")
	@PutMapping(path = "/update/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> upateEmp(@RequestBody Employee employee, @PathVariable long id) {
		try {

			restTemplate.put("http://Employee-Service/api/emp/update/" + id, employee, Employee.class);
			return ResponseEntity.status(HttpStatus.OK).body("Updating Employee");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in Updating Employee: " + e.getMessage());
		}
	}

	@Operation(summary = "Soft Delete a Employee by ID", description = "Soft Delete a Employee object by specifying its ID.")
	@DeleteMapping(path = "/deleteActive/{id}")
	public ResponseEntity<String> softDeleteEmp(@PathVariable("id") long id) {
		try {
			restTemplate.delete("http://Employee-Service/api/emp/deleteActive/" + id);
			return ResponseEntity.status(HttpStatus.OK).body(" Employee Soft Deleted Successfully: " + id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error in Deleting Employee: " + e.getMessage());
		}
	}

	@Operation(summary = "Search Employee by Id, Name, Department, Role, Location", description = "Search a Employees by Id, Name, Department, Role, Location.")
	@GetMapping(path = "/getEmp/name", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEmpByName(@RequestParam("name") String name) {
		try {

			String Emp = restTemplate.getForObject("http://Employee-Service/api/emp/getEmp/name?name=" + name,
					String.class);

			return ResponseEntity.status(HttpStatus.OK).body("Result: " + Emp);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Error in Searching: " + name + " " + e.getMessage());
		}
	}

	@PatchMapping("/update-status/{id}")
	public ResponseEntity<?> updateEmployeeStatus(@PathVariable("id") long id, @RequestBody Map<String, Boolean> status)
			throws Exception {
		try {
			String responseEntity = restTemplate.patchForObject("http://Employee-Service/api/emp/update-status/" + id, status, String.class);
			return ResponseEntity.status(200).body(responseEntity);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error in Searching: " + id + " " + e.getMessage());
		}
	}
}
