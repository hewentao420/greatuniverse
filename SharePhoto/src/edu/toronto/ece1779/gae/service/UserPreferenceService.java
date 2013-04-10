package edu.toronto.ece1779.gae.service;

import edu.toronto.ece1779.gae.model.UserPrefs;

public interface UserPreferenceService {

	public boolean addFavouriteUser(String ownerId, String favouriteUserId);
	
	public boolean removeFavouriteUser(String ownerId, String favouriteUserId);
	
	public UserPrefs getUserPrefs(String userId);
	
}
