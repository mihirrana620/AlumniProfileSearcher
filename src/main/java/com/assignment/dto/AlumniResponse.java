package com.assignment.dto;




public class AlumniResponse {
	private String name;
	private String currentRole;
	private String university;
	private String location;
	private String linkedinHeadline;
	private Integer passoutYear;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCurrentRole() {
		return currentRole;
	}
	public void setCurrentRole(String currentRole) {
		this.currentRole = currentRole;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLinkedinHeadline() {
		return linkedinHeadline;
	}
	public void setLinkedinHeadline(String linkedinHeadline) {
		this.linkedinHeadline = linkedinHeadline;
	}
	public Integer getPassoutYear() {
		return passoutYear;
	}
	public void setPassoutYear(Integer passoutYear) {
		this.passoutYear = passoutYear;
	}
	public AlumniResponse(String name, String currentRole, String university, String location, String linkedinHeadline,
			Integer passoutYear) {
		super();
		this.name = name;
		this.currentRole = currentRole;
		this.university = university;
		this.location = location;
		this.linkedinHeadline = linkedinHeadline;
		this.passoutYear = passoutYear;
	}
	public AlumniResponse() {
		
	}

	
}
