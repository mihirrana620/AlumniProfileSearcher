
package com.assignment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.dao.AlumniProfileDao;
import com.assignment.dto.AlumniRequest;
import com.assignment.dto.AlumniResponse;
import com.assignment.entity.AlumniProfile;
import com.assignment.phantomBuster.PhantomBusterAPI;
@Service
public class AlumniProfileService{
   
	@Autowired
	private AlumniProfileDao alumniProfileDao;
	
	@Autowired
	private PhantomBusterAPI phantomBusterAPI;

	public List<AlumniResponse> searchAndSaveAlumni(AlumniRequest alumniRequest) throws Exception {
		 List<AlumniProfile> profiles = phantomBusterAPI.fetchProfiles(alumniRequest);
		 alumniProfileDao.saveAll(profiles);
	        return profiles.stream()
	                .map(this::mapToResponse)
	                .collect(Collectors.toList());
	}

	public List<AlumniResponse> getAllAlumni() {
		 return alumniProfileDao.findAll().stream()
	                .map(this::mapToResponse)
	                .collect(Collectors.toList());
	}
	
	 private AlumniResponse mapToResponse(AlumniProfile profile) {
		 AlumniResponse dto = new AlumniResponse();
	        dto.setName(profile.getName());
	        dto.setCurrentRole(profile.getCurrentRole());
	        dto.setUniversity(profile.getUniversity());
	        dto.setLocation(profile.getLocation());
	        dto.setLinkedinHeadline(profile.getLinkedinHeadline());
	        dto.setPassoutYear(profile.getPassoutYear());
	        return dto;
	    }
	
	
	
	
}