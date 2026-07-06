package com.projectphoenix.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProjectResponse {

	Long id;

	String name;
	String description;
	String environment;
	String applicationType;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;
	
}
