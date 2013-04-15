package edu.toronto.ece1779.gae.service;

import java.io.IOException;
import java.util.List;

import org.apache.commons.fileupload.FileItemStream;

import edu.toronto.ece1779.gae.dao.PhotoDAO;
import edu.toronto.ece1779.gae.dao.PhotoDAOImpl;
import edu.toronto.ece1779.gae.model.Photo;
import edu.toronto.ece1779.gae.model.SearchCriteria;

public class PhotoServiceImpl implements PhotoService {

	@Override
	public List<Photo> searchPhotos(SearchCriteria searchCriteria) {
		PhotoDAO photoDAO = new PhotoDAOImpl();
		return photoDAO.searchPhotos(searchCriteria);
	}
	
	@Override
	public void addPhoto(Photo photo) {
		PhotoDAO photoDAO = new PhotoDAOImpl();
		photoDAO.addPhoto(photo);
	}

	@Override
	public Photo retrievePhoto(Long imageKey) {
		PhotoDAO photoDAO = new PhotoDAOImpl();
		return photoDAO.retrievePhoto(imageKey);
	}

	@Override
	public List<Photo> retrieveUserPhotos(String userId) {
		PhotoDAO photoDAO = new PhotoDAOImpl();
		return photoDAO.retrieveUserPhotos(userId);
	}

	@Override
	public void uploadPhotoToCloudStorage(String fileName, FileItemStream item)
			throws IOException {
		PhotoDAO photoDAO = new PhotoDAOImpl();
		photoDAO.uploadPhotoToCloudStorage(fileName, item);
	}

}
