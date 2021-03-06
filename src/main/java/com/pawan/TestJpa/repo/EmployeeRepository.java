package com.pawan.TestJpa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pawan.TestJpa.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	List<Employee> findByEmpName(String empName);

 

}
