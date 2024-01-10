package com.Dept.Service;
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
			return repo.save(dept);

		} else {
			throw new Exception("Error while retriving Adding Department");
		}
	}

	public Department updateDept(Department updatedDept, long id) throws Exception {
		try {
			Department dept = repo.findById(id).orElseThrow(() -> new Exception("Department Id not found"));
			dept.setDeptName(updatedDept.getDeptName());
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
			throw new Exception("Error in Deleting Department");
		}
	}

	@Override
	public Optional<Department> getDeptById(long id) throws Exception {
		try {
			return Optional.ofNullable(repo.findById(id).orElseThrow(() -> new Exception("Department Id not found")));
		} catch (Exception e) {
			throw new Exception("Error while retriving Department by ID", e);
		}
	}

	private long generateUniqueID() {
		Long deptId = repo.findMaxDeptId();
		return (deptId != null) ? deptId + 1 : 1;
	}
}
