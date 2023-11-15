package com.Employee.Model;

public class Employee {

	private int empId;
	private String empName;
	private String empDept;
	private String empRole;

	public int getEmpId() {
		return empId;
	}

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

	public Employee() {

	}

	public Employee(int empId, String empName, String empDept, String empRole) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empDept = empDept;
		this.empRole = empRole;
	}
}
