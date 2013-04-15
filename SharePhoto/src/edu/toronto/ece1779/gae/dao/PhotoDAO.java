package edu.toronto.ece1779.gae.dao;

import java.io.IOException;
import java.util.List;

import org.apache.commons.fileupload.FileItemStream;

import edu.toronto.ece1779.gae.model.Photo;
import edu.toronto.ece1779.gae.model.SearchCriteria;

public interface PhotoDAO {
	
	public List<Photo> searchPhotos(SearchCriteria searchCriteria);
	
	public void addPhoto(Photo photo);
	
	public Photo retrievePhoto(Long imageKey);
	
	public List<Photo> retrieveUserPhotos(String userId);

	public void uploadPhotoToCloudStorage(String fileName, FileItemStream item) throws IOException;
	
}
