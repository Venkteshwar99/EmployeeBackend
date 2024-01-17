package com.Admin.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.Admin.Model.Department;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Admin Department Controller", description = "Admin Department Management API's")
@RestController
@RequestMapping("/admin/api/dept")
public class AdminDepartmentController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Operation(summary = "Fetches all Active & InActive Departments", description = "Fetches all Active & InActive Department entities and their data from data source")
	@GetMapping(path = "/findAll", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getAllDepartments() {
		try {
			String dept = restTemplate.getForObject("http://Department-Service/api/dept/findAll", String.class);
			return ResponseEntity.status(HttpStatus.OK).body("Result: " + dept);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error in getting all employees: " + e.getMessage());
		}

	}
	
	@Operation(summary = "Fetches all Active Departments", description = "Fetches all Active Department entities and their data from data source")
	@GetMapping(path = "/findAllActive", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getAllActiveDepartment() {
		try {
			String dept = restTemplate.getForObject("http://Department-Service/api/dept/findAllActive", String.class);
			return ResponseEntity.status(HttpStatus.OK).body("Result: " + dept);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error in getting all Active departments: " + e.getMessage());
		}

	}

	@Operation(summary = "Retrieve a Active Department by ID", description = "Get a Department object by specifying its ID")
	@GetMapping(path = "/getDept/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getDeptById(@PathVariable("id") long id) {
		try {
			String dept = restTemplate.getForObject("http://Department-Service/api/dept/getDept/" + id,
					String.class);
			return ResponseEntity.status(HttpStatus.OK).body("Result: " + dept);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Error In Getting Department by Id: " + id + "\n" + e.getMessage());
		}
	}

	@Operation(summary = "Retrieve a Active Department by ID", description = "Get a Active Department object by specifying its ID")
	@GetMapping(path = "/getActiveDept/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getActiveDeptById(@PathVariable("id") long id) {
		try {
			Department dept = restTemplate.getForObject("http://Department-Service/api/dept/getActiveDept/" +id,
					Department.class);
			return ResponseEntity.status(HttpStatus.OK).body("Result: " + dept);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Error In Getting Department by Id: " + id + "\n" + e.getMessage());
		}
	}
	
	
	@Operation(summary = "Create a Department", description = "Creates a new Department")
	@PostMapping(path = "/add", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> addDept(@RequestBody Department department) {
		try {
			Department dept = restTemplate.postForObject("http://Department-Service/api/dept/add", department,
					Department.class);
			return ResponseEntity.status(HttpStatus.CREATED).body("Department Created: " + dept);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Error In Creating Department: " + e.getMessage());
		}
	}

	@Operation(summary = "Update a Department by ID", description = "Update a Department object by specifying its ID.")
	@PutMapping(path = "/update/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> upateDept(@PathVariable("id") long id, @RequestBody Department department) {
		try {
			restTemplate.put("http://Department-Service/api/dept/update/" + id, department, Department.class);
			
			return ResponseEntity.status(HttpStatus.OK).body("Updated Department with Id: " + id);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Error in Updating Department: " + e.getMessage());
		}
	}
	
	
	@Operation(summary = "Delete a Department by ID", description = "Delete a Department object by specifying its ID.")
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<String> deleteDept(@PathVariable("id") long id) {
		try {
			restTemplate.delete("http://Department-Service/api/dept/delete/" + id);
			return ResponseEntity.status(HttpStatus.OK).body(" Department Deleted Successfully: " + id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error in Deleting Department: " + e.getMessage());
		}
	}
	
	
	@Operation(summary = "Soft Delete a Department by ID", description = "Soft Delete a Department object by specifying its ID.")
	@DeleteMapping(path = "/deleteActive/{id}")
	public ResponseEntity<String> softDeleteDept(@PathVariable("id") long id) {
		try {
			restTemplate.delete("http://Department-Service/api/dept/deleteActive/" + id);
			return ResponseEntity.status(HttpStatus.OK).body(" Department Soft Deleted Successfully: " + id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error in Deleting Active Department: " + e.getMessage());
		}
	}
	
	
	@PatchMapping("/update-status/{id}")
	public ResponseEntity<?> updateDeptStatus(@PathVariable("id") long id, @RequestBody Map<String, Boolean> status)
			throws Exception {
		try {
			String responseEntity = restTemplate.patchForObject("http://Department-Service/api/dept/update-status/" + id, status, String.class);
			return ResponseEntity.status(200).body(responseEntity);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error in Searching: " + id + " " + e.getMessage());
		}
	}
}
