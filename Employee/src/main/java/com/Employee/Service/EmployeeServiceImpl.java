package com.Employee.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Employee.Dao.EmployeeDao;
import com.Employee.Exception.EmployeeException;
import com.Employee.Model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao dao;

	@Override
	public List<Employee> getEmp() {

		return dao.findAll();
	}

	@Override
	public Employee addEmp(Employee employee) {
		if (!dao.existsById((long) employee.getEmpId())) {
			employee.setEmpId(generateUniqueID());
			return dao.save(employee);
		} else {
			throw EmployeeException.updateException(employee.getEmpId());
		}
	}

	public Object updateEmp(Employee employee) {

		if (dao.existsById((long) employee.getEmpId())) {
			return dao.save(employee);
		} else {
			throw EmployeeException.notFoundException(employee.getEmpId());
		}
	}

	private int generateUniqueID() {
		Integer empId = dao.findMaxEmployeeId();
		return (empId!=null) ? empId+1:1;
}
}
