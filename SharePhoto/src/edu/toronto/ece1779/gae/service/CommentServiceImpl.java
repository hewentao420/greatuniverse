package edu.toronto.ece1779.gae.service;

import java.util.List;

import edu.toronto.ece1779.gae.dao.CommentDAO;
import edu.toronto.ece1779.gae.dao.CommentDAOImpl;
import edu.toronto.ece1779.gae.model.Comment;
import edu.toronto.ece1779.gae.model.Photo;

public class CommentServiceImpl implements CommentService {

	@Override
	public List<Comment> retrieveComments(Photo photo) {
		CommentDAO commentDAO = new CommentDAOImpl();
		return commentDAO.retrieveComments(photo);
//		Comment comment = new Comment();
//		comment.setImageId(123);
//		comment.setDescription("wonderful");
//		comment.setRating(2);
//		comment.setUserId("hewentao420");
//		comment.setNickName("Wentao");
//
//		Comment comment2 = new Comment();
//		comment2.setImageId(123);
//		comment2.setDescription("bad");
//		comment2.setRating(2);
//		comment2.setUserId("abced1234");
//		comment2.setNickName("Tim");
//		
//		List<Comment> comments = new ArrayList<Comment>();
//		comments.add(comment);
//		comments.add(comment2);
//		
//		return comments; 
	}

	@Override
	public void addComment(Comment comment) {
		CommentDAO commentDAO = new CommentDAOImpl();
		commentDAO.addComment(comment);
	}
	
}
