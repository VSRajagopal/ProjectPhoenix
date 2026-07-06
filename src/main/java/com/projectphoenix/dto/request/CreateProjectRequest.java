package com.projectphoenix.dto.request;

import lombok.Data;

@Data
public class CreateProjectRequest {

	String name;
	String description;
	String environment;
	String applicationType;

	
}
