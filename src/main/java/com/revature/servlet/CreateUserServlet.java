package com.revature.servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.beans.People;
import com.revature.beans.UserTemplate;
import com.revature.service.LoginManager;
public class CreateUserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private ObjectMapper objectMapper = new ObjectMapper();
	private LoginManager log = new LoginManager();
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader reader = request.getReader();
		
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		String jsonString = sb.toString();
		try {
			// Jackson Databind parsing the jsonString and creating an InsertPirateTemplate object, with that data
			UserTemplate userData = objectMapper.readValue(jsonString, UserTemplate.class);
			People p = log.createUser(userData);
			if(p.getId() != -1) {
				String insertedUserJSON = objectMapper.writeValueAsString(p);
				response.getWriter().append(insertedUserJSON);
				
				response.setContentType("application/json");
				response.setStatus(201);
			} else {
				response.setStatus(409);
			}
		} catch (JsonProcessingException e) {
			response.setStatus(400);
		}
	}
}
