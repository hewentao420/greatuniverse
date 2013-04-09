package edu.toronto.ece1779.gae.dao;

public interface ManageFavouriteUserDAO {

	public boolean manageFavouriteUser(String ownerId, String favouriteUserId, boolean add);
	
}
