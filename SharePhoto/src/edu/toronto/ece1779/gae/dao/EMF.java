package edu.toronto.ece1779.gae.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EMF {
	private static final EntityManagerFactory emfInstance = Persistence
			.createEntityManagerFactory("transaction=optional");
	private EMF(){}
	
	public static EntityManagerFactory get(){
		return emfInstance;
	}
}