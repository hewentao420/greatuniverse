package edu.toronto.ece1779.gae.model;

import java.io.Serializable;

public class FavouriteUser implements Serializable {
	private static final long serialVersionUID = 1L;

	private String userId;
	private String nickName;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
}
