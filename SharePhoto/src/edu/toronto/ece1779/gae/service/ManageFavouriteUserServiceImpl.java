package edu.toronto.ece1779.gae.service;

import edu.toronto.ece1779.gae.dao.ManageFavouriteUserDAO;
import edu.toronto.ece1779.gae.dao.ManageFavouriteUserDAOImpl;

public class ManageFavouriteUserServiceImpl implements ManageFavouriteUserService {

	@Override
	public boolean addFavouriteUser(String ownerId, String favouriteUserId) {
		boolean isAdded;
		ManageFavouriteUserDAO manageFavouriteUserDAO = new ManageFavouriteUserDAOImpl();
		isAdded = manageFavouriteUserDAO.manageFavouriteUser(ownerId, favouriteUserId, true);
		return isAdded;
	}

	@Override
	public boolean removeFavouriteUser(String ownerId, String favouriteUserId) {
		boolean isRemoved;
		ManageFavouriteUserDAO manageFavouriteUserDAO = new ManageFavouriteUserDAOImpl();
		isRemoved = manageFavouriteUserDAO.manageFavouriteUser(ownerId, favouriteUserId, false);
		return isRemoved;
	}

	
	
}
