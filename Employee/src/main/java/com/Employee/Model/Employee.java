package com.Employee.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Employee_Details")
public class Employee {

	@Id
	@Column(name="emp_Id",nullable =false, unique=true)
	private int empId;
	@Column(name="emp_Name",nullable =false)
	private String empName;
	@Column(name="emp_Dept",nullable =false)
	private String empDept;
	@Column(name="emp_Role",nullable =false)
	private String empRole;
	
	@JsonProperty("empId")
	public int getEmpId() {
		return empId;
	}
	@JsonIgnore
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpDept() {
		return empDept;
	}
	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}
	public String getEmpRole() {
		return empRole;
	}
	public void setEmpRole(String empRole) {
		this.empRole = empRole;
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empDept=" + empDept + ", empRole=" + empRole
				+ "]";
	}
	public Employee(int empId, String empName, String empDept, String empRole) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empDept = empDept;
		this.empRole = empRole;
	}

	
public Employee() {

}	
}
