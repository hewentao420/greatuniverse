package edu.toronto.ece1779.gae.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.*;

import edu.toronto.ece1779.gae.model.Photo;

public class SearchPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ClearCache(response);

		ArrayList<Photo> list = new ArrayList<Photo>();
		Photo photo1 = new Photo();
		photo1.setImageId(123);
		photo1.setUserId("Jason");
		photo1.setTitle("My profile pic");
		photo1.setDescription("Nice photo");
		photo1.setWeather("sunny");
		photo1.setTime("morning");
		photo1.setAperture("45F");
		photo1.setIso(800);
		photo1.setShutterSpeed(100);
		photo1.setLatitude(12.3456);
		photo1.setLongitude(23.4532);
		photo1.setUrl_small("https://lh4.googleusercontent.com/-YiREmvlXE4M/Theg088FkyI/AAAAAAAAAmI/rICo95CGQzU/w759-h767-p-o-k/DSCN2136.JPG");
		photo1.setUrl_big("https://lh4.googleusercontent.com/-YiREmvlXE4M/Theg088FkyI/AAAAAAAAAmI/rICo95CGQzU/w759-h767-p-o-k/DSCN2136.JPG");
		list.add(photo1);

		Photo photo2 = new Photo();
		photo2.setImageId(124);
		photo2.setUserId("David");
		photo2.setTitle("My dinner");
		photo2.setDescription("Hotpot");
		photo2.setWeather("cloudy");
		photo2.setTime("evening");
		photo2.setAperture("60F");
		photo2.setIso(600);
		photo2.setShutterSpeed(200);
		photo2.setLatitude(-22.5631);
		photo2.setLongitude(11.3228);
		photo2.setUrl_small("https://lh5.googleusercontent.com/-22xqzpO0nSg/SE4H8QJH66I/AAAAAAAAAQc/hZyH7z6kQaI/s679/Dinner+in+the+balcony.JPG");
		photo2.setUrl_big("https://lh5.googleusercontent.com/-22xqzpO0nSg/SE4H8QJH66I/AAAAAAAAAQc/hZyH7z6kQaI/s679/Dinner+in+the+balcony.JPG");
		list.add(photo2);
		
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
