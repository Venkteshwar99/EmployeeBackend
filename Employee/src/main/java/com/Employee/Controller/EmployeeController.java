package com.Employee.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Employee.Model.Employee;
import com.Employee.Service.EmployeeServiceImpl;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	EmployeeServiceImpl employeeService;

	@GetMapping("/hello")
	public String Hey() {
		return "Hello";
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Employee>> getAllEmp() {
		try {
			List<Employee> employee = employeeService.getEmp();
			return ResponseEntity.ok(employee);

		} catch (Exception e) {
			e.getStackTrace();
			return null;
		}
	}

	@PostMapping("/add")
	public ResponseEntity<Employee> addEmp(@RequestBody Employee employee) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.addEmp(employee));
		} catch (Exception e) {
			return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<Object> upateEmp(@RequestBody Employee employee) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateEmp(employee));
		} catch (Exception e) {
			e.getStackTrace();
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
