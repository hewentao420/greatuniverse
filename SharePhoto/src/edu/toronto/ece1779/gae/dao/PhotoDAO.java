package edu.toronto.ece1779.gae.dao;

import java.util.List;

import edu.toronto.ece1779.gae.model.Photo;
import edu.toronto.ece1779.gae.model.SearchCriteria;

public interface PhotoDAO {
	
	public List<Photo> searchPhotos(SearchCriteria searchCriteria);
	
	public void addPhoto(Photo photo);
	
	public Photo retrievePhoto(Long imageKey);

}
