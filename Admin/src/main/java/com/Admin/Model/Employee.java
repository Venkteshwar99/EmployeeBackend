package com.Admin.Model;

import java.time.LocalDateTime;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Employee {

  private long empId;

  private String firstName;
  
  private String lastName;
  
  private String fullName;
 
  private String email;
 
  private String empDept;

  private String empRole;

  private byte[] photo;
  
  private boolean isActive;

  private String location;

  private LocalDateTime created;
  
  private LocalDateTime updated;

public Employee() {
	
}

public Employee(long empId, String firstName, String lastName, String fullName, String email, String empDept,
		String empRole, byte[] photo, boolean isActive, String location, LocalDateTime created, LocalDateTime updated) {
	super();
	this.empId = empId;
	this.firstName = firstName;
	this.lastName = lastName;
	this.fullName = fullName;
	this.email = email;
	this.empDept = empDept;
	this.empRole = empRole;
	this.photo = photo;
	this.isActive = isActive;
	this.location = location;
	this.created = created;
	this.updated = updated;
}

public long getEmpId() {
	return empId;
}

@JsonIgnore
public void setEmpId(long empId) {
	this.empId = empId;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getFullName() {
	return fullName;
}

public void setFullName(String fullName) {
	this.fullName = fullName;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
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

public byte[] getPhoto() {
	return photo;
}

public void setPhoto(byte[] photo) {
	this.photo = photo;
}

public boolean isActive() {
	return isActive;
}

public void setActive(boolean isActive) {
	this.isActive = isActive;
}

public String getLocation() {
	return location;
}

public void setLocation(String location) {
	this.location = location;
}

public LocalDateTime getCreated() {
	return created;
}

public void setCreated(LocalDateTime created) {
	this.created = created;
}

public LocalDateTime getUpdated() {
	return updated;
}

public void setUpdated(LocalDateTime updated) {
	this.updated = updated;
}

@Override
public String toString() {
	return "Employee [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName + ", fullName=" + fullName
			+ ", email=" + email + ", empDept=" + empDept + ", empRole=" + empRole + ", photo=" + Arrays.toString(photo)
			+ ", isActive=" + isActive + ", location=" + location + ", created=" + created + ", updated=" + updated
			+ "]";
}
  

}