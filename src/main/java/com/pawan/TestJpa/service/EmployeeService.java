package com.pawan.TestJpa.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawan.TestJpa.domain.Employee;
import com.pawan.TestJpa.repo.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EntityManager em;
	
	@Transactional
	public Employee post(Employee employee) {

		// return employeeRepository.save(employee);
		Session session = (Session) em.getDelegate();
//		Transaction t = session.beginTransaction();
		Integer id = (Integer) session.save(employee);
		employee = session.find(Employee.class, id);
//		t.commit();
		return employee;
	}

	public List<Employee> getAll(String empName) {

		if (empName != null) {
			// return employeeRepository.findByEmpName(empName);

//			Query query = em.createQuery("select empName,salary from Employee e where empName='"+empName+"'"); //where e.empName="+empName
//			return query.getResultList();

			Session session = (Session) em.getDelegate();
//			List<Employee> list = session.createQuery("FROM Employee E WHERE E.empName = :empName", Employee.class)
//					.setParameter("empName", empName).list();
			
			List<Employee> list = session.createQuery("FROM Employee E join fetch E.address A WHERE E.empName = :empName ", Employee.class)
					.setParameter("empName", empName).list(); // solve N+1 problem  
			
			return list.stream().map(Employee::new).collect(Collectors.toList());
		}
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
		} else {
			throw new Exception("not found");
		}

	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		employeeRepository.deleteById(id);
	}
}
