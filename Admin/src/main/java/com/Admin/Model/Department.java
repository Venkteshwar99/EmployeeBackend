package com.Admin.Model;

import java.time.LocalDateTime;

public class Department {

	private long deptId;
	private String deptName;
	private boolean isActive;
	private String email;
	private LocalDateTime created;
	private LocalDateTime updated;

	public long getDeptId() {
		return deptId;
	}

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
