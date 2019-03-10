package com.examen.domain;

import java.util.List;

public class Persons {
	
	private List<Person> technicians;
	private Integer errorCode;
	private String success;
	

	public List<Person> getTechnicians() {
		return technicians;
	}
	public void setTechnicians(List<Person> technicians) {
		this.technicians = technicians;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	
	

}
