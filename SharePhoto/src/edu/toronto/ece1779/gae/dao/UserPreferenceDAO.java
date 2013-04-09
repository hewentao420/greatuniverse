package edu.toronto.ece1779.gae.dao;

public interface UserPreferenceDAO {

	public boolean manageFavouriteUser(String ownerId, String favouriteUserId, boolean add);
	
}
