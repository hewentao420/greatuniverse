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
import edu.toronto.ece1779.gae.util.Constants;


public class AddCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String commenterUserId = request.getParameter(Constants.USER_ID);
		//long imageKey = Long.parseLong(request.getParameter(Constants.KEY));
		String description = request.getParameter(Constants.COMMENT);
		//int rating = Integer.parseInt(request.getParameter(Constants.RATING));
		String nickName = request.getParameter(Constants.NICK_NAME);
		
		commenterUserId = "hewentao@gmail.com";
		long imageKey = 1;
		//description = "interesting picture";
		int rating = 1;
		nickName = "Marcy";
		
		Comment comment = new Comment();
		comment.setImageId(imageKey);
		comment.setDescription(description);
		comment.setRating(rating);
		comment.setUserId(commenterUserId);
		comment.setNickName(nickName);
		
		CommentService commentService = new CommentServiceImpl();
		commentService.addComment(comment);
		
		Photo photo = new Photo();
		photo.setImageKey(imageKey);
		List<Comment> comments = commentService.retrieveComments(photo);
		
		//request.setAttribute(Constants.COMMENTS, comments);
		
		String nextJSP = "/map.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request,response);
	}
	
}