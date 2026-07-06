package com.projectphoenix.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.projectphoenix.dto.request.CreateProjectRequest;
import com.projectphoenix.dto.request.UpdateProjectRequest;
import com.projectphoenix.dto.response.ProjectResponse;

public interface ProjectService {
	
	ProjectResponse createProject(CreateProjectRequest request);
	
	List<ProjectResponse> getAllProjects();
	
	ProjectResponse getProjectById(Long id);
	
	ProjectResponse updateProject(Long id, UpdateProjectRequest request);
	
	void deleteProject(Long id);
}
