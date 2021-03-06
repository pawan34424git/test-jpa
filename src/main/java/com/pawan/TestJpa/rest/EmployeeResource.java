package com.pawan.TestJpa.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pawan.TestJpa.domain.Employee;
import com.pawan.TestJpa.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeResource {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<Employee> post(@RequestBody Employee employee) {

		try {
			return new ResponseEntity<>(employeeService.post(employee), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping
	public ResponseEntity<List<Employee>> getAll(HttpServletRequest request) {
		try {
			String empName = request.getParameter("empName");
			return new ResponseEntity<>(employeeService.getAll(empName ), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Employee> get(@PathVariable Integer id) {

		try {
			return employeeService.get(id)
						.map(emp-> new ResponseEntity<>(emp, HttpStatus.OK))
						.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PatchMapping(value = "/{id}")
	public ResponseEntity<Employee> patch(@PathVariable Integer id, @RequestBody Employee employee) {

		try {
			return new ResponseEntity<>(employeeService.patch(id, employee), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping
	public ResponseEntity<Employee> put(@RequestBody Employee employee) {

		try {
			return new ResponseEntity<>(employeeService.put(employee), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		try {
			employeeService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
