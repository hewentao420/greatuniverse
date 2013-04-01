package edu.toronto.ece1779.gae.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import net.sf.json.JSONArray;
import edu.toronto.ece1779.gae.model.Photo;
import edu.toronto.ece1779.gae.model.SearchCriteria;
import edu.toronto.ece1779.gae.service.PhotoService;
import edu.toronto.ece1779.gae.service.PhotoServiceImpl;

public class SearchPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ClearCache(response);

		//get user
		UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
		//parse the request, construct the search criteria object
		String weather = request.getParameter("weather");
		String time = request.getParameter("time");
		String keyword = request.getParameter("keyword");
		double lat1 = Double.parseDouble(request.getParameter("lat1"));
		double lat2 = Double.parseDouble(request.getParameter("lat2"));
		double lng1 = Double.parseDouble(request.getParameter("lng1"));
		double lng2 = Double.parseDouble(request.getParameter("lng2"));
		System.out.println("\nParameters from UI - user: " + user.toString() +"; weather: " + weather + " ;time: " + time + " ;keyword: " + keyword
				+ " ;lat1: " + lat1 + " ;lat2: " + lat2 + " ;lng1: " + lng1 + " ;lng2: " + lng2);
		
		SearchCriteria searchCriteria = new SearchCriteria(user.toString(), keyword, weather, time, lat1, lat2, lng1, lng2, ""); 
		//TODO last parameter of searchCriteria is ownerId, needs to get it from UI.
		PhotoService photoService = new PhotoServiceImpl();
		List<Photo> searchResult = photoService.searchPhotos(searchCriteria);
		
		JSONArray json = JSONArray.fromObject(searchResult);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json.toString());
		System.out.print("JSON String: " + json.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}

	
	public void ClearCache(HttpServletResponse response) {
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setHeader("expires", "0");
	}
	
}
