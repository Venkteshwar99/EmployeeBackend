package com.Employee.Dao;

import com.Employee.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/** Repository interface for accessing Employee related data. */
public interface EmployeeDao extends JpaRepository<Employee, Long> {

  /**
   * Finds the maximum employee ID from the Employee Details table. The MAX() function is used to
   * find the highest employee ID. Note: The nativeQuery attribute is set to true, indicating that
   * this is a native SQL query.
   *
   * @return The maximum employee ID or null if no employees exist.
   */
  @Query(value = "SELECT MAX(e.emp_Id) From Employee_Details e", nativeQuery = true)
  Long findMaxEmployeeId();
}
