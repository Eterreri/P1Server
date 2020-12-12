package com.revature.servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.People;
import com.revature.beans.UserTemplate;
import com.revature.service.LoginManager;

/**
 * Servlet implementation class Login
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginManager log = new LoginManager();
	private ObjectMapper objectMapper = new ObjectMapper();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("user") != null) {
			response.getWriter().append(((People)session.getAttribute("user")).getUsername());
			response.setStatus(200);
		} else {
			response.setStatus(400);
		}
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger logger = Logger.getLogger(this.getClass());
		BufferedReader reader = request.getReader();
		HttpSession session = request.getSession(true);
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		
		String jsonString = sb.toString();
		logger.debug(jsonString);
		UserTemplate userData = objectMapper.readValue(jsonString, UserTemplate.class);
		People p = log.logUser(userData.getUsername(), userData.getPassword());
		if(p != null)
		{
			session.setAttribute("user", p);
			String insertedUserJSON = objectMapper.writeValueAsString(p);
			response.getWriter().append(insertedUserJSON);
			response.setContentType("application/json");
			response.setStatus(200);
		}
		else
		response.getWriter().append("user not found");
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
