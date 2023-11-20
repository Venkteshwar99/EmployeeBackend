package com.Employee.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Schema(description = "Employee Model Information")
@Entity
@Table(name = "Employee_Details")
public class Employee {

  @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Employee Id", example = "123")
  @Id
  @Column(name = "emp_Id", nullable = false, unique = true)
  private long empId;

  @Schema(description = "Employee Name", example = "Rohit Sharma")
  @Column(name = "emp_Name", nullable = false)
  private String empName;

  @Schema(description = "Employee Department", example = "DEV")
  @Column(name = "emp_Dept", nullable = false)
  private String empDept;

  @Schema(description = "Employee Role", example = "Manager")
  @Column(name = "emp_Role", nullable = false)
  private String empRole;

  /**
   * @return the empId
   */
  @JsonProperty("empId")
  public long getEmpId() {
    return empId;
  }

  /**
   * @param empId the empId to set
   */
  @JsonIgnore
  public void setEmpId(long empId) {
    this.empId = empId;
  }

  /**
   * @return the empName
   */
  public String getEmpName() {
    return empName;
  }

  /**
   * @param empName the empName to set
   */
  public void setEmpName(String empName) {
    this.empName = empName;
  }

  /**
   * @return the empDept
   */
  public String getEmpDept() {
    return empDept;
  }

  /**
   * @param empDept the empDept to set
   */
  public void setEmpDept(String empDept) {
    this.empDept = empDept;
  }

  /**
   * @return the empRole
   */
  public String getEmpRole() {
    return empRole;
  }

  /**
   * @param empRole the empRole to set
   */
  public void setEmpRole(String empRole) {
    this.empRole = empRole;
  }

  /**
   * @param empId
   * @param empName
   * @param empDept
   * @param empRole
   */
  public Employee(long empId, String empName, String empDept, String empRole) {
    super();
    this.empId = empId;
    this.empName = empName;
    this.empDept = empDept;
    this.empRole = empRole;
  }

  public Employee() {}

  @Override
  public String toString() {
    return "Employee [empId="
        + empId
        + ", empName="
        + empName
        + ", empDept="
        + empDept
        + ", empRole="
        + empRole
        + "]";
  }
}
