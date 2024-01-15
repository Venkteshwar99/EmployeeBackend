package com.Dept.Repo;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Dept.Model.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {

	@Query(value = "SELECT MAX(e.dept_Id) From Department_Details e", nativeQuery = true)
	Long findMaxDeptId();

	@Query(value = "SELECT * FROM Department_Details e WHERE e.dept_Id = :id AND e.is_Active = true", nativeQuery = true)
	Optional<Department> findByIdAndIsActiveTrue(@Param("id") long id);

	List<Department> findAllByIsActiveTrue();
}
