package edu.toronto.ece1779.gae.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Photo")
public class Photo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long imageKey;
	
	@Basic
	private String userId;
	@Basic
	private String nickName;
	@Basic
	private String weather;
	@Basic
	private String time;
	@Basic
	private double latitude;
	@Basic
	private double longitude;
	@Basic
	private String aperture;
	@Basic
	private int shutterSpeed;
	@Basic
	private int iso;
	@Basic
	private String title;
	@Basic
	private String description;
	@Basic
	private String url_small;
	@Basic
	private String url_big;
	
	public void setImageKey(Long imageKey){
		this.imageKey = imageKey;
	}
	
	public Long getImageKey(){
		return imageKey;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
}
