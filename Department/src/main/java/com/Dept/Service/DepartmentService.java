package com.Dept.Service;

import java.util.List;
import java.util.Optional;

import com.Dept.Model.Department;

public interface DepartmentService {
	
	 public List<Department> getAllDept() throws Exception;

	  public Department addDept(Department dept) throws Exception;

	  public Object updateDept(Department updatedDept, long id) throws Exception;

	  public String deleteDept(long id) throws Exception;

	  public Optional<Department> getDeptById(long id) throws Exception;
	

}
