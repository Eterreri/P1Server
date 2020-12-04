package com.revature.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Equipment;
import com.revature.beans.People;
import com.revature.beans.Trip;
import com.revature.dao.TripDAO;
import com.revature.service.TripManager;

/**
 * Servlet implementation class ItemsOnTripServlet
 */
@WebServlet("/ItemsOnTripServlet")
public class ItemsOnTripServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TripDAO trip = new TripDAO();
	TripManager TM = new TripManager();
	private ObjectMapper objectMapper = new ObjectMapper();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemsOnTripServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		People person = (People)session.getAttribute("user");
		Trip t = (Trip)session.getAttribute("trip");
		if(t != null && person != null)
		{
			ArrayList<Equipment> eList = TM.GetEquipmentTrip(t.getTripId(), person.getId()); //gets all equipment
			if(eList.size() > 1)
			{
				StringBuilder json = new StringBuilder();
				for(Equipment e : eList)
				{
					json.append(objectMapper.writeValueAsString(e));
				}
				response.getWriter().append(json);
				response.setContentType("application/json");
				response.setStatus(200);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				
	}

}
