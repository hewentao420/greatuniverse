package edu.toronto.ece1779.gae.service;

public interface ManageFavouriteUserService {

	public boolean addFavouriteUser(String ownerId, String favouriteUserId);
	
	public boolean removeFavouriteUser(String ownerId, String favouriteUserId);
	
}
