package com.niit.collaboration.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.niit.collaboration.controller.UserController;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Override
	protected Class[] getRootConfigClasses() {
		logger.debug("Starting of the method getRootConfigClasses");
		return new Class[] { AppConfig.class };
	}

	@Override
	protected Class[] getServletConfigClasses() {
		logger.debug("Starting of the method getServletConfigClasses");
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		logger.debug("Starting of the method getServletMappings");
		return new String[] { "/" };
	}
}
