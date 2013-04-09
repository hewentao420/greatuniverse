package edu.toronto.ece1779.gae.service;

import java.util.List;

import edu.toronto.ece1779.gae.model.Photo;
import edu.toronto.ece1779.gae.model.SearchCriteria;

public interface PhotoService {
	
	public List<Photo> searchPhotos(SearchCriteria searchCriteria);
	
	public void addPhoto(Photo photo);
	
	public Photo retrievePhoto(long imageKey);

	public List<Photo> retrieveUserPhotos(String userId);
	
}
