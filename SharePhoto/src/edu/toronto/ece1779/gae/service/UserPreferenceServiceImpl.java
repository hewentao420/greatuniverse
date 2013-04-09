package edu.toronto.ece1779.gae.service;

import edu.toronto.ece1779.gae.dao.UserPreferenceDAO;
import edu.toronto.ece1779.gae.dao.UserPreferenceDAOImpl;

public class UserPreferenceServiceImpl implements UserPreferenceService {

	@Override
	public boolean addFavouriteUser(String ownerId, String favouriteUserId) {
		boolean isAdded;
		UserPreferenceDAO userPreferenceDAO = new UserPreferenceDAOImpl();
		isAdded = userPreferenceDAO.manageFavouriteUser(ownerId, favouriteUserId, true);
		return isAdded;
	}

	@Override
	public boolean removeFavouriteUser(String ownerId, String favouriteUserId) {
		boolean isRemoved;
		UserPreferenceDAO userPreferenceDAO = new UserPreferenceDAOImpl();
		isRemoved = userPreferenceDAO.manageFavouriteUser(ownerId, favouriteUserId, false);
		return isRemoved;
	}

	
	
}
