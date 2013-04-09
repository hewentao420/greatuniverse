package edu.toronto.ece1779.gae.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import edu.toronto.ece1779.gae.model.Comment;
import edu.toronto.ece1779.gae.model.Photo;
import edu.toronto.ece1779.gae.service.CommentService;
import edu.toronto.ece1779.gae.service.CommentServiceImpl;
import edu.toronto.ece1779.gae.util.Constants;


public class AddCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String description = request.getParameter(Constants.COMMENT);
		
		int rating = 0;
		String ratingString = request.getParameter(Constants.RATING);
		if(ratingString!=null && !(Constants.BLANK.equals(ratingString)))
			rating = Integer.parseInt(ratingString);
		
		UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
		
		HttpSession session = request.getSession(true);
		Photo photo = (Photo)session.getAttribute(Constants.PHOTO);
		
		Comment comment = new Comment();
		comment.setImageId(photo.getImageKey());
		comment.setDescription(description);
		comment.setRating(rating);
		comment.setUserId(user.getUserId());
		comment.setNickName(user.getNickname());
		
		CommentService commentService = new CommentServiceImpl();
		commentService.addComment(comment);
		
		List<Comment> commentList = commentService.retrieveComments(photo);
		
		request.setAttribute(Constants.PHOTO, photo);
		request.setAttribute(Constants.COMMENT_LIST, commentList);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Constants.PHOTO_VIEW_JSP);
		dispatcher.forward(request,response);
	}
	
}