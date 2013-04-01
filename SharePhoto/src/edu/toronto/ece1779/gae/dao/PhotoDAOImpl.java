package edu.toronto.ece1779.gae.dao;

import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.Entity;

import edu.toronto.ece1779.gae.model.Comment;
import edu.toronto.ece1779.gae.model.Photo;
import edu.toronto.ece1779.gae.model.SearchCriteria;

public class PhotoDAOImpl implements PhotoDAO {

	@Override
	public List<Photo> searchPhotos(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> retrieveComments(Photo photo) {
		// TODO Auto-generated method stub
		
		
		return null;
	}

	@Override
	public void addComment(Comment comment) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void addPhoto(Photo photo) {
		// TODO Auto-generated method stub
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		try{
			//Create a ancestor key for photos according to userName
			String userId;
			if(!photo.getUserId().equals(null)){
				userId = photo.getUserId();	
			}else{
				userId = "default";
			}
	        Key userIdKey = KeyFactory.createKey("userId",userId);
	        
	        //create a new photo entity
			Entity newPhoto = new Entity("Photo", userIdKey);
			
			//set mandatory property
			newPhoto.setProperty("userId", photo.getUserId());
			newPhoto.setProperty("weather",photo.getWeather());
			newPhoto.setProperty("time",photo.getTime());
			newPhoto.setProperty("latitude",photo.getLatitude());
			newPhoto.setProperty("Longitud",photo.getLongitude());
			
			//set not mandatory property
			if (!photo.getAperture().equals(null)){
				newPhoto.setProperty("aperture",photo.getAperture());
			}else{
				newPhoto.setProperty("aperture","defaut");
			}
			
			if (photo.getShutterSpeed() != Integer.MIN_VALUE){
				newPhoto.setProperty("shutterSpeed",photo.getShutterSpeed());
			}else{
				newPhoto.setProperty("shutterSpeed","defaut");
			}
			
			if (photo.getIso() != Integer.MIN_VALUE){
				newPhoto.setProperty("iso",photo.getIso());
			}else{
				newPhoto.setProperty("iso","defaut");
			}
			
			if (photo.getImageId() != Integer.MIN_VALUE){
				newPhoto.setProperty("imageId",photo.getImageId());
			}else{
				newPhoto.setProperty("imageId","defaut");
			}
			
			if (!photo.getTitle().equals(null)){
				newPhoto.setProperty("title",photo.getTitle());
			}else{
				newPhoto.setProperty("title","defaut");
			}
			
			if (!photo.getDescription().equals(null)){
				newPhoto.setProperty("description",photo.getDescription());
			}else{
				newPhoto.setProperty("description","defaut");
			}
			
			if (!photo.getUrl_small().equals(null)){
				newPhoto.setProperty("url_small",photo.getUrl_small());
			}else{
				newPhoto.setProperty("url_small","defaut");
			}
			
			if (!photo.getUrl_big().equals(null)){
				newPhoto.setProperty("url_big",photo.getUrl_big());
			}else{
				newPhoto.setProperty("url_big","defaut");
			}
			
			//put newPhoto into datastore
			datastore.put(newPhoto);
		
			//commit transaction
			txn.commit();

		}catch(Exception e){
			System.out.println(e);
		}finally{
		    if (txn.isActive()) {
		        txn.rollback();
		    }	
		}
	}
}
