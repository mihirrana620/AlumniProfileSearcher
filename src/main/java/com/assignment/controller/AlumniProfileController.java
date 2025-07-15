package com.assignment.controller;

import java.util.List;
import java.util.Map;

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
import com.assignment.service.AlumniProfileService;

@RestController
@RequestMapping("/api/alumni")
public class AlumniProfileController {

	@Autowired
	private AlumniProfileService alumniProfileService;

	@PostMapping( value ="/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> searchAlumni(@RequestBody AlumniRequest request) throws Exception {
		System.out.println("###############" + request);
		List<AlumniResponse> result = alumniProfileService.searchAndSaveAlumni(request);
		System.out.println("###############" + result);
		
		return ResponseEntity.ok().body(Map.of("status", "success", "data", result));
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllAlumni() {
		List<AlumniResponse> result = alumniProfileService.getAllAlumni();
		return ResponseEntity.ok().body(Map.of("status", "success", "data", result));
	}

}