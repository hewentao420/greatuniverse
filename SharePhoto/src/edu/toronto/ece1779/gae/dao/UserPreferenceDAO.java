package edu.toronto.ece1779.gae.dao;

import edu.toronto.ece1779.gae.model.UserPrefs;

public interface UserPreferenceDAO {

	public boolean manageFavouriteUser(String ownerId, String favouriteUserId, boolean add);
	
	public UserPrefs getUserPrefs(String userId);
}
