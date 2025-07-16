package com.assignment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "alumni_profiles")
public class AlumniProfile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name") 
	private String name;
	
	@Column(name = "role") 
	private String currentRole;
	
	@Column(name = "university") 
	private String university;
	
	@Column(name = "location") 
	private String location;
	
	@Column(name = "linkedinHeadline") 
	private String linkedinHeadline;
	
	@Column(name = "passoutYear") 
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
	
	
	
	public AlumniProfile(String name, String currentRole, String university, String location, String linkedinHeadline,
			Integer passoutYear) {
		super();
		this.name = name;
		this.currentRole = currentRole;
		this.university = university;
		this.location = location;
		this.linkedinHeadline = linkedinHeadline;
		this.passoutYear = passoutYear;
	}
	public AlumniProfile() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
	    return String.format(
	        "Name: %s\nRole: %s\nUniversity: %s\nLocation: %s\nHeadline: %s\nPassout Year: %s",
	        name,
	        currentRole,
	        university,
	        location,
	        linkedinHeadline,
	        passoutYear != null ? passoutYear : "N/A"
	    );
	}
	
	
}
