package edu.toronto.ece1779.gae.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.*;

import edu.toronto.ece1779.gae.model.Image;

public class SearchPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ClearCache(response);

		ArrayList<Image> list = new ArrayList<Image>();
		Image user1 = new Image();
		user1.setImageId(123);
		user1.setUserId("Jason");
		user1.setTitle("My profile");
		user1.setDescription("Nice photo");
		user1.setWeather("sunny");
		user1.setTime("morning");
		user1.setAperture("45F");
		user1.setIso(800);
		user1.setShutterSpeed(100);
		user1.setUrl_small("https://lh4.googleusercontent.com/-YiREmvlXE4M/Theg088FkyI/AAAAAAAAAmI/rICo95CGQzU/w759-h767-p-o-k/DSCN2136.JPG");
		user1.setUrl_big("https://lh4.googleusercontent.com/-YiREmvlXE4M/Theg088FkyI/AAAAAAAAAmI/rICo95CGQzU/w759-h767-p-o-k/DSCN2136.JPG");
		list.add(user1);

		Image user2 = new Image();
		user2.setImageId(124);
		user2.setUserId("David");
		user2.setTitle("My dinner");
		user2.setDescription("Hotpot");
		user2.setWeather("cloudy");
		user2.setTime("evening");
		user2.setAperture("60F");
		user2.setIso(600);
		user2.setShutterSpeed(200);
		user2.setUrl_small("https://lh5.googleusercontent.com/-22xqzpO0nSg/SE4H8QJH66I/AAAAAAAAAQc/hZyH7z6kQaI/s679/Dinner+in+the+balcony.JPG");
		user2.setUrl_big("https://lh5.googleusercontent.com/-22xqzpO0nSg/SE4H8QJH66I/AAAAAAAAAQc/hZyH7z6kQaI/s679/Dinner+in+the+balcony.JPG");
		list.add(user2);
		
		response.setCharacterEncoding("utf-8");
		JSONArray json1 = JSONArray.fromObject(list);
		response.getWriter().write(json1.toString());
		System.out.print(json1.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}

	public void ClearCache(HttpServletResponse response) {
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setHeader("expires", "0");
	}
}
