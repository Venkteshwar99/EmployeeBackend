package com.Employee.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Arrays;

@Schema(description = "Employee Model Information")
@Entity
@Table(name = "Employee_Details")
public class Employee {

  @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Employee Id", example = "123")
  @Id
  @Column(name = "emp_Id", nullable = false, unique = true)
  private long empId;

  @Schema(description = "First Name", example = "Rohit")
  @Column(name = "first_Name", nullable = false)
  private String firstName;

  @Schema(description = "Last Name", example = "Sharma")
  @Column(name = "last_Name", nullable = false)
  private String lastName;

  @Schema(description = "Full Name", example = "Sharma, Rohit")
  @Column(name = "full_Name", nullable = false)
  private String fullName;

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

  @Schema(description = "Created", example = "166000201")
  @Column(name = "created", nullable = false)
  private LocalDateTime created;

  @Schema(description = "Updated", example = "166000201")
  @Column(name = "updated", nullable = false)
  private LocalDateTime updated;

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
   * @return the firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * @param firstName the firstName to set
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @param lastName the lastName to set
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * @return the fullName
   */
  public String getFullName() {
    return fullName;
  }

  /**
   * @param fullName the fullName to set
   */
  public void setFullName() {
    this.fullName = generateFullName();
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

  /**
   * @return the created
   */
  public LocalDateTime getCreated() {
    return created;
  }

  /**
   * @param created the created to set
   */
  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  /**
   * @return the updated
   */
  public LocalDateTime getUpdated() {
    return updated;
  }

  /**
   * @param updated the updated to set
   */
  public void setUpdated(LocalDateTime updated) {
    this.updated = updated;
  }

  @Override
  public String toString() {
    return "Employee [empId="
        + empId
        + ", firstName="
        + firstName
        + ", lastName="
        + lastName
        + ", fullName="
        + fullName
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
        + ", created="
        + created
        + ", updated="
        + updated
        + "]";
  }

  /**
   * @param empId
   * @param firstName
   * @param lastName
   * @param fullName
   * @param email
   * @param empDept
   * @param empRole
   * @param photo
   * @param isActive
   * @param location
   * @param created
   * @param updated
   */
  public Employee(
      long empId,
      String firstName,
      String lastName,
      String fullName,
      String email,
      String empDept,
      String empRole,
      byte[] photo,
      boolean isActive,
      String location,
      LocalDateTime created,
      LocalDateTime updated) {
    super();
    this.empId = empId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.fullName = generateFullName();
    this.email = email;
    this.empDept = empDept;
    this.empRole = empRole;
    this.photo = photo;
    this.isActive = isActive;
    this.location = location;
    this.created = created;
    this.updated = updated;
  }

  private String generateFullName() {
    return lastName + ", " + firstName;
  }

  public Employee() {}
}
