package edu.toronto.ece1779.gae.servlet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;
import com.google.appengine.api.files.GSFileOptions.GSFileOptionsBuilder;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import edu.toronto.ece1779.gae.model.Photo;
import edu.toronto.ece1779.gae.service.PhotoService;
import edu.toronto.ece1779.gae.service.PhotoServiceImpl;
import edu.toronto.ece1779.gae.util.Constants;

public class AddPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int BUFFER_SIZE = 1024;
	private String BUCKETNAME = "ece1779";
	private String random_ID;
	private String fileName;
	private String smallFileName;
	private String url_big;
	private String url_small;

	
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		if(user == null) {
			
			//TODO: return msg: need to login
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
				
				if(item.isFormField()) {
					InputStream steam = item.openStream();
					String fieldName = item.getFieldName();
					String value = Streams.asString(steam);
					System.out.println("fieldName: " + fieldName + "; value" + value);
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
					
					// response.getWriter().println(fileName);
					// response.getWriter().println("\n");
					// response.getWriter().println(url_big);
					// response.getWriter().println("\n");

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
				
				//response.sendRedirect("/addPhoto.jsp");
			}
			
			//TODO potential null pointer exception below
			Photo photo = new Photo();
			photo.setUserId(user.getEmail());
			photo.setIso(Integer.parseInt(fieldMap.get("iso")));
			photo.setAperture(fieldMap.get("aperture"));
			photo.setShutterSpeed(Integer.parseInt(fieldMap.get("shutter_speed")));
			photo.setTime(fieldMap.get("time"));
			photo.setWeather(fieldMap.get("weather"));
			photo.setTitle(fieldMap.get("title"));
			photo.setDescription(fieldMap.get("description"));
			photo.setLatitude(Double.parseDouble(fieldMap.get("latitude")));
			photo.setLongitude(Double.parseDouble(fieldMap.get("longitude")));;
			photo.setUrl_big(fieldMap.get("url_big"));
			photo.setUrl_small(fieldMap.get("url_small"));
			photo.setNickName(user.getNickname());
			
			//add tags
			String tagsStr = fieldMap.get("tags");
			String[] tags = tagsStr.split(",");
			
			setTags(photo, tags);
			
			PhotoService service = new PhotoServiceImpl();
			service.addPhoto(photo);
			
		} catch (Exception e) {
			e.printStackTrace(response.getWriter());
			System.out.println("Exception::" + e.getMessage());
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Constants.MAP_JSP);
		dispatcher.forward(request,response);
		
	}



	private void setTags(Photo photo, String[] tags) {
		if(tags.length == 1 && !tags[0].equals("")) {
			photo.setTag1(tags[0]);
		}
		else if(tags.length == 2) {
			photo.setTag1(tags[0]);
			photo.setTag2(tags[1]);
		}
		else if(tags.length == 3) {
			photo.setTag1(tags[0]);
			photo.setTag2(tags[1]);
			photo.setTag3(tags[2]);
		}
		else if(tags.length == 4) {
			photo.setTag1(tags[0]);
			photo.setTag2(tags[1]);
			photo.setTag3(tags[2]);
			photo.setTag4(tags[3]);
		}
		else if(tags.length == 5) {
			photo.setTag1(tags[0]);
			photo.setTag2(tags[1]);
			photo.setTag3(tags[2]);
			photo.setTag4(tags[3]);
			photo.setTag5(tags[4]);
		}
	}

}
