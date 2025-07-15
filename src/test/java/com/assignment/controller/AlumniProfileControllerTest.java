package com.assignment.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.assignment.dto.AlumniRequest;
import com.assignment.dto.AlumniResponse;
import com.assignment.service.AlumniProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = AlumniProfileController.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class AlumniProfileControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AlumniProfileService alumniProfileService;

	@Test
	void shouldReturnAlumniList() throws Exception {
		List<AlumniResponse> mockList = List
				.of(new AlumniResponse("John", "Software Engineer", "SVNIT", "Ahmedabad", "Headline", 2023));

		when(alumniProfileService.searchAndSaveAlumni(any())).thenReturn(mockList);
        
		AlumniRequest alumniRequest = new AlumniRequest();
		alumniRequest.setDesignation("Software Engineer");
		alumniRequest.setUniversity("SVNIT");
		alumniRequest.setPassoutYear(2023);
		
		
		MvcResult result = mockMvc.perform(post("/api/alumni/search")
		        .content(toJson(alumniRequest))
		        .contentType(MediaType.APPLICATION_JSON)
		        .accept(MediaType.APPLICATION_JSON))
//		    .andExpect(status().isOk())
		    .andReturn(); // 👈 Capture the result

		String responseJson = result.getResponse().getContentAsString();
		System.out.println("Response JSON: " + responseJson); 
		
		
//		       .andExpect(status().isOk()).andExpect(jsonPath("$[0].name").value("John"))
//				.andExpect(jsonPath("$[0].university").value("SVNIT"))
//				.andExpect(jsonPath("$[0].passoutYear").value(2023));
	}
	
	 @Test
	    void shouldReturnAllAlumni() throws Exception {
	        List<AlumniResponse> mockList = List.of(
	                new AlumniResponse("John", "Engineer", "SVNIT", "Ahmedabad", "Headline", 2023),
	                new AlumniResponse("Alice", "Designer", "NIT", "Mumbai", "Headline", 2022)
	        );

	        when(alumniProfileService.getAllAlumni()).thenReturn(mockList);

	        MvcResult result = mockMvc.perform(get("/api/alumni/all")
	                .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.status").value("success"))
	            .andExpect(jsonPath("$.data").isArray())
	            .andExpect(jsonPath("$.data[0].name").value("John"))
	            .andExpect(jsonPath("$.data[1].name").value("Alice"))
	            .andReturn();

	        // Print the full JSON response
	        System.out.println("Response: " + result.getResponse().getContentAsString());
	    }
	
	private byte[] toJson(Object r) throws Exception {
        ObjectMapper map = new ObjectMapper();
        return map.writeValueAsString(r).getBytes();
    }
}
