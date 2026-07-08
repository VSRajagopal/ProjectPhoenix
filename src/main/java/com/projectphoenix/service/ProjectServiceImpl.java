package com.projectphoenix.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectphoenix.dto.request.CreateProjectRequest;
import com.projectphoenix.dto.request.UpdateProjectRequest;
import com.projectphoenix.dto.response.ProjectResponse;
import com.projectphoenix.entity.Project;
import com.projectphoenix.exception.ProjectNotFoundException;
import com.projectphoenix.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

	private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public ProjectResponse createProject(CreateProjectRequest request) {

		logger.info("Creating Project with name: { }", request.getName());

		Project project = new Project();
		project.setName(request.getName());
		project.setDescription(request.getDescription());
		project.setEnvironment(request.getEnvironment());
		project.setApplicationType(request.getApplicationType());
		project.setCreatedAt(LocalDateTime.now());
		project.setUpdatedAt(LocalDateTime.now());
		Project response = projectRepository.save(project);
		ProjectResponse projectResponse = new ProjectResponse();
		projectResponse.setId(response.getId());
		projectResponse.setName(response.getName());
		projectResponse.setDescription(response.getDescription());
		projectResponse.setEnvironment(response.getEnvironment());
		projectResponse.setApplicationType(response.getApplicationType());
		projectResponse.setCreatedAt(response.getCreatedAt());
		projectResponse.setUpdatedAt(response.getUpdatedAt());
		return projectResponse;
	}

	@Override
	public List<ProjectResponse> getAllProjects() {

		List<Project> projectList = projectRepository.findAll();
		List<ProjectResponse> responseList = new ArrayList<ProjectResponse>();
		for (Project project : projectList) {
			ProjectResponse projectResponse = new ProjectResponse();
			projectResponse.setId(project.getId());
			projectResponse.setName(project.getName());
			projectResponse.setDescription(project.getDescription());
			projectResponse.setEnvironment(project.getEnvironment());
			projectResponse.setApplicationType(project.getApplicationType());
			projectResponse.setCreatedAt(project.getCreatedAt());
			projectResponse.setUpdatedAt(project.getUpdatedAt());
			responseList.add(projectResponse);
		}
		return responseList;
	}

	@Override
	public ProjectResponse getProjectById(Long id) {

		logger.info("Getting Project by using ID " + id);

		Project project = projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
		ProjectResponse projectResponse = new ProjectResponse();
		projectResponse.setId(project.getId());
		projectResponse.setName(project.getName());
		projectResponse.setDescription(project.getDescription());
		projectResponse.setEnvironment(project.getEnvironment());
		projectResponse.setApplicationType(project.getApplicationType());
		projectResponse.setCreatedAt(project.getCreatedAt());
		projectResponse.setUpdatedAt(project.getUpdatedAt());
		
		return projectResponse;
	}

	@Override
	public ProjectResponse updateProject(Long id, UpdateProjectRequest request) {

		logger.info("Updating Project with ID {}", id);

		Project project = (Project) projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
		project.setName(request.getName());
		project.setDescription(request.getDescription());
		project.setEnvironment(request.getEnvironment());
		project.setApplicationType(request.getApplicationType());
		project.setUpdatedAt(LocalDateTime.now());
		Project response = projectRepository.save(project);
		ProjectResponse projectResponse = new ProjectResponse();
		projectResponse.setId(response.getId());
		projectResponse.setName(response.getName());
		projectResponse.setDescription(response.getDescription());
		projectResponse.setEnvironment(response.getEnvironment());
		projectResponse.setApplicationType(response.getApplicationType());
		projectResponse.setCreatedAt(response.getCreatedAt());
		projectResponse.setUpdatedAt(response.getUpdatedAt());
		return projectResponse;

	}

	@Override
	public void deleteProject(Long id) {

		logger.info("Deleting Project with ID {}", id);

		projectRepository.deleteById(id);
	}

}
