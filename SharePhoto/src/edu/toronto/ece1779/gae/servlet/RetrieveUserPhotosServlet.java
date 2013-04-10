package edu.toronto.ece1779.gae.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.toronto.ece1779.gae.model.Photo;
import edu.toronto.ece1779.gae.service.PhotoService;
import edu.toronto.ece1779.gae.service.PhotoServiceImpl;
import edu.toronto.ece1779.gae.util.Constants;

public class RetrieveUserPhotosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		Photo photo = (Photo)session.getAttribute(Constants.PHOTO);
		
		PhotoService photoService = new PhotoServiceImpl();
		List<Photo> photoList = photoService.retrieveUserPhotos(photo.getUserId());
		
		request.setAttribute(Constants.PHOTO_LIST, photoList);
		request.setAttribute(Constants.PHOTO, photo);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Constants.USER_DETAILS_JSP);
		dispatcher.forward(request,response);
	}

}
