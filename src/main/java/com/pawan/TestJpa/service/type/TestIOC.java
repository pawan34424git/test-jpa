package com.pawan.TestJpa.service.type;

public interface TestIOC {
	
	private void intrerfacePrivate() {
		System.out.println("called intrerfacePrivate");
	}
	
	default void mydefault() {
		intrerfacePrivate();
	}
	
	void showMe();
	
	
	
}
