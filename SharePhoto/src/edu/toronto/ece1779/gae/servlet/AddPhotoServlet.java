package edu.toronto.ece1779.gae.servlet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.util.UUID;

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
		        
		// get upload data
		ServletFileUpload upload = new ServletFileUpload();
		FileItemIterator iter;
		
		try {
			iter = upload.getItemIterator(request);
			while (iter.hasNext()) {
				FileItemStream item = iter.next();
				
				if(item.isFormField()) {
					InputStream steam = item.openStream();
					String fieldName = item.getFieldName();
					String value = Streams.asString(steam);
					System.out.println("fieldName: " + fieldName + "; value" + value);
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
		} catch (Exception e) {
			e.printStackTrace(response.getWriter());
			System.out.println("Exception::" + e.getMessage());
			e.printStackTrace();
		}
		
	}

}
