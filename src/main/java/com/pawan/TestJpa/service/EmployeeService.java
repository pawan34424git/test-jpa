package com.pawan.TestJpa.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pawan.TestJpa.ApplicationContextProvider;
import com.pawan.TestJpa.domain.Employee;
import com.pawan.TestJpa.repo.EmployeeRepository;
import com.pawan.TestJpa.service.type.TestIOC;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EntityManager em;
	
	@Autowired
	@Qualifier("ABC")
	private TestIOC testIOC;
	
	@Autowired
	@Qualifier("testIOCB")
	private TestIOC testIOC2;
	
	private Validator validator;
	
	public EmployeeService(ValidatorFactory factory ) {
		 this.validator = factory.getValidator();
	}
	
	@PostConstruct
	private void Show() {
		testIOC.mydefault();
		testIOC.showMe();
		testIOC2.showMe();
		
		
	}
	
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
		
		//ApplicationContextProvider.getContext().getBean(TestIOC.class).showMe();
		ApplicationContextProvider.getContext().getBean("ABC", TestIOC.class).showMe();
		
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

	public Employee patch(Integer id, Map<String,Object> map) throws Exception {
		Optional<Employee> employeeExisting = get(id);

		if (employeeExisting.isPresent()) {
			final Employee pathEmployee = employeeExisting.get();
			map.forEach((key,value)->{
				switch (key) {
				case "email":
					pathEmployee.setEmail((String)value);
					break;
				case "empName":
					pathEmployee.setEmpName((String)value);
					break;
				default:
					break;
				}
			}); 
			Set<ConstraintViolation<Employee>> violations = validator.validate(pathEmployee); 
			for (ConstraintViolation<Employee> constraintViolation : violations) {
				throw new Exception(constraintViolation.getMessage());
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
