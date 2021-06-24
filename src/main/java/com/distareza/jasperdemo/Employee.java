package com.distareza.jasperdemo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
	
	@Id
	private Long id;
	private String name;
	private String job;
	private Double salary;
	
	public Employee() {
	}
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getJob() {
		return job;
	}



	public void setJob(String job) {
		this.job = job;
	}



	public Double getSalary() {
		return salary;
	}



	public void setSalary(Double salary) {
		this.salary = salary;
	}



	@Override
	public String toString() {
		return String.format("{ %d, '%s', '%s', '%,.2f'}", this.id, this.name, this.job, this.salary);
	}
}
