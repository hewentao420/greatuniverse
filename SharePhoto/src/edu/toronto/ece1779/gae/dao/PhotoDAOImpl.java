package edu.toronto.ece1779.gae.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.google.appengine.api.datastore.GeoPt;
import com.google.appengine.api.datastore.Key;

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
		
		if (searchCriteria.getWeather().equals("")) {
			weatherList.add("sunny");
			weatherList.add("snowy");
			weatherList.add("rainy");
			weatherList.add("cloudy");
		} else {
			weatherList.add(searchCriteria.getWeather());
		}
		
		if (searchCriteria.getTime().equals("")) {
			timeList.add("morning");
			timeList.add("noon");
			timeList.add("afternoon");
			timeList.add("evening");
			timeList.add("night");
		} else {
			timeList.add(searchCriteria.getTime());
		}

		//TODO: Only one inequality supported in GAE, Try another method
		String sql = "SELECT p from Photo p"
				+ " WHERE p.weather IN (:weatherList)"
				+ " AND p.time in(:timeList)"
				+ " AND p.latitude > :latitudeFrom"
				+ " AND p.latitude < :latitudeTo";
		
		
		if(!searchCriteria.getUserName().equals("")){
			sql += " AND p.userId = :userId";
		}

		//TODO: GAE doesn't support fuzzy search. Try another method
		//if(!searchCriteria.getKeyword().equals("")){
		//	sql += " AND p.title >= :key1 AND p.title < :key2";
		//}
		

		query = em.createQuery(sql);
		query.setParameter("weatherList", weatherList);
		query.setParameter("timeList", timeList);
		query.setParameter("latitudeFrom", searchCriteria.getLatitudeFrom());
		query.setParameter("latitudeTo", searchCriteria.getLatitudeTo());

		if(!searchCriteria.getUserName().equals("")){
			query.setParameter("userId", searchCriteria.getUserName());
		}

		//TODO: GAE doesn't support fuzzy search. Try another method
		//if(!searchCriteria.getKeyword().equals("")){
		//	query.setParameter("key1", searchCriteria.getKeyword());
		//	query.setParameter("key2", searchCriteria.getKeyword() + "/ufffd");
		//}
		
		
		try {
			List<Photo> results = query.getResultList();
			if(results.size() != 0) {
				Iterator<Photo> it = results.iterator();
				while(it.hasNext()) {
					Photo photo = (Photo)it.next();
					photoList.add(photo);
				}
			}
		} finally {
			em.close();
		}
		
		return photoList;
		
		/*
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
							+ " AND p.userId = :userName");
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
							+ " AND p.userId = :userName");
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
		*/
	}


	@Override
	public void addPhoto(Photo photo) {
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


	@Override
	public Photo retrievePhoto(Long imageKey) {
		Photo photo = new Photo();
		
		EntityManagerFactory emf = EMF.get();
		EntityManager em = null;
		Query query = null;
		em = emf.createEntityManager();

		try {
			query = em
					.createQuery("select p from Photo p where p.imageKey = :imageKey");
			query.setParameter("imageKey", imageKey);
			
			List results = query.getResultList();
			if(results.size() != 0) {
				photo = (Photo)results.get(0);
			}
		} finally {
			em.close();
		}

		return photo;
	}


	@Override
	public List<Photo> retrieveUserPhotos(String userId) {
		List<Photo> photoList = new ArrayList<Photo>();

		EntityManagerFactory emf = EMF.get();
		EntityManager em = null;
		Query query = null;
		em = emf.createEntityManager();

		try {
			query = em
					.createQuery("select p from Photo p where p.userId = :userId");
			query.setParameter("userId", userId);
			
			List results = query.getResultList();
			if(results.size() != 0) {
				Iterator it = results.iterator();
				while(it.hasNext()) {
					Photo photo = (Photo)it.next();
					photoList.add(photo);
				}
			}
		} finally {
			em.close();
		}

		return photoList;
	}
}
