package edu.toronto.ece1779.gae.model;

public class SearchCriteria {
	private String userName;
	private String keyword;
	private String weather; //ideally use Enum type, here use String type to be simple. Same for the following fields.
	private String time; 
	private double latitudeFrom;
	private double latitudeTo;
	private double longitudeFrom;
	private double longitudeTo;
	private String ownerId;
	
	public SearchCriteria(String userName, String keyword, String weather, String time,
			double latitudeFrom, double latitudeTo, double longitudeFrom,
			double longitudeTo, String ownerId) {
		super();
		this.userName = userName;
		this.keyword = keyword;
		this.weather = weather;
		this.time = time;
		this.latitudeFrom = latitudeFrom;
		this.latitudeTo = latitudeTo;
		this.longitudeFrom = longitudeFrom;
		this.longitudeTo = longitudeTo;
		this.ownerId = ownerId;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
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
	public double getLatitudeFrom() {
		return latitudeFrom;
	}
	public void setLatitudeFrom(double latitudeFrom) {
		this.latitudeFrom = latitudeFrom;
	}
	public double getLatitudeTo() {
		return latitudeTo;
	}
	public void setLatitudeTo(double latitudeTo) {
		this.latitudeTo = latitudeTo;
	}
	public double getLongitudeFrom() {
		return longitudeFrom;
	}
	public void setLongitudeFrom(double longitudeFrom) {
		this.longitudeFrom = longitudeFrom;
	}
	public double getLongitudeTo() {
		return longitudeTo;
	}
	public void setLongitudeTo(double longitudeTo) {
		this.longitudeTo = longitudeTo;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	
}
