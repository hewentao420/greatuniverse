package edu.toronto.ece1779.gae.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import edu.toronto.ece1779.gae.model.Photo;
import edu.toronto.ece1779.gae.service.PhotoService;
import edu.toronto.ece1779.gae.service.PhotoServiceImpl;
import edu.toronto.ece1779.gae.util.Constants;

public class AddPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String random_ID;
	private String fileName;
	private String smallFileName;
	private String url_big;
	private String url_small;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		if (user == null) {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("Please login first...");
			response.getWriter().flush();
			response.getWriter().close();
			return;
		}

		// get upload data
		ServletFileUpload upload = new ServletFileUpload();
		FileItemIterator iter;

		try {
			iter = upload.getItemIterator(request);

			HashMap<String, String> fieldMap = new HashMap<String, String>();
			fieldMap.put("userId", user.getEmail());

			while (iter.hasNext()) {
				FileItemStream item = iter.next();

				if (item.isFormField()) {
					InputStream steam = item.openStream();
					String fieldName = item.getFieldName();
					String value = Streams.asString(steam);
					System.out.println("fieldName: " + fieldName + "; value"
							+ value);
					fieldMap.put(fieldName, value);
				}

				String mime = item.getContentType();
				if (mime == null) {
					mime = "null";
				}

				// handle image data
				if (mime.equals("image/jpeg")) {

					random_ID = UUID.randomUUID().toString();
					fileName = random_ID + "_big_" + item.getName();
					smallFileName = random_ID + "_small_" + item.getName();
					url_big = "http://storage.googleapis.com/ece1779/"
							+ fileName;
					url_small = "http://storage.googleapis.com/ece1779/"
							+ smallFileName;

					fieldMap.put("url_big", url_big);
					fieldMap.put("url_small", url_big);

					PhotoService photoService = new PhotoServiceImpl();
					photoService.uploadPhotoToCloudStorage(fileName, item);
				}
			}

			Photo photo = new Photo();
			photo.setUserId(user.getEmail());
			if (fieldMap.get("aperture").equals("")) {
				photo.setAperture("Default");
			} else {
				photo.setAperture(fieldMap.get("aperture"));
			}
			if (fieldMap.get("shutter_speed").equals("")) {
				photo.setShutterSpeed(-1);
			} else {
				photo.setShutterSpeed(Integer.parseInt(fieldMap
						.get("shutter_speed")));
			}
			if (fieldMap.get("iso").equals("")) {
				photo.setIso(-1);
			} else {
				photo.setIso(Integer.parseInt(fieldMap.get("iso")));
			}
			photo.setTime(fieldMap.get("time"));
			photo.setWeather(fieldMap.get("weather"));
			photo.setTitle(fieldMap.get("title"));
			photo.setDescription(fieldMap.get("description"));
			photo.setLatitude(Double.parseDouble(fieldMap.get("latitude")));
			photo.setLongitude(Double.parseDouble(fieldMap.get("longitude")));
			;
			photo.setUrl_big(fieldMap.get("url_big"));
			photo.setUrl_small(fieldMap.get("url_small"));
			photo.setNickName(user.getNickname());

			// add tags
			String tagsStr = fieldMap.get("tags");
			String[] tags = tagsStr.split(",");

			setTags(photo, tags);

			PhotoService service = new PhotoServiceImpl();
			service.addPhoto(photo);

			updateMemcache(photo);
		} catch (Exception e) {
			e.printStackTrace(response.getWriter());
			System.out.println("Exception::" + e.getMessage());
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(Constants.MAP_JSP);
		dispatcher.forward(request, response);

	}

	@SuppressWarnings("unchecked")
	private void updateMemcache(Photo photo) {
		MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
		syncCache.setErrorHandler(ErrorHandlers
				.getConsistentLogAndContinue(Level.INFO));
		List<Photo> searchResultInMemcache = (List<Photo>) syncCache
				.get(Constants.COMMON_SEARCH_RESULT);
		if (searchResultInMemcache != null) {
			searchResultInMemcache.add(photo);
			syncCache.put(Constants.COMMON_SEARCH_RESULT,
					searchResultInMemcache);
		}
	}

	private void setTags(Photo photo, String[] tags) {
		if (tags.length == 1 && !tags[0].equals("")) {
			photo.setTag1(tags[0]);
		} else if (tags.length == 2) {
			photo.setTag1(tags[0]);
			photo.setTag2(tags[1]);
		} else if (tags.length == 3) {
			photo.setTag1(tags[0]);
			photo.setTag2(tags[1]);
			photo.setTag3(tags[2]);
		} else if (tags.length == 4) {
			photo.setTag1(tags[0]);
			photo.setTag2(tags[1]);
			photo.setTag3(tags[2]);
			photo.setTag4(tags[3]);
		} else if (tags.length == 5) {
			photo.setTag1(tags[0]);
			photo.setTag2(tags[1]);
			photo.setTag3(tags[2]);
			photo.setTag4(tags[3]);
			photo.setTag5(tags[4]);
		}
	}

}
