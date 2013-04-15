package edu.toronto.ece1779.gae.dao;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.commons.fileupload.FileItemStream;

import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;
import com.google.appengine.api.files.GSFileOptions.GSFileOptionsBuilder;

import edu.toronto.ece1779.gae.model.Photo;
import edu.toronto.ece1779.gae.model.SearchCriteria;

public class PhotoDAOImpl implements PhotoDAO {

	private static int BUFFER_SIZE = 1024;
	private String BUCKETNAME = "ece1779";
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Photo> searchPhotos(SearchCriteria searchCriteria) {

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

		String sql = "SELECT p from Photo p"
				+ " WHERE p.weather IN (:weatherList)"
				+ " AND p.time in(:timeList)"
				+ " AND p.latitude > :latitudeFrom"
				+ " AND p.latitude < :latitudeTo";
		
		
		if(!searchCriteria.getUserName().equals("")){
			sql += " AND p.userId = :userId";
		}
		
		try {
			List<Photo> results = new ArrayList<Photo>();
			if(searchCriteria.getKeyword().equals("")){
				
				query = em.createQuery(sql);
				query.setParameter("weatherList", weatherList);
				query.setParameter("timeList", timeList);
				query.setParameter("latitudeFrom", searchCriteria.getLatitudeFrom());
				query.setParameter("latitudeTo", searchCriteria.getLatitudeTo());

				if(!searchCriteria.getUserName().equals("")){
					query.setParameter("userId", searchCriteria.getUserName());
				}
	
				results = query.getResultList();
			}
			else {
				
				for(int i=1; i<6; i++) {
					String sqlTmp = sql+" AND tag"+i+" = :key";
					
					query = em.createQuery(sqlTmp);
					query.setParameter("weatherList", weatherList);
					query.setParameter("timeList", timeList);
					query.setParameter("latitudeFrom", searchCriteria.getLatitudeFrom());
					query.setParameter("latitudeTo", searchCriteria.getLatitudeTo());
					
					if(!searchCriteria.getKeyword().equals("")){
						query.setParameter("key", searchCriteria.getKeyword());
					}
					
					results.addAll(query.getResultList());
				}
			}
			
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
	
	
	@Override
	public void uploadPhotoToCloudStorage(String fileName, FileItemStream item) throws IOException{
		InputStream is = new BufferedInputStream(item.openStream());

		byte[] b = new byte[BUFFER_SIZE];

		// store the photo to the google cloud storage
		FileService fileService = FileServiceFactory
				.getFileService();
		GSFileOptionsBuilder optionsBuilder = new GSFileOptionsBuilder()
				.setBucket(BUCKETNAME).setKey(fileName)
				.setMimeType("image/jpeg").setAcl("public_read")
				.addUserMetadata("myfield1", "my field value");
		AppEngineFile writableFile = fileService
				.createNewGSFile(optionsBuilder.build());

		// Open a channel to write to it
		boolean lock = true;
		FileWriteChannel writeChannel = fileService
				.openWriteChannel(writableFile, lock);

		int readBytes = is.read(b, 0, BUFFER_SIZE);
		OutputStream os = Channels.newOutputStream(writeChannel);
		while (readBytes > 0) {
			// writeChannel.write(ByteBuffer.wrap(b));
			os.write(b, 0, readBytes);
			os.flush();
			readBytes = is.read(b, 0, BUFFER_SIZE);
		}

		// Now finalize
		writeChannel.closeFinally();
		is.close();		
	}
}
