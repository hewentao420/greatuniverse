package edu.toronto.ece1779.gae.service;

import java.util.List;

import edu.toronto.ece1779.gae.model.Comment;
import edu.toronto.ece1779.gae.model.Photo;

public interface CommentService {
	
	public List<Comment> retrieveComments(Photo photo); 

	public void addComment(Comment comment);
}
