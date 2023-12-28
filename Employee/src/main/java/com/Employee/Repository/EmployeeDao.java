package com.Employee.Repository;

import com.Employee.Model.Employee;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

  //  @Query(
  //      value = "SELECT * FROM Employee_Details e WHERE e.full_Name LIKE %:name%",
  //      nativeQuery = true)

  @Query(
      value =
          "SELECT * FROM Employee_Details e WHERE "
              + "e.emp_Id LIKE %:name% OR "
              + "e.full_Name LIKE %:name% OR "
              + "e.location LIKE %:name% OR "
              + "e.emp_Dept LIKE %:name% OR "
              + "e.emp_Role LIKE %:name% ",
      nativeQuery = true)
  Optional<List<Employee>> empName(@Param("name") String name);

  @Query(
      value = "SELECT * FROM Employee_Details e WHERE e.emp_Id = :id AND e.is_Active = true",
      nativeQuery = true)
  Optional<Employee> findByIdAndIsActiveTrue(@Param("id") long id);

  List<Employee> findAllByIsActiveTrue();
}
