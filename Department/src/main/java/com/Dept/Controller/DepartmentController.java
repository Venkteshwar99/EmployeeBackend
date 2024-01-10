package com.Dept.Controller;

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

import com.Dept.Model.Department;
import com.Dept.Service.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Department Controller", description = "Department Management API's")
@RestController
@RequestMapping("/api/dept")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;

	@Operation(summary = "Fetch all Active & InActive Departments", description = "Fetches all Active & InActive Department entities and their data from data source")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
	@GetMapping(path = "/findAll")
	public ResponseEntity<List<Department>> getAllDept() throws Exception {
		List<Department> list = departmentService.getAllDept();
		return ResponseEntity.ok(list);
	}

	@Operation(summary = "Create a Department", description = "Creates a new Department")
	@PostMapping(path = "/add", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> addDept(@RequestBody Department Department) {
		try {
			Department dept = departmentService.addDept(Department);

			return ResponseEntity.status(HttpStatus.CREATED).body(dept);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@Operation(summary = "Update a Department by ID", description = "Update a Department object by specifying its ID.")
	@PutMapping(path = "/update/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> upateEmp(@PathVariable("id") long id,@RequestBody Department Department) {
		try {
			Department dept = departmentService.updateDept(Department, id);
			System.out.println("dept: "+dept);
			return ResponseEntity.status(HttpStatus.OK).body("Department Updated: "+dept);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	/**
	 * Deletes an Department by their ID.
	 *
	 * @param id The ID of the Department to be deleted.
	 * @return A no content response.
	 */
	@Operation(summary = "Delete a Department by ID", description = "Delete a Department object by specifying its ID.")
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<String> deletEmp(@PathVariable("id") long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(departmentService.deleteDept(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@Operation(summary = "Retrieve a Department by ID", description = "Get a Department object by specifying its ID.")
	@GetMapping(path = "/getDept/{id}")
	public ResponseEntity<Object> getEmpById(@PathVariable("id") long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(departmentService.getDeptById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
