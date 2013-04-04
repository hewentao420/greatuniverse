package edu.toronto.ece1779.gae.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import edu.toronto.ece1779.gae.model.Comment;
import edu.toronto.ece1779.gae.model.Photo;
import edu.toronto.ece1779.gae.model.SearchCriteria;

public class PhotoDAOImpl implements PhotoDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Photo> searchPhotos(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub

		List<Photo> photoList = new ArrayList<Photo>();
		EntityManagerFactory emf = EMF.get();
		EntityManager em = null;
		Query query = null;
		em = emf.createEntityManager();

		List<String> weatherList = new ArrayList<String>();
		List<String> timeList = new ArrayList<String>();

		weatherList.add("'timeList'");
		weatherList.add("'timeList'");
		weatherList.add("'timeList'");
		weatherList.add("'timeList'");

		timeList.add("'morning'");
		timeList.add("'noon'");
		timeList.add("'afternoon'");
		timeList.add("'evening'");
		timeList.add("'night'");

		try {
			if (searchCriteria.getUserName().equals("")) {
				if (searchCriteria.getKeyword().equals("")) {
					query = em.createQuery("SELECT p from Photo p"
							+ " WHERE p.weather IN (:weatherList)"
							+ " AND p.time in(:timeList)"
							+ " AND p.latitude > :latitudeFrom"
							+ " AND p.latitude < :latitudeFrom"
							+ " AND p.longitude > :longitudeFrom"
							+ " AND p.longitude < :longitudeTo");
				} else {
					query = em.createQuery("SELECT p from Photo p"
							+ " WHERE p.weather IN (:weatherList)"
							+ " AND p.time in(:timeList)"
							+ " AND p.latitude > :latitudeFrom"
							+ " AND p.latitude < :latitudeFrom"
							+ " AND p.longitude > :longitudeFrom"
							+ " AND p.longitude < :longitudeTo"
							+ " AND p.keyword LIKE :keyword");
					query.setParameter("keyword",
							"'" + "%" + searchCriteria.getKeyword() + "%" + "'");

				}
			} else {
				if (searchCriteria.getKeyword().equals("")) {
					query = em.createQuery("SELECT p from Photo p"
							+ " WHERE p.weather IN (:weatherList)"
							+ " AND p.time in(:timeList)"
							+ " AND p.latitude > :latitudeFrom"
							+ " AND p.latitude < :latitudeFrom"
							+ " AND p.longitude > :longitudeFrom"
							+ " AND p.longitude < :longitudeTo"
							+ " AND p.userName = :userName");
					query.setParameter("userName", searchCriteria.getUserName());
				} else {
					query = em.createQuery("SELECT p from Photo p"
							+ " WHERE p.weather IN (:weatherList)"
							+ " AND p.time in(:timeList)"
							+ " AND p.latitude > :latitudeFrom"
							+ " AND p.latitude < :latitudeFrom"
							+ " AND p.longitude > :longitudeFrom"
							+ " AND p.longitude < :longitudeTo"
							+ " AND p.keyword LIKE :keyword"
							+ " AND p.userName = :userName");
					query.setParameter("keyword",
							"'" + "%" + searchCriteria.getKeyword() + "%" + "'");
					query.setParameter("userName", searchCriteria.getUserName());
				}
			}
			if (searchCriteria.getWeather().equals("")) {
				query.setParameter("weatherList", weatherList);
			} else {
				query.setParameter("weatherList",
						"'" + searchCriteria.getWeather() + "'");
			}
			if (searchCriteria.getTime().equals("")) {
				query.setParameter("timeList", timeList);
			} else {
				query.setParameter("timeList", "'" + searchCriteria.getTime()
						+ "'");
			}
			query.setParameter("latitudeFrom", searchCriteria.getLatitudeFrom());
			query.setParameter("latitudeTo", searchCriteria.getLatitudeTo());
			query.setParameter("longitudeFrom",
					searchCriteria.getLongitudeFrom());
			query.setParameter("longitudeTo", searchCriteria.getLongitudeTo());
			
			photoList = (List<Photo>) query.getResultList();
		} finally {
			em.close();
		}

		return photoList;
	}

	@Override
	public List<Comment> retrieveComments(Photo photo) {
		// TODO Auto-generated method stub

		return null;
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

	@Override
	public void addPhoto(Photo photo) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = EMF.get();
		EntityManager em = null;

		try {
			em = emf.createEntityManager();
			em.persist(photo);
		} catch (Exception e) {
			System.out.print(e);

		} finally {
			em.close();
		}
	}
}
