package com.projectphoenix.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

	@GetMapping("/health")
	public Map<String, String> health() {
		Map<String, String> response = new HashMap<String, String>();
		response.put("status", "UP");
		response.put("application", "Project Phoenix");
		response.put("version", "0.0.1");
		return  response;
	}
	
}
