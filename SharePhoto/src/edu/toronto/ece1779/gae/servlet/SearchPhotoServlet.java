package edu.toronto.ece1779.gae.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

import edu.toronto.ece1779.gae.model.Photo;
import edu.toronto.ece1779.gae.model.SearchCriteria;
import edu.toronto.ece1779.gae.service.PhotoService;
import edu.toronto.ece1779.gae.service.PhotoServiceImpl;

public class SearchPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ClearCache(response);
        
        SearchCriteria searchCriteria = constructSearchCriteriaFromRequest(request);
        	 
		MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
	    syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
	    String key = "commonSearchResult";
	    List<Photo> searchResult;
	
	    if(searchCriteria.isCommonSearchCriteria()){
	    	List<Photo> searchResultInMemcache = (List<Photo>) syncCache.get(key);
	    	if(searchResultInMemcache == null) {
	    		//SearchCriteria commonSearchCriteria = new SearchCriteria("", "", "", "", 0, 0, 0, 0);
				PhotoService photoService = new PhotoServiceImpl();
				searchResultInMemcache = photoService.searchPhotos(searchCriteria);
				syncCache.put(key, searchResultInMemcache);
	    	}
	    	searchResult = filterUnMatched(searchResultInMemcache, searchCriteria);
	    } else {
			PhotoService photoService = new PhotoServiceImpl();
			searchResult = photoService.searchPhotos(searchCriteria);
	    	//syncCache.put(key, searchResult);
	    }
	   		
		//construct json object
		JSONArray json = JSONArray.fromObject(searchResult);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json.toString());
		System.out.print("JSON String: " + json.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}

	
	public SearchCriteria constructSearchCriteriaFromRequest(HttpServletRequest request) {
		SearchCriteria searchCriteria = new SearchCriteria();
		//parse the request, construct the search criteria object
		String weather = request.getParameter("weather");
		String time = request.getParameter("time");
		String keyword = request.getParameter("keyword");
		double lat1 = Double.parseDouble(request.getParameter("lat1"));	
		double lat2 = Double.parseDouble(request.getParameter("lat2"));
		double lng1 = Double.parseDouble(request.getParameter("lng1"));
		double lng2 = Double.parseDouble(request.getParameter("lng2"));
		System.out.println("\nParameters from UI - weather: " + weather + " ;time: " + time + " ;keyword: " + keyword
				+ " ;lat1: " + lat1 + " ;lat2: " + lat2 + " ;lng1: " + lng1 + " ;lng2: " + lng2);
		searchCriteria = new SearchCriteria("", keyword, weather, time, lat1, lat2, lng1, lng2); 
		return searchCriteria;
	}
	
	
	public List<Photo> filterUnMatched(List<Photo> searchResultInMemcache, SearchCriteria searchCriteria){
		List<Photo> searchResult = new ArrayList<Photo>(); 
		if(searchResultInMemcache != null) {
			for(int i=0; i<searchResultInMemcache.size(); i++) {
				Photo photo = searchResultInMemcache.get(i);
				if(photo.getLatitude() >= searchCriteria.getLatitudeFrom() 
						&& photo.getLatitude() <= searchCriteria.getLatitudeTo()
						&& photo.getLongitude() >= searchCriteria.getLongitudeFrom()
						&& photo.getLongitude() <= searchCriteria.getLongitudeTo()) {
					searchResult.add(photo);
				}
			}
		}
		return searchResult;
	}
	
	
	public void ClearCache(HttpServletResponse response) {
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setHeader("expires", "0");
	}
	
	
}
