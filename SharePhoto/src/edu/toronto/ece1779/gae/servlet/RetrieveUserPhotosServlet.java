package edu.toronto.ece1779.gae.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.toronto.ece1779.gae.util.Constants;

public class RetrieveUserPhotosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter(Constants.USER_ID);
		
		//call service, pass in userId, return photos.
		
		//request.setAttribute(Constants.PHOTOS, photos);
		
		String nextJSP = "/user.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request,response);
		
	}



}
