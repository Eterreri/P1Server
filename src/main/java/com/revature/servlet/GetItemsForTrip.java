package com.revature.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.People;
import com.revature.beans.Trip;
import com.revature.beans.TripTemplate;
import com.revature.dao.TripDAO;
import com.revature.service.TripManager;

/**
 * Servlet implementation class GetItemsForTrip
 */
@WebServlet("/GetItemsForTrip")
public class GetItemsForTrip extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TripDAO trip = new TripDAO();
	TripManager TM = new TripManager();
	
	private ObjectMapper objectMapper = new ObjectMapper();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetItemsForTrip() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null)
		{
			if(session.getAttribute("trip")!= null) {
				BufferedReader reader = request.getReader();
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
				People p = (People)session.getAttribute("user");
				Trip t = (Trip)session.getAttribute("trip");
				String jsonString = sb.toString();
				System.out.println(jsonString);
				TripTemplate tripData = objectMapper.readValue(jsonString, TripTemplate.class);
				Trip newTrip = TM.EditTripName(p.getId(), t.getTripId(), tripData.getName());
				if(newTrip != null)
				{
					response.getWriter().append(objectMapper.writeValueAsString(newTrip));
					session.setAttribute("trip", newTrip);
					response.setContentType("application/json");
					response.setStatus(200);
				}
			}
			else {
				response.getWriter().append("Select a trip");
			}
		}
		else {
			response.getWriter().append("Please login!");
		}
	}

}
