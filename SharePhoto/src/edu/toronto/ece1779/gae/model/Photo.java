package edu.toronto.ece1779.gae.model;

public class Photo {
	private String userId = null;
	private String weather = null; //ideally use Enum type, here use String type to be simple. Same for the following fields.
	private String time = null; 
	private double latitude = Double.MIN_VALUE;
	private double longitude = Double.MIN_VALUE;
	private String aperture = null;
	private int shutterSpeed = Integer.MIN_VALUE;
	private int iso = Integer.MIN_VALUE;
	private int imageId = Integer.MIN_VALUE;
	private String title = null;
	private String description = null;
	private String url_small = null;
	private String url_big = null;

	
	public Photo() {
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

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
}
