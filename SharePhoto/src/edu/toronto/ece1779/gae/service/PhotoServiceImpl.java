package edu.toronto.ece1779.gae.service;

import java.util.ArrayList;
import java.util.List;

import edu.toronto.ece1779.gae.dao.PhotoDAO;
import edu.toronto.ece1779.gae.dao.PhotoDAOImpl;
import edu.toronto.ece1779.gae.model.Comment;
import edu.toronto.ece1779.gae.model.Photo;
import edu.toronto.ece1779.gae.model.SearchCriteria;

public class PhotoServiceImpl implements PhotoService {

	@Override
	public List<Photo> searchPhotos(SearchCriteria searchCriteria) {
		//TODO once DAO is ready, switch to it.
		//PhotoDAO photoDAO = new PhotoDAOImpl();
		//return photoDAO.searchPhotos(searchCriteria);
		return constructTestData(searchCriteria);
	}

	@Override
	public List<Comment> retrieveComments(Photo photo) {
		PhotoDAO photoDAO = new PhotoDAOImpl();
		return photoDAO.retrieveComments(photo);
	}

	@Override
	public void addComment(Comment comment) {
		PhotoDAO photoDAO = new PhotoDAOImpl();
		photoDAO.addComment(comment);
	}
	
	
	public List<Photo> constructTestData(SearchCriteria searchCriteria) {
		ArrayList<Photo> list = new ArrayList<Photo>();
		Photo photo1 = new Photo();
		photo1.setImageId(123);
		photo1.setUserId("Jason");
		photo1.setTitle("My profile");
		photo1.setDescription("Nice photo");
		photo1.setWeather("sunny");
		photo1.setTime("morning");
		photo1.setAperture("45F");
		photo1.setIso(800);
		photo1.setShutterSpeed(100);
		photo1.setLatitude(12.3456);
		photo1.setLongitude(23.4532);
		photo1.setUrl_small("https://lh4.googleusercontent.com/-YiREmvlXE4M/Theg088FkyI/AAAAAAAAAmI/rICo95CGQzU/w759-h767-p-o-k/DSCN2136.JPG");
		photo1.setUrl_big("https://lh4.googleusercontent.com/-YiREmvlXE4M/Theg088FkyI/AAAAAAAAAmI/rICo95CGQzU/w759-h767-p-o-k/DSCN2136.JPG");
		list.add(photo1);

		Photo photo2 = new Photo();
		photo2.setImageId(124);
		photo2.setUserId("David");
		photo2.setTitle("My dinner");
		photo2.setDescription("Hotpot");
		photo2.setWeather("cloudy");
		photo2.setTime("evening");
		photo2.setAperture("60F");
		photo2.setIso(600);
		photo2.setShutterSpeed(200);
		photo2.setLatitude(-22.5631);
		photo2.setLongitude(11.3228);
		photo2.setUrl_small("https://lh5.googleusercontent.com/-22xqzpO0nSg/SE4H8QJH66I/AAAAAAAAAQc/hZyH7z6kQaI/s679/Dinner+in+the+balcony.JPG");
		photo2.setUrl_big("https://lh5.googleusercontent.com/-22xqzpO0nSg/SE4H8QJH66I/AAAAAAAAAQc/hZyH7z6kQaI/s679/Dinner+in+the+balcony.JPG");
		list.add(photo2);
		
		return list;
	}
	

}
