package com.assignment.dto;

public class AlumniRequest {

    private String university;
    private String designation;
    private int passoutYear;

    public AlumniRequest() {
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

    public int getPassoutYear() {
        return passoutYear;
    }

    public void setPassoutYear(int passoutYear) {
        this.passoutYear = passoutYear;
    }

    @Override
    public String toString() {
        return "AlumniRequest{" +
                "university='" + university + '\'' +
                ", designation='" + designation + '\'' +
                ", passoutYear=" + passoutYear +
                '}';
    }

	public AlumniRequest(String university, String designation, int passoutYear) {
		super();
		this.university = university;
		this.designation = designation;
		this.passoutYear = passoutYear;
	}
    
    
}
