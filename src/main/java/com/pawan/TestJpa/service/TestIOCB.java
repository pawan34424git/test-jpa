package com.pawan.TestJpa.service;

import org.springframework.stereotype.Component;

import com.pawan.TestJpa.service.type.TestIOC;

@Component
public class TestIOCB implements TestIOC {

	@Override
	public void showMe() {
		System.out.println("caleed TestIOCB");
		
	}
	
}
