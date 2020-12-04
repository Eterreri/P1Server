package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StaticResourceServlet
 */
@WebServlet("/StaticResourceServlet")
public class StaticResourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaticResourceServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURI().replace("/OutdoorApp", "");
		if(path!= null) {
			switch(path) {
			case "/":
				request.getRequestDispatcher("/static/index.html").include(request, response);
				response.setContentType("text/html");
				response.setStatus(200);
				break;
			case "/login":
				request.getRequestDispatcher("/static/login.html").include(request, response);
				response.setContentType("text/html");
				response.setStatus(200);
				break;
			case "/logout":
				request.getRequestDispatcher("/static/logout.html").include(request, response);
				response.setContentType("text/html");
				response.setStatus(200);
				break;
			case "/create_trip":
				request.getRequestDispatcher("/static/create-trip.html").include(request, response);
				response.setContentType("text/html");
				response.setStatus(200);
				break;
			case "/create_user":
				request.getRequestDispatcher("/static/create-user.html").include(request, response);
				response.setContentType("text/html");
				response.setStatus(200);
				break;
			case "/js/landing":
				request.getRequestDispatcher("/static/js/landing.js").include(request, response);
				response.setContentType("text/javascript");
				response.setStatus(200);
				break;
			case "/js/login":
				request.getRequestDispatcher("/static/js/login.js").include(request, response);
				response.setContentType("text/javascript");
				response.setStatus(200);
				break;
			case "/js/logout":
				request.getRequestDispatcher("/static/js/logout.js").include(request, response);
				response.setContentType("text/javascript");
				response.setStatus(200);
				break;
			case "/js/create-trip":
				request.getRequestDispatcher("/static/js/create-trip.js").include(request, response);
				response.setContentType("text/javascript");
				response.setStatus(200);
				break;
			case "/js/create-user":
				request.getRequestDispatcher("/static/js/create-user.js").include(request, response);
				response.setContentType("text/javascript");
				response.setStatus(200);
				break;
			case "/js/init":
				request.getRequestDispatcher("/static/js/init.js").include(request, response);
				response.setContentType("text/javascript");
				response.setStatus(200);
				break;
			case "/styles/style":
				request.getRequestDispatcher("/static/css/style.css").include(request, response);
				response.setContentType("text/css");
				response.setStatus(200);
				break;
			}
		}
	}

}
