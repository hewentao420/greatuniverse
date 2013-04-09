package edu.toronto.ece1779.gae.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.toronto.ece1779.gae.model.Comment;
import edu.toronto.ece1779.gae.model.Photo;
import edu.toronto.ece1779.gae.service.CommentService;
import edu.toronto.ece1779.gae.service.CommentServiceImpl;
import edu.toronto.ece1779.gae.service.PhotoService;
import edu.toronto.ece1779.gae.service.PhotoServiceImpl;
import edu.toronto.ece1779.gae.util.Constants;

public class RetrievePhotoDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String nextJSP = "/photoView.jsp";
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String key = request.getParameter(Constants.KEY);
		System.out.println("key:" + key);
		long imageKey = Long.parseLong(key);
		
		PhotoService photoService = new PhotoServiceImpl();
		Photo photo = photoService.retrievePhoto(imageKey);
		
		photo.setImageKey(12345);//TODO test code
		
		CommentService commentService = new CommentServiceImpl();
		List<Comment> commentList = commentService.retrieveComments(photo);
		System.out.println("comment list size: " + commentList.size());
		
		request.setAttribute(Constants.PHOTO, photo);
		request.setAttribute(Constants.COMMENTS, commentList);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request,response);

	}

}
