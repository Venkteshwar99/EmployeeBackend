package com.Dept.Model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Department_Details")
public class Department {

	@Id
	@Column(name = "dept_Id", nullable = false, unique = true)
	private long deptId;

	@Column(name = "dept_Name", nullable = false)
	private String deptName;

	@Schema(description = "Active", example = "True")
	@Column(name = "is_Active", nullable = false)
	private boolean isActive;

	@Schema(description = "Email", example = "Dev@gmail.com")
	@Column(name = "email", nullable = false)
	private String email;

	@Schema(description = "Created")
	@Column(name = "created", nullable = false)
	private LocalDateTime created;

	@Schema(description = "Updated")
	@Column(name = "updated", nullable = false)
	private LocalDateTime updated;

	@JsonProperty("deptId")
	public long getDeptId() {
		return deptId;
	}

	@JsonIgnore
	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Department(long deptId, String deptName, boolean isActive, String email, LocalDateTime created,
			LocalDateTime updated) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
		this.isActive = isActive;
		this.email = email;
		this.created = created;
		this.updated = updated;
	}

	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", deptName=" + deptName + ", isActive=" + isActive + ", email=" + email
				+ ", created=" + created + ", updated=" + updated + "]";
	}

	public Department() {

	}
}
