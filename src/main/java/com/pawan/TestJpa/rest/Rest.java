package com.pawan.TestJpa.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pawan.TestJpa.domain.Employee;

@Controller
@RequestMapping("/api/rest")
public class Rest {

	
	@GetMapping
	@ResponseBody
	public List<Employee> get() {
		
		List<Employee> list=new ArrayList<>(1);
		list.add(new Employee(null, "AA"));
		return list;
	}
}
