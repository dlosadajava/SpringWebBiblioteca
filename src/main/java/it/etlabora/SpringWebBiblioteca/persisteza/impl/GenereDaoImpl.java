package it.etlabora.SpringWebBiblioteca.persisteza.impl;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import it.etlabora.SpringWebBiblioteca.modello.Autore;
import it.etlabora.SpringWebBiblioteca.modello.Genere;
import it.etlabora.SpringWebBiblioteca.persisteza.GenereDao;
import it.etlabora.SpringWebBiblioteca.persisteza.PersistenceUtil;

@Repository // spring
public class GenereDaoImpl implements GenereDao {
//private static final String FIND_ALL="select * from genere";
	private static final String FIND_ALL = "select g from Genere g";
//private static final String FIND_By_Id="select * from genere where id=?";
	private static final String FIND_By_Id = "select g from Genere g where id=:id";
	// Uso di JPA
	private EntityManagerFactory emf = PersistenceUtil.getEmf();

	@Override
	public List<Genere> FindAll() {
		List<Genere> listGenere = new LinkedList<>();
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Genere> query = em.createQuery(FIND_ALL, Genere.class);
			listGenere = query.getResultList();

			tx.commit();

		} catch (PersistenceException e) {
			System.err.println(e.getMessage());
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}

		}

		return listGenere;
//		Connection connection = null;
//		PreparedStatement prepareStatement =null;
//		ResultSet resulset =null;
//		
//		try {
//			connection = DataSource.getInstance().getConnection();
//			prepareStatement = connection.prepareStatement(FIND_ALL);
//			resulset = prepareStatement.executeQuery();
//			while (resulset.next()) {
//				Genere genere= new Genere();
//				genere.setId(resulset.getInt(1));
//				genere.setNome(resulset.getString(2));
//				listGenere.add(genere);
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			DBUtil.close(resulset);
//			DBUtil.close(prepareStatement);
//			DBUtil.close(connection);
//			
//		}

	}

	@Override
	public void insert(Genere genere) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Genere genere) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Genere FindById(int id) {
		Genere genere = new Genere();
		EntityManager em = null;
		EntityTransaction tx = null;
	
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			genere = em.find(Genere.class, id);
			tx.commit();
		} catch (PersistenceException e) {
			if (tx != null && tx.isActive())
				tx.rollback();
		} finally {
			if(em!=null) {
				em.close();
			}
		}

//		Connection connection = null;
//		PreparedStatement prepareStatement =null;
//		ResultSet resulset =null;
//		
//		try {
//			connection = DataSource.getInstance().getConnection();
//			prepareStatement = connection.prepareStatement(FIND_By_Id);
//			prepareStatement.setInt(1, id);
//			resulset = prepareStatement.executeQuery();
//			
//			if (resulset.next()) {
//				
//				genere.setId(resulset.getInt(1));
//				genere.setNome(resulset.getString(2));
//				
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			DBUtil.close(resulset);
//			DBUtil.close(prepareStatement);
//			DBUtil.close(connection);
//			
//		}

		return genere;
	}

}
