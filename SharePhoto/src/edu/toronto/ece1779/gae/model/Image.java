package edu.toronto.ece1779.gae.model;

public class Image {
	
	private int imageId;
	private String userId;
	private String title;
	private String description;
	private String weather; //ideally use Enum type, here use String type to be simple. Same for the following fields.
	private String time; 
	private String aperture;
	private int iso;
	private int shutterSpeed;
	private String url_small;
	private String url_big;
	
	public Image() {
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAperture() {
		return aperture;
	}

	public void setAperture(String aperture) {
		this.aperture = aperture;
	}

	public int getIso() {
		return iso;
	}

	public void setIso(int iso) {
		this.iso = iso;
	}

	public int getShutterSpeed() {
		return shutterSpeed;
	}

	public void setShutterSpeed(int shutterSpeed) {
		this.shutterSpeed = shutterSpeed;
	}

	public String getUrl_small() {
		return url_small;
	}

	public void setUrl_small(String url_small) {
		this.url_small = url_small;
	}

	public String getUrl_big() {
		return url_big;
	}

	public void setUrl_big(String url_big) {
		this.url_big = url_big;
	}
	
	
}
