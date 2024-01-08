package com.Dept.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Dept.Model.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
	
	  @Query(value = "SELECT MAX(e.dept_Id) From Department_Details e", nativeQuery = true)
	  Long findMaxDeptId();
}


