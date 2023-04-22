package it.etlabora.SpringWebBiblioteca.persisteza;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
//@Configuration
//@ComponentScan
public class PersistenceUtil {
	private static EntityManagerFactory emf = null;

	public static EntityManagerFactory getEmf() {
		if(emf==null) {
			emf = Persistence.createEntityManagerFactory("persistence");
		}
			 
		return emf;
	}
	
	
	
	
	//@Bean
//	public EntityManagerFactory emf() {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
//		return emf;
//	}
}
