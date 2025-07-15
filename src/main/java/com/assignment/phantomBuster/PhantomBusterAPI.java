package com.assignment.phantomBuster;

import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.assignment.dto.AlumniRequest;
import com.assignment.entity.AlumniProfile;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class PhantomBusterAPI {

	private final Environment env;
	
	
	
 
	private  final RestTemplate restTemplate = new RestTemplate();
	private  final ObjectMapper objectMapper = new ObjectMapper();

	public  List<AlumniProfile> fetchProfiles(AlumniRequest request) throws Exception {
		
		// Step 1: Launch the Phantom
		final String LAUNCH_URL = "https://api.phantombuster.com/api/v1/agent/" + env.getProperty("phantombuster.phantomId") + "/launch";
	    final String OUTPUT_URL = "https://api.phantombuster.com/api/v1/agent/" + env.getProperty("phantombuster.phantomId") + "/output";
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-Phantombuster-Key-1", env.getProperty("phantombuster.apiKey"));
		headers.setContentType(MediaType.APPLICATION_JSON);
		String sessionCookie = env.getProperty("phantombuster.sessionCookie");
		String query = buildLinkedInSearchUrl(request);

		String body = String.format(
				"{\"id\": \"%s\", \"argument\": {\"search\": \"%s\" , \"sessionCookie\" : \"%s\" } }",  env.getProperty("phantombuster.phantomId"),
				query, sessionCookie);

		System.out.println(body);
		HttpEntity<String> entity = new HttpEntity<>(body, headers);

		ResponseEntity<String> launchResponse = restTemplate.postForEntity(LAUNCH_URL, entity, String.class);
		if (!launchResponse.getStatusCode().is2xxSuccessful()) {
			throw new RuntimeException("Failed to launch Phantom");
		}

		// Step 2: Poll for completion
		System.out.println(launchResponse);
		int attempts = 0;
		while (attempts < 10) {
			Thread.sleep(7000);
			ResponseEntity<String> pollResponse = restTemplate.exchange(OUTPUT_URL, HttpMethod.GET,
					new HttpEntity<>(headers), String.class);
			JsonNode output = objectMapper.readTree(pollResponse.getBody());

			String log = output.path("data").path("output").asText();
			String csvUrl = extractCsvUrlFromLog(log);
			
			if (csvUrl != null) {
				String jsonUrl = csvUrl.replace(".csv", ".json");
				return parseJsonFromUrl(jsonUrl, request);
			}
			attempts++;
		}

		throw new RuntimeException("Phantom timed out or no result found.");

	}

	private  String cleanHeadline(String rawHeadline) {
	    if (rawHeadline == null) return "";
	    int colonIndex = rawHeadline.indexOf(':');
	    return (colonIndex >= 0 && colonIndex + 1 < rawHeadline.length())
	        ? rawHeadline.substring(colonIndex + 1).trim()
	        : rawHeadline.trim();
	}
	private  List<AlumniProfile> parseJsonFromUrl(String jsonUrl, AlumniRequest request) throws Exception {
	    List<AlumniProfile> profiles = new ArrayList<>();
	    JsonNode root = objectMapper.readTree(new URL(jsonUrl));

	    for (JsonNode node : root) {
	        AlumniProfile profile = new AlumniProfile();
	        profile.setName(node.path("fullName").asText(""));
	        profile.setUniversity(request.getUniversity());
	        profile.setPassoutYear(request.getPassoutYear());
	        profile.setLocation(node.path("location").asText(""));
	        profile.setCurrentRole(node.path("jobTitle").asText(""));
	        
	        String rawHeadline = node.path("additionalInfo").asText();
	        profile.setLinkedinHeadline(cleanHeadline(rawHeadline));

	        profiles.add(profile);
	    }

	    return profiles;
	}

	private  String extractCsvUrlFromLog(String rawLog) {
		Pattern pattern = Pattern.compile("https://cache1\\.phantombooster\\.com/[^\"]+?\\.csv");
		Matcher matcher = pattern.matcher(rawLog);
		return matcher.find() ? matcher.group() : null;
	}


	private  String buildLinkedInSearchUrl(AlumniRequest request) {
		String keywords = request.getUniversity() + " " + request.getDesignation();
		if (request.getPassoutYear() != null) {
			keywords += " " + request.getPassoutYear();
		}
		String encoded = URLEncoder.encode(keywords, StandardCharsets.UTF_8);
		return "https://www.linkedin.com/search/results/people/?keywords=" + encoded;
	}

}
