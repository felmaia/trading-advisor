package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EM {

	private static EntityManager entityManager;
	private static EntityManagerFactory entityManagerFactory;
	
	public static synchronized EntityManager getInstance() {
		
		if (entityManager == null) {
			entityManagerFactory = Persistence
					.createEntityManagerFactory("trading_advisor_pu");
			
			entityManager = entityManagerFactory.createEntityManager();
		}
		return entityManager;
	}
	
	public static synchronized void close() {
		
		entityManager.close();
		entityManagerFactory.close();
	}
}
