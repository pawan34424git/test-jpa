package com.pawan.TestJpa.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pawan.TestJpa.domain.Employee;

@Controller
public class GreetingController {


	@GetMapping("/greeting")
	public String get(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		Employee employee = new Employee();
		employee.setEmpName("Aaaa");
		model.addAttribute("employee", employee);
		return "greeting-pages/greeting";
	}

	@PostMapping(value = "/greeting",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String post(Employee employee, Model model) {
		model.addAttribute("name", employee.getEmpName());
		model.addAttribute("employee", employee);
		return "greeting-pages/greeting";
	}

}
