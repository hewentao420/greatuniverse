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
	}

	@Override
	public void addComment(Comment comment) {
		CommentDAO commentDAO = new CommentDAOImpl();
		commentDAO.addComment(comment);
	}
	
}
