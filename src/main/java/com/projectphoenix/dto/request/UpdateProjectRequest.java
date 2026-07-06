package com.projectphoenix.dto.request;

import lombok.Data;

@Data
public class UpdateProjectRequest {
	
	String name;
	String description;
	String environment;
	String applicationType;

}
