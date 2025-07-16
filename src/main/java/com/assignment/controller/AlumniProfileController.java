package com.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.dto.AlumniRequest;
import com.assignment.dto.AlumniResponse;
import com.assignment.dto.ApiResponse;
import com.assignment.service.AlumniProfileService;

@RestController
@RequestMapping("/api/alumni")
public class AlumniProfileController {

	@Autowired
	private AlumniProfileService alumniProfileService;

	@PostMapping("/search")
	public ResponseEntity<Object> searchAlumni(@RequestBody AlumniRequest request) throws Exception {
		List<AlumniResponse> result = alumniProfileService.searchAndSaveAlumni(request);
		
		return ResponseEntity.ok(new ApiResponse<>("success", result));
	}

	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllAlumni() {
		List<AlumniResponse> result = alumniProfileService.getAllAlumni();
		 
		    
		    
		return ResponseEntity.ok(new ApiResponse<>("success", result));
	}

}