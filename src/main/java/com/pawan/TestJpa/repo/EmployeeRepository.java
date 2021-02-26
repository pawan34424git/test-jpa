package com.pawan.TestJpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pawan.TestJpa.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
