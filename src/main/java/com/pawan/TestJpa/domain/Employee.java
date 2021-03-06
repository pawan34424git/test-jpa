package com.pawan.TestJpa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String empName;
	private Integer salary;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Address> address =new ArrayList<>(0);
	
	public Employee(Employee emp) {
		this.id=emp.id;
		this.empName=emp.empName;
		this.salary=emp.salary;
		if(emp.getAddress()!=null) {
			System.out.println("sss -->"+emp.getAddress().size()); // call started fetch
		}
	}
}
