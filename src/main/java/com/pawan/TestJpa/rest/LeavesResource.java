package com.pawan.TestJpa.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import com.pawan.TestJpa.domain.Leaves;
import com.pawan.TestJpa.service.LeavesService;

@RestController
@RequestMapping("/api/leaves")
public class LeavesResource {

	@Autowired
	private LeavesService leavesService;

	@PostMapping
	public ResponseEntity<Leaves> post(@Valid @RequestBody Leaves leaves) {

		try {
			return new ResponseEntity<>(leavesService.post(leaves), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping
	public ResponseEntity<List<Leaves>> getAll(HttpServletRequest request) {
		try {
			String employeeId = request.getParameter("employeeId");
			return new ResponseEntity<>(leavesService.getAll(employeeId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Leaves> get(@PathVariable Integer id) {

		try {
			return new ResponseEntity<>(leavesService.get(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PatchMapping(value = "/{id}")
	public ResponseEntity<Leaves> patch(@PathVariable Integer id,@Valid @RequestBody Leaves leaves) {

		try {
			return new ResponseEntity<>(leavesService.patch(id, leaves), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping
	public ResponseEntity<Leaves> put(@Valid @RequestBody Leaves leaves) {

		try {
			return new ResponseEntity<>(leavesService.put(leaves), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		try {
			leavesService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
