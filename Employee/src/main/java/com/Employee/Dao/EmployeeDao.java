package com.Employee.Dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.Employee.Model.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long>{
	
	@Query(value="SELECT MAX(e.emp_Id) From Employee_Details e",nativeQuery = true)
	Integer findMaxEmployeeId();

}
