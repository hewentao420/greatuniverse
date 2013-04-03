package edu.toronto.ece1779.gae.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
		EntityManagerFactory emf = EMF.get();
		EntityManager em = null; 
		
		try{
			em = emf.createEntityManager();
			em.persist(photo);
		}catch (Exception e){
			System.out.print(e);
			
		}finally{
			em.close();
		}
	}
}
