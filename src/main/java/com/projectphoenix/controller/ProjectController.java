package com.projectphoenix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectphoenix.dto.request.CreateProjectRequest;
import com.projectphoenix.dto.request.UpdateProjectRequest;
import com.projectphoenix.dto.response.ProjectResponse;
import com.projectphoenix.service.ProjectService;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@PostMapping("/")
	ResponseEntity<ProjectResponse> createProject(@RequestBody CreateProjectRequest createProjectRequest) {
		return new ResponseEntity<>(projectService.createProject(createProjectRequest), HttpStatus.CREATED);
	}

	@GetMapping("/")
	ResponseEntity<List<ProjectResponse>> getAllProjects() {
		return new ResponseEntity<>(projectService.getAllProjects(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	ResponseEntity<ProjectResponse> getProjectById(@PathVariable long id) {
		try {
			ProjectResponse projectResponse = projectService.getProjectById(id);
			return new ResponseEntity<>(projectResponse, HttpStatus.OK);
		} catch (RuntimeException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PutMapping("/{id}")
	ResponseEntity<ProjectResponse> UpdateProject(@PathVariable long id,
			@RequestBody UpdateProjectRequest updateProjectRequest) {
		return new ResponseEntity<>(projectService.updateProject(id, updateProjectRequest), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	void deleteProject(@PathVariable long id) {
		projectService.deleteProject(id);
	}

}
