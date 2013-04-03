package edu.toronto.ece1779.gae.dao;

import java.util.List;

import edu.toronto.ece1779.gae.model.Comment;
import edu.toronto.ece1779.gae.model.Photo;
import edu.toronto.ece1779.gae.model.SearchCriteria;

public interface PhotoDAO {
	
	//DAO	
	public List<Photo> searchPhotos(SearchCriteria searchCriteria);
	
	public void addPhoto(Photo photo);
	
	public List<Comment> retrieveComments(Photo photo); 

	public void addComment(Comment comment);

}
