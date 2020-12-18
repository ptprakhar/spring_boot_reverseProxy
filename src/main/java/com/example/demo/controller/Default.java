package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * 
 * @author Prakhar Tiwari
 * 
 * 	
 */

@RestController
@RequestMapping("/mobile-api")
public class Default {
	
	 @Autowired
	 RestTemplate restTemplate;
	 private String baseUrl = "http://localhost";
	
		
	/**
	 * Handle all GET requests by pass to other API
	 */
	 
	@GetMapping("restProxy/**"  )
	public @ResponseBody String restProxyGet(HttpServletRequest request) {
		String url = request.getRequestURI()
		        .split(request.getContextPath() + "/restProxy/")[1];
	    return restTemplate.getForObject(baseUrl + "/" + url, String.class);
	}
	
	/*
	 * Handle all POST requests and pass to other API
	 */
	
	@PostMapping("restProxy/**")
	public @ResponseBody String restProxyPost(HttpServletRequest request, @RequestBody String body) {
			String url = request.getRequestURI()
			        .split(request.getContextPath() + "/restProxy/")[1];
		    return restTemplate.postForObject(baseUrl + "/" + url, body,  String.class);
		}

}
