package com.projectphoenix.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateProjectRequest {
	@NotBlank
	@NotNull
	@Size(min=3, max=100)
	String name;
	@Size(max=500)
	String description;
	@NotNull
	@NotBlank
	String environment;
	@NotNull
	@NotBlank
	String applicationType;

}
