package com.assignment.dto;

public class AlumniRequest {
	private String university;
	private String designation;
	private Integer passoutYear;
	
	
	
	
	public AlumniRequest() {
		super();
	}

	public AlumniRequest(String university, String designation, Integer passoutYear) {
		super();
		this.university = university;
		this.designation = designation;
		this.passoutYear = passoutYear;
	}
	
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Integer getPassoutYear() {
		return passoutYear;
	}
	public void setPassoutYear(Integer passoutYear) {
		this.passoutYear = passoutYear;
	}

	@Override
	public String toString() {
		return "AlumniRequest [university=" + university + ", designation=" + designation + ", passoutYear="
				+ passoutYear + "]";
	}
	
	
}
