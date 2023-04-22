package it.etlabora.SpringWebBiblioteca.persisteza.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import it.etlabora.SpringWebBiblioteca.modello.Autore;
import it.etlabora.SpringWebBiblioteca.modello.Libro;
import it.etlabora.SpringWebBiblioteca.persisteza.AutoreDao;
import it.etlabora.SpringWebBiblioteca.persisteza.PersistenceUtil;


@Repository // spring
public class AutoreDaoImpl implements AutoreDao {

	// private static final String FIND_ALL = "SELECT * FROM autore";
	private static final String FIND_ALL = "SELECT a FROM Autore a";
	// private static final String FIND_BY_Id = "SELECT * FROM autore where id=?";

	//private static final String INSERT = "INSERT INTO autore(nome,cognome,data_nascita) VALUES(?,?,?);";
	// private static final String FIND_NOME_COGNOME_LIKE = "Select * from autore
	// where nome like? and cognome like ?; ";
	// Uso JPA
	private static final String FIND_NOME_COGNOME_LIKE = "Select a from Autore a where a.nome like:nome and a.cognome like:cognome ";

	private static final String DELETE = "DELETE FROM autore where id=?";
	
	//conJPA
		private static final String FIND_BY_LIBRO = "select l from Libro l inner join LibroAutore  la on l.id=la.libro.id inner join Autore a on a.id=la.autore.id where a.id=:idAutore";


	// Uso di JPA
	private EntityManagerFactory emf = PersistenceUtil.getEmf();

	@Override
	public List<Autore> findAll() {
		List<Autore> autori = new ArrayList<Autore>();
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Autore> query = em.createQuery(FIND_ALL, Autore.class);
			autori = query.getResultList();

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

		return autori;

		// Con la clase DataSource(usa libreria jdbc)
//		Connection connection = null;
//		PreparedStatement statement = null;
//		ResultSet resultSet = null;
		// try {
		// connection = DataSource.getInstance().getConnection();

		// statement = connection.prepareStatement(FIND_ALL);
		// resultSet = statement.executeQuery();
//			while (resultSet.next()) {
//				Autore autore = new Autore();
//				autore.setId(resultSet.getInt(1));
//				autore.setNome(resultSet.getString(2));
//				autore.setCognome(resultSet.getString(3));
//				autore.setData_nascita((new Date(resultSet.getDate(4).getTime())));
//				autori.add(autore);
//			}
//		} catch (SQLException e) {
//			System.err.println(e.getMessage());
//		} finally {
//			DBUtil.close(resultSet);
//			DBUtil.close(statement);
//			DBUtil.close(connection);
//		}

	}

	@Override
	public List<Autore> findAllNomeCognomeLIKE(String nome, String cognome) {
		List<Autore> autori = new ArrayList<Autore>();
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Autore> query = em.createQuery(FIND_NOME_COGNOME_LIKE, Autore.class);
			System.out.println("Dopo la query");
			query.setParameter("nome", nome);
			query.setParameter("cognome", cognome);
			autori = query.getResultList();

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
		return autori;
//		Connection connection = null;
//		PreparedStatement statement = null;
//		ResultSet resultSet = null;
//		try {
//			connection = DataSource.getInstance().getConnection();
//			statement = connection.prepareStatement(FIND_NOME_COGNOME_LIKE);
//			statement.setString(1, "%" + nome + "%");
//			statement.setString(2, "%" + cognome + "%");
//			resultSet = statement.executeQuery();
//			while (resultSet.next()) {
//				Autore autore = new Autore();
//				autore.setId(resultSet.getInt(1));
//				autore.setNome(resultSet.getString(2));
//				autore.setCognome(resultSet.getString(3));
//				autore.setData_nascita((new Date(resultSet.getDate(4).getTime())));
//				autori.add(autore);
//			}
//		} catch (SQLException e) {
//			System.err.println(e.getMessage());
//		} finally {
//			DBUtil.close(resultSet);
//			DBUtil.close(statement);
//			DBUtil.close(connection);
//		}

	}

	@Override
	public void insert(Autore autore) {
		EntityTransaction tx = null;
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			em.persist(autore);

			tx.commit();
		} catch (PersistenceException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {
			if (em != null)
				em.close();

		}
		// TODO Auto-generated method stub
//		Connection connection = null;
//		PreparedStatement statement = null;
//		ResultSet resultSet = null;
//		try {
//			connection = DataSource.getInstance().getConnection();
//			statement = connection.prepareStatement(INSERT);
//			statement.setString(1, autore.getNome());
//			statement.setString(2, autore.getCognome());
//
//			java.sql.Date data = new java.sql.Date(autore.getData_nascita().getTime());
//
//			statement.setDate(3, data);
//			System.out.println(autore);
//
//			statement.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			DBUtil.close(resultSet);
//			DBUtil.close(statement);
//			DBUtil.close(connection);
//		}

	}

	@Override
	public void update(Autore autore) {
		EntityTransaction tx = null;
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			em.merge(autore);
			System.out.println("Estoy dentro metodo update de autore");
			System.err.println("Datos del autor :"+autore);
			
			tx.commit();
		} catch (PersistenceException e) {
			if (tx!= null && tx.isActive()) {
				tx.rollback();
			}
		} finally {
			if (em!= null)
				em.close();

		}

	}

	@Override
	public void delete(int id) {
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Autore autore = em.find(Autore.class, id);
			em.remove(autore);
			tx.commit();
		} catch (PersistenceException e) {
			System.err.println(e.getMessage());
			if (tx != null && tx.isActive())
				tx.rollback();
		} finally {
			if (em != null)
				em.close();
		}
//		Connection connection = null;
//		PreparedStatement prepareStatement = null;
//		try {
//			connection = DataSource.getInstance().getConnection();
//			prepareStatement = connection.prepareStatement(DELETE);
//			prepareStatement.setInt(1, id);
//			prepareStatement.executeUpdate();
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			DBUtil.close(prepareStatement);
//			DBUtil.close(connection);
//		}

	}

	

	@Override
	public Autore FindById(int id) {

		Autore autore = new Autore();
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			autore = em.find(Autore.class, id);

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

		return autore;
//		Connection connection = null;
//		PreparedStatement statement = null;
//		ResultSet resultSet = null;
//		
//		try {
//			connection = DataSource.getInstance().getConnection();
//			statement = connection.prepareStatement(FIND_BY_Id);
//			statement.setInt(1, id);
//			resultSet = statement.executeQuery();
//
//			if (resultSet.next()) {
//
//				autore.setId(resultSet.getInt(1));
//				autore.setNome(resultSet.getString(2));
//				autore.setCognome(resultSet.getString(3));
//				autore.setData_nascita((new Date(resultSet.getDate(4).getTime())));
//
//			}
//		} catch (SQLException e) {
//			System.err.println(e.getMessage());
//		} finally {
//			DBUtil.close(resultSet);
//			DBUtil.close(statement);
//			DBUtil.close(connection);
//		}

	}

	@Override
	public List<Libro> listLibribyIdAutore(int idAutore) {
		List<Libro> libro = new ArrayList<Libro>();
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Libro> query = em.createQuery(FIND_BY_LIBRO, Libro.class);
			//System.out.println("Dopo la query");
			query.setParameter("idAutore",idAutore);
			libro = query.getResultList();

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
		return libro;
	}

}
