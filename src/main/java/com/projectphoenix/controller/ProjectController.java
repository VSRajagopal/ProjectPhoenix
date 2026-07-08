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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/projects")
@Tag(name="Project APIs", description="Manage Projects")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@PostMapping("/")
	@Operation(summary="Create Project")
	ResponseEntity<ProjectResponse> createProject(@Valid @RequestBody CreateProjectRequest createProjectRequest) {
		return new ResponseEntity<>(projectService.createProject(createProjectRequest), HttpStatus.CREATED);
	}

	@GetMapping("/")
	@Operation(summary="Get all projects")
	ResponseEntity<List<ProjectResponse>> getAllProjects() {
		return new ResponseEntity<>(projectService.getAllProjects(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary="Get project by id")
	ResponseEntity<ProjectResponse> getProjectById(@PathVariable long id) {
		ProjectResponse projectResponse = projectService.getProjectById(id);
		return new ResponseEntity<>(projectResponse, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	@Operation(summary="Update project by ID")
	ResponseEntity<ProjectResponse> UpdateProject(@PathVariable long id,
			@Valid @RequestBody UpdateProjectRequest updateProjectRequest) {
		return new ResponseEntity<>(projectService.updateProject(id, updateProjectRequest), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	@Operation(summary="Delete Project")
	void deleteProject(@PathVariable long id) {
		projectService.deleteProject(id);
	}

}
