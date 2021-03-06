package com.pawan.TestJpa.service;

import org.springframework.stereotype.Component;

import com.pawan.TestJpa.service.type.TestIOC;

@Component("ABC")
public class TestIOCA implements TestIOC {

	@Override
	public void showMe() {
		System.out.println("caleed TestIOCA");
		
	}
	
}
