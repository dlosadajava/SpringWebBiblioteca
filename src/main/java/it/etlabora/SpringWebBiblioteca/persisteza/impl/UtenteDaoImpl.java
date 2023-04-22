package it.etlabora.SpringWebBiblioteca.persisteza.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.etlabora.SpringWebBiblioteca.modello.Utente;
import it.etlabora.SpringWebBiblioteca.persisteza.PersistenceUtil;
import it.etlabora.SpringWebBiblioteca.persisteza.UtenteDao;

@Repository//spring
public class UtenteDaoImpl implements UtenteDao{
	//private static final String FIND_BY_USERNAME="Select * from utente where username= ?";
	
	private static final String FIND_BY_USERNAME="Select u from Utente u where u.username= :user";
	//Uso di JPA
	private EntityManagerFactory emf = PersistenceUtil.getEmf();
		
		
	@Override
	public Utente findByUsername(String username) {
		Utente u=null;
		EntityManager em = null;
		EntityTransaction tx =null;
		try {
			em = emf.createEntityManager();
		tx = em.getTransaction();
		tx.begin();
		
		TypedQuery<Utente> query = em.createQuery(FIND_BY_USERNAME,Utente.class);
		query.setParameter("user", username);
		u = query.getSingleResult();
		
		tx.commit();
		} catch (PersistenceException e) {
			if(tx!=null && tx.isActive()) {
				tx.rollback();
			}
		}finally {
			if(em!=null) {
				em.close();
			}
			
		}
		
		
		
		return u;
//		Connection connection = null;
//		
//		ResultSet resulset = null;
//		PreparedStatement prepareStatement =null;
//		
//		try {
//			//cambiarlo a JPA
//			connection = DataSource.getInstance().getConnection();
//			prepareStatement = connection.prepareStatement(FIND_BY_USERNAME);
//			prepareStatement.setString(1,username);
//			resulset = prepareStatement.executeQuery();
//			while (resulset.next()) {
//				u= new Utente();
//				
//				u.setId(Integer.parseInt(resulset.getString(1)));
//				u.setNome(resulset.getString(2));
//				u.setPassword(resulset.getString(3));
//				
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
	}


		


	
}
