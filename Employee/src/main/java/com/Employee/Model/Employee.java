package com.Employee.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.util.Arrays;

@Schema(description = "Employee Model Information")
@Entity
@Table(name = "Employee_Details")
public class Employee {

  @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Employee Id", example = "123")
  @Id
  @Column(name = "emp_Id", nullable = false, unique = true)
  private long empId;

  @Schema(description = "Name", example = "Rohit Sharma")
  @Column(name = "emp_Name", nullable = false)
  private String empName;

  @Schema(description = "Email", example = "rohit@gmail.com")
  @Column(name = "email", nullable = false)
  private String email;

  @Schema(description = "Department", example = "DEV")
  @Column(name = "emp_Dept", nullable = false)
  private String empDept;

  @Schema(description = "Role", example = "Manager")
  @Column(name = "emp_Role", nullable = false)
  private String empRole;

  @Schema(description = "Image")
  @Column(name = "emp_image", length = 100000)
  @Lob
  private byte[] photo;

  @Schema(description = "Active", example = "True")
  @Column(name = "is_Active", nullable = false)
  private boolean isActive;

  @Schema(description = "Location", example = "Mumbai")
  @Column(name = "location", nullable = false)
  private String location;

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
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
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
   * @return the photo
   */
  public byte[] getPhoto() {
    return photo;
  }

  /**
   * @param photo the photo to set
   */
  public void setPhoto(byte[] photo) {
    this.photo = photo;
  }

  /**
   * @return the isActive
   */
  public boolean isActive() {
    return isActive;
  }

  /**
   * @param isActive the isActive to set
   */
  public void setActive(boolean isActive) {
    this.isActive = isActive;
  }

  /**
   * @return the location
   */
  public String getLocation() {
    return location;
  }

  /**
   * @param location the location to set
   */
  public void setLocation(String location) {
    this.location = location;
  }

  @Override
  public String toString() {
    return "Employee [empId="
        + empId
        + ", empName="
        + empName
        + ", email="
        + email
        + ", empDept="
        + empDept
        + ", empRole="
        + empRole
        + ", photo="
        + Arrays.toString(photo)
        + ", isActive="
        + isActive
        + ", location="
        + location
        + "]";
  }

  /**
   * @param empId
   * @param empName
   * @param email
   * @param empDept
   * @param empRole
   * @param photo
   * @param isActive
   * @param location
   */
  public Employee(
      long empId,
      String empName,
      String email,
      String empDept,
      String empRole,
      byte[] photo,
      boolean isActive,
      String location) {
    super();
    this.empId = empId;
    this.empName = empName;
    this.email = email;
    this.empDept = empDept;
    this.empRole = empRole;
    this.photo = photo;
    this.isActive = isActive;
    this.location = location;
  }

  public Employee() {}
}
