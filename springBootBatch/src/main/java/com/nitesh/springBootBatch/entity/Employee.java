package com.nitesh.springBootBatch.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {
	@Id
    private Integer empId;
    private String name;
    private String dept;
    private Integer salary;
    private Date creationTs;
	public Employee() {
	}
	public Employee(Integer empId, String name, String dept, Integer salary, Date creationTs) {
		this.empId = empId;
		this.name = name;
		this.dept = dept;
		this.salary = salary;
		this.creationTs = creationTs;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	public Date getCreationTs() {
		return creationTs;
	}
	public void setCreationTs(Date creationTs) {
		this.creationTs = creationTs;
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", name=" + name + ", dept=" + dept + ", salary=" + salary + ", creationTs="
				+ creationTs + "]";
	}
    
    
}
