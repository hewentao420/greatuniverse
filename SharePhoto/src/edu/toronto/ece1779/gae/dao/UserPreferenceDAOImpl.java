package edu.toronto.ece1779.gae.dao;

import javax.persistence.EntityManager;

import edu.toronto.ece1779.gae.model.UserPrefs;

public class UserPreferenceDAOImpl implements UserPreferenceDAO {

	@Override
	public boolean manageFavouriteUser(String ownerId, String favouriteUserId,
			boolean add) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserPrefs getUserPrefs(String userId) {
		UserPrefs userPrefs = null;
		
		EntityManager em = EMF.get().createEntityManager();
        try {
            userPrefs = em.find(UserPrefs.class, userId);
            if (userPrefs == null) {
                userPrefs = new UserPrefs(userId);
                userPrefs.save();
            } 
        } finally {
            em.close();
        }
        
        return userPrefs;
	}
	
	
	

}
