package com.revature.servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.People;
import com.revature.beans.Trip;
import com.revature.beans.TripTemplate;
import com.revature.dao.TripDAO;
import com.revature.service.TripManager;

public class CreateTripServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String message = "";
	private ObjectMapper objectMapper = new ObjectMapper();
	TripDAO tripdb = new TripDAO();
	TripManager TM = new TripManager();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BufferedReader reader = request.getReader();
		HttpSession session = request.getSession();
		if (session.isNew()) {
			response.getWriter().append("Please login.");
		} else {
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}

			String jsonString = sb.toString();
			System.out.println(jsonString);
			try {
				TripTemplate tripData = objectMapper.readValue(jsonString, TripTemplate.class);
				System.out.println(tripData.getName());
				People p = (People) session.getAttribute("user");
				Trip t = TM.createTrip(p.getId(), tripData.getName());
				if (t != null) {
					// session.setAttribute("trip", t);
					response.getWriter().append(objectMapper.writeValueAsString(t));
					response.setContentType("application/json");
					response.setStatus(201);
				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

		}
	}
}
