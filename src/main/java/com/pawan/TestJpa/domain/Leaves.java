package com.pawan.TestJpa.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Leaves {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Size(min = 3, max = 5)
	private String day;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Employee employee;
	
	@Transient
	private  Integer  employeeId;
	
	@Transient
	private  String  employeeName;
	
	public Leaves(Leaves leaves,Boolean fetchEmp) {
		this.id=leaves.id;
		this.day=leaves.day;
		if(fetchEmp && leaves.getEmployee()!=null) {
			this.employeeId=leaves.getEmployee().getId();
			this.employeeName=leaves.getEmployee().getEmpName();
		}
		 
	}
}
