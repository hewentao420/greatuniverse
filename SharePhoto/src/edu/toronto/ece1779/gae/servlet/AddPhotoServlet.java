package edu.toronto.ece1779.gae.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		String weather = request.getParameter("weather");
		String time = request.getParameter("time");
//		String keyword = request.getParameter("keyword");
//		double lat1 = Double.parseDouble(request.getParameter("lat1"));
//		double lat2 = Double.parseDouble(request.getParameter("lat2"));
//		double lng1 = Double.parseDouble(request.getParameter("lng1"));
//		double lng2 = Double.parseDouble(request.getParameter("lng2"));
//		System.out.println("AddPhotoServlet:\nParameters from UI - weather: " + weather + " ;time: " + time + " ;keyword: " + keyword
//				+ " ;lat1: " + lat1 + " ;lat2: " + lat2 + " ;lng1: " + lng1 + " ;lng2: " + lng2);
		System.out.println("AddPhotoServlet:\nParameters from UI - weather: " + weather + " ;time: " + time);
		
	}
	
}
