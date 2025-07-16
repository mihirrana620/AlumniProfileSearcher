package com.assignment.controller;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.assignment.dto.AlumniRequest;
import com.assignment.dto.AlumniResponse;
import com.assignment.service.AlumniProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AlumniProfileController.class)
@AutoConfigureMockMvc
public class AlumniProfileControllerTest {

	@Autowired
	private MockMvc mockMvc;
  
    
	@MockBean
	private AlumniProfileService alumniProfileService;
	
	

    @Test
    void testGetAllAlumni() throws Exception {
        AlumniResponse mock = new AlumniResponse("John", "Software Engineer", "SVNIT", "Surat", "Headline", 2023);
        when(alumniProfileService.getAllAlumni()).thenReturn(List.of(mock));

        mockMvc.perform(get("/api/alumni/all")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("success"))
            .andExpect(jsonPath("$.data[0].name").value("John"));
    }

    @Test
    void testSearchAlumni() throws Exception {
        AlumniResponse mock = new AlumniResponse("Jane", "Software Engineer", "SVNIT", "Surat", "Headline", 2023);
        when(alumniProfileService.searchAndSaveAlumni(Mockito.any())).thenReturn(List.of(mock));

        String json = """
        {
            "university": "SVNIT",
            "designation": "Software Engineer",
            "passoutYear": 2023
        }
        """;

        mockMvc.perform(post("/api/alumni/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("success"))
            .andExpect(jsonPath("$.data[0].name").value("Jane"));
    }
	
	

}
