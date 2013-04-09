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
import edu.toronto.ece1779.gae.service.PhotoService;
import edu.toronto.ece1779.gae.service.PhotoServiceImpl;
import edu.toronto.ece1779.gae.util.Constants;


public class AddCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String commenterUserId = request.getParameter(Constants.USER_ID);
		long imageKey = Long.parseLong(request.getParameter(Constants.KEY));
		String description = request.getParameter(Constants.DESCRIPTION);
		int rating = Integer.parseInt(request.getParameter(Constants.RATING));
		String nickName = request.getParameter(Constants.NICK_NAME);
		
		Comment comment = new Comment();
		comment.setImageId(imageKey);
		comment.setDescription(description);
		comment.setRating(rating);
		comment.setUserId(commenterUserId);
		comment.setNickName(nickName);
		
		PhotoService photoService = new PhotoServiceImpl();
		photoService.addComment(comment);
		
		Photo photo = new Photo();
		photo.setImageKey(imageKey);
		List<Comment> comments = photoService.retrieveComments(photo);
		
		request.setAttribute(Constants.COMMENTS, comments);
		
		String nextJSP = "/photoDetail.jsp"; //TODO
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request,response);
	}
	
}