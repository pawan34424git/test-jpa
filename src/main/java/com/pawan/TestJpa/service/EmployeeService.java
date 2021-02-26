package com.pawan.TestJpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawan.TestJpa.domain.Employee;
import com.pawan.TestJpa.repo.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee post(Employee employee) {
		
		return employeeRepository.save(employee);
	}

	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}

	public Optional<Employee> get(Integer id) {
		return employeeRepository.findById(id);
	}

	public Employee patch(Integer id, Employee employee) throws Exception {
		Optional<Employee> employeeExisting = get(id);

		if (employeeExisting.isPresent()) {
			Employee pathEmployee = employeeExisting.get();
			if (employee.getEmpName() != null) {
				pathEmployee.setEmpName(employee.getEmpName());
			}
			return employeeRepository.save(pathEmployee);
		} else {
			throw new Exception("not found");
		}

	}

	public Employee put(Employee employee) throws Exception {
		Optional<Employee> employeeExisting = get(employee.getId());
		
		if (employeeExisting.isPresent()) {
			return employeeRepository.save(employee);
		}else {
			throw new Exception("not found");
		}
		
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		employeeRepository.deleteById(id);
	}
}
