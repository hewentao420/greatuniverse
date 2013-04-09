package edu.toronto.ece1779.gae.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.toronto.ece1779.gae.util.Constants;

import edu.toronto.ece1779.gae.model.Comment;
import edu.toronto.ece1779.gae.model.Photo;
import edu.toronto.ece1779.gae.service.PhotoService;
import edu.toronto.ece1779.gae.service.PhotoServiceImpl;

public class RetrievePhotoDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String key = request.getParameter(Constants.KEY);
		System.out.println("key:" + key);
		
		long imageKey = Long.parseLong(key);
		
		PhotoService service = new PhotoServiceImpl();
		Photo photo = service.retrievePhoto(imageKey);
		List<Comment> comments = service.retrieveComments(photo);
		
		request.setAttribute(Constants.KEY, key);
		request.setAttribute(Constants.NICK_NAME, photo.getNickName());
		request.setAttribute(Constants.DESCRIPTION, photo.getDescription());
		request.setAttribute(Constants.WEATHER, photo.getWeather());
		request.setAttribute(Constants.TIME, photo.getTime());
		request.setAttribute(Constants.APERTURE, photo.getAperture());
		request.setAttribute(Constants.ISO, photo.getIso());
		request.setAttribute(Constants.SHUTTER_SPEED, photo.getShutterSpeed());
		request.setAttribute(Constants.COMMENTS, comments);
		
		String nextJSP = "/photoDetail.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request,response);

	}

}
