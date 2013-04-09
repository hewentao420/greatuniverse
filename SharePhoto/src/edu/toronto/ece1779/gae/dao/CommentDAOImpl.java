package edu.toronto.ece1779.gae.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import edu.toronto.ece1779.gae.model.Comment;
import edu.toronto.ece1779.gae.model.Photo;

public class CommentDAOImpl implements CommentDAO {

	@Override
	public List<Comment> retrieveComments(Photo photo) {
		// TODO Auto-generated method stub

		List<Comment> commentList = new ArrayList<Comment>();
		long imageId = photo.getImageKey();

		EntityManagerFactory emf = EMF.get();
		EntityManager em = null;
		Query query = null;
		em = emf.createEntityManager();

		try {
			query = em
					.createQuery("select c from Comment c where c.imageId = :imageId");
			query.setParameter("imageId", imageId);
//			query = em
//					.createQuery("select c from Comment c where c.rating = 1");
//			//query.setParameter("nickName", "Wentao");
			
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
		// TODO Auto-generated method stub
		EntityManagerFactory emf = EMF.get();
		EntityManager em = null;

		try {
			em = emf.createEntityManager();
			em.persist(comment);
		} catch (Exception e) {
			System.out.print(e);
		} finally {
			em.close();
		}
	}
	
}
