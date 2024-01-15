package com.Dept.Service;

import java.time.LocalDateTime;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Dept.Model.Department;
import com.Dept.Repo.DepartmentRepo;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentRepo repo;

	@Override
	public List<Department> getAllDept() throws Exception {
		try {
			List<Department> list = repo.findAll();
			if (!list.isEmpty()) {
				return list;
			}
			return null;
		} catch (Exception e) {
			e.getMessage();
			throw new Exception("Error while retriving Department list");
		}
	}

	@Override
	public Department addDept(Department dept) throws Exception {

		if (!repo.existsById(dept.getDeptId())) {
			dept.setDeptId(generateUniqueID());
			dept.setActive(true);
			dept.setCreated(LocalDateTime.now());
			dept.setUpdated(LocalDateTime.now());
			return repo.save(dept);

		} else {
			throw new Exception("Error while retriving Adding Department");
		}
	}

	public Department updateDept(Department updatedDept, long id) throws Exception {
		try {
			Department dept = repo.findById(id).orElseThrow(() -> new Exception("Department Id not found"));
			dept.setDeptName(updatedDept.getDeptName());
			dept.setActive(updatedDept.isActive());
			dept.setEmail(updatedDept.getEmail());
			if (dept.getCreated() == null) {
				dept.setCreated(LocalDateTime.now());
			}
			dept.setUpdated(LocalDateTime.now());

			return repo.save(dept);

		} catch (Exception e) {
			e.getMessage();
			throw new RuntimeException("Error while updating Department");
		}
	}

	public String deleteDept(long id) throws Exception {
		if (repo.existsById(id)) {
			repo.deleteById(id);
			return "Department: " + id + " Deleted";
		} else {
			throw new Exception("Department Not Found with Id: " + id);
		}
	}

	@Override
	public Optional<Department> getDeptById(long id) throws Exception {
		Optional<Department> dept = repo.findById(id);
		if (dept.isPresent()) {
			return dept;
		} else {
			throw new Exception("Department Not found with Id:" + id);
		}
	}

	/**
	 * getAllActiveEmp() method Retrieves a list of all employees.
	 *
	 * @return A list of Active employees.
	 * @throws Exception
	 */
	@Override
	public List<Department> getAllActiveDept() throws Exception {
		try {
			List<Department> list = repo.findAllByIsActiveTrue();
			if (!list.isEmpty()) {
				return list;
			} else {
				throw new Exception("No content");
			}
		} catch (Exception e) {
			e.getMessage();
			throw new Exception("Error while retriving Department list");
		}
	}

	/**
	 * softDeleteDept method Deletes an employee by setting isAcrive to false.
	 *
	 * @param id The ID of the employee to be soft deleted.
	 * @return True If the employee with the given ID is found & soft deleted,false
	 *         otherwise.
	 */
	public boolean softDeleteDept(long id) throws Exception {
		Optional<Department> opDept = getDeptById(id);

		if (opDept.isPresent()) {
			Department employee = opDept.get();
			employee.setActive(false);
			repo.save(employee);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Optional<Department> getActiveDeptById(long id) throws Exception {
		try {
			Optional<Department> activeDept = repo.findByIdAndIsActiveTrue(id);
			return Optional
					.ofNullable(activeDept.orElseThrow(() -> new Exception("Department Not found or is In Active")));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	 @Override
	  public Department setOrUpdateStatus(Department dept) throws Exception {
			if (repo.existsById(dept.getDeptId())) {
			  return repo.save(dept);
			} else {
			  throw new Exception("Unable to update status");
			}
		} 
	
	private long generateUniqueID() {
		Long deptId = repo.findMaxDeptId();
		return (deptId != null) ? deptId + 1 : 1;
	}
}
