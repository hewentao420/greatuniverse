package edu.toronto.ece1779.gae.dao;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import edu.toronto.ece1779.gae.model.Comment;
import edu.toronto.ece1779.gae.model.Photo;

public class CommentDAOImpl implements CommentDAO {

	@Override
	public List<Comment> retrieveComments(Photo photo) {
		List<Comment> commentList = new ArrayList<Comment>();
		Long imageId = photo.getImageKey();

		EntityManagerFactory emf = EMF.get();
		EntityManager em = null;
		Query query = null;
		em = emf.createEntityManager();

		try {
			query = em
					.createQuery("select c from Comment c where c.imageId = :imageId");
			query.setParameter("imageId", imageId);
			
			List results = query.getResultList();
			if(results.size() != 0) {
				Iterator it = results.iterator();
				while(it.hasNext()) {
					Comment comment = (Comment)it.next();
					commentList.add(comment);
				}
			}
		} finally {
			em.close();
		}

		return commentList;
	}

	@Override
	public void addComment(Comment comment) {
		
		PhotoDAO photoDAO = new PhotoDAOImpl();
		Photo photo = photoDAO.retrievePhoto(comment.getImageId());
		int previousRating = photo.getRating();
		int previousCommentedTimes = photo.getCommentedTimes();
		
		int averageRating = (previousRating*previousCommentedTimes + comment.getRating())/(previousCommentedTimes+1);
		
		photo.setRating(averageRating);
		photo.setCommentedTimes(previousCommentedTimes+1);
		
		EntityManagerFactory emf = EMF.get();
		EntityManager em = null;

		try {
			em = emf.createEntityManager();
			//em.getTransaction().begin();
			//em.getTransaction().commit();
			em.persist(comment); 
			
			EntityTransaction txn = em.getTransaction();
	        txn.begin();
	        try {
	        	Photo photo2 = em.find(Photo.class, comment.getImageId());
	    		photo2.setRating(averageRating);
	    		photo2.setCommentedTimes(previousCommentedTimes+1);
	            txn.commit();
	        } finally {
	            if (txn.isActive()) {
	                txn.rollback();
	            }
	        }			
			
		} catch (Exception e) {
			System.out.print(e);
		} finally {
			em.close();
		}
	}
	
}
