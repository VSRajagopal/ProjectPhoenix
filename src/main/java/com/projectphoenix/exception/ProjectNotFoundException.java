package com.projectphoenix.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectNotFoundException extends RuntimeException{

	private static final Logger logger = LoggerFactory.getLogger(ProjectNotFoundException.class);
	
	public ProjectNotFoundException(Long id) {
				
		super("Project with ID " + id + " not found.");
	
		logger.debug("Project not found");
		
	}

	
	
}
