package it.etlabora.SpringWebBiblioteca.persisteza.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import it.etlabora.SpringWebBiblioteca.modello.LibroAutore;
import it.etlabora.SpringWebBiblioteca.persisteza.LibroAutoreDao;
import it.etlabora.SpringWebBiblioteca.persisteza.PersistenceUtil;

@Repository // spring
public class LibroAutoreDaoImpl implements LibroAutoreDao {

	// private static final String INSERT = "insert into
	// libro_autore(libro_id,autore_id) values(?,?)";

	// Uso di JPA
	private EntityManagerFactory emf = PersistenceUtil.getEmf();

	@Override
	public void insert(LibroAutore la) {
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			em.persist(la);
			tx.commit();
		} catch (PersistenceException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {
			if (em != null)
				em.close();
		}

	}

	// @Override con DataSource
//	public void insert(int libro_id, int autore_id) {

//		Connection connection =null;
//		PreparedStatement prepareStatement =null;
//		try {
//			connection = DataSource.getInstance().getConnection();
//			prepareStatement = connection.prepareStatement(INSERT);
//			prepareStatement.setInt(1, libro_id);
//			prepareStatement.setInt(2, autore_id);
//			prepareStatement.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			DBUtil.close(prepareStatement);
//			DBUtil.close(connection);
//		}

	// }

}
