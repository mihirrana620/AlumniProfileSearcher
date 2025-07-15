package com.assignment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.assignment.AlumniProfileSearcherApplication;
import com.assignment.controller.AlumniProfileController;
import com.assignment.dao.AlumniProfileDao;
import com.assignment.dto.AlumniRequest;
import com.assignment.dto.AlumniResponse;
import com.assignment.entity.AlumniProfile;

@SpringBootTest
@ContextConfiguration(classes = AlumniProfileSearcherApplication.class)
public class AlumniProfileServiceTest {
  
	@Autowired
    private AlumniProfileService service;

    @Autowired
    private AlumniProfileDao dao;

    @Test
    void shouldSaveAndReturnAlumni() throws Exception {
        AlumniRequest request = new AlumniRequest("SVNIT", "Software Engineer", 2023);

        List<AlumniResponse> response = service.searchAndSaveAlumni(request);
        
        List<AlumniProfile> saved = response.stream()
                .map(this::mapToProfile)
                .toList();
        assertFalse(saved.isEmpty());
        assertEquals("SVNIT", saved.get(0).getUniversity());
    }
    
    private AlumniProfile mapToProfile(AlumniResponse profile) {
    	AlumniProfile dto = new AlumniProfile();
	        dto.setName(profile.getName());
	        dto.setCurrentRole(profile.getCurrentRole());
	        dto.setUniversity(profile.getUniversity());
	        dto.setLocation(profile.getLocation());
	        dto.setLinkedinHeadline(profile.getLinkedinHeadline());
	        dto.setPassoutYear(profile.getPassoutYear());
	        return dto;
	    }
}
