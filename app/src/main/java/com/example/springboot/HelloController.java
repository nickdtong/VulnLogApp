package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
public class HelloController {

	private static final Logger logger = LogManager.getLogger(HelloController.class);

	@GetMapping("/")
	public String index(HttpServletRequest request) {
		logger.info("Request URL: " + request.getRequestURL());
		logger.info("Request URI: " + request.getRequestURI());
		logger.info("Request Method: " + request.getMethod());
		logger.info("Request Query String: " + request.getQueryString());
		logger.info("Request Protocol: " + request.getProtocol());
		logger.info("Request Remote Address: " + request.getRemoteAddr());
		logger.info("Request Remote Host: " + request.getRemoteHost());
		logger.info("Request Remote Port: " + request.getRemotePort());
		logger.info("Request User Agent: " + request.getHeader("User-Agent"));
		return "Log4J2 is working!";
	}

	@PostMapping("/")
	public String post(HttpServletRequest request, @RequestBody String body) {
		logger.info("Request URL: " + request.getRequestURL());
		logger.info("Request URI: " + request.getRequestURI());
		logger.info("Request Method: " + request.getMethod());
		logger.info("Request Query String: " + request.getQueryString());
		logger.info("Request Protocol: " + request.getProtocol());
		logger.info("Request Remote Address: " + request.getRemoteAddr());
		logger.info("Request Remote Host: " + request.getRemoteHost());
		logger.info("Request Remote Port: " + request.getRemotePort());
		logger.info("Request User Agent: " + request.getHeader("User-Agent"));
		logger.info("Request Body: " + body);
		return "Log4J2 is working!";
	}

}
