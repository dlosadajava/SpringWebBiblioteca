package it.etlabora.SpringWebBiblioteca.persisteza.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.google.protobuf.TextFormat.ParseException;

import it.etlabora.SpringWebBiblioteca.modello.Autore;
import it.etlabora.SpringWebBiblioteca.modello.Libro;
import it.etlabora.SpringWebBiblioteca.persisteza.LibroDao;
import it.etlabora.SpringWebBiblioteca.persisteza.PersistenceUtil;


@Repository // spring
public class LibroDaoImpl implements LibroDao {

	// private static final String FIND_ALL = "SELECT * FROM libro INNER JOIN genere
	// ON libro.genere_id = genere.id";
	private static final String FIND_ALL = "SELECT l FROM Libro l INNER JOIN Genere g ON l.genere.id = g.id";
//private static final String FIND_BY_TITOLO = "SELECT * FROM libro INNER JOIN genere ON libro.genere_id = genere.id WHERE titolo LIKE ?";
	//JPA
	private static final String FIND_BY_TITOLO = "SELECT l FROM Libro l INNER JOIN Genere g ON l.genere.id = g.id WHERE l.titolo LIKE :titolo";
	//private static final String FIND_BY_ISBN = "SELECT * FROM libro INNER JOIN genere ON libro.genere_id = genere.id WHERE isbn=?";
	//JPA
	private static final String FIND_BY_ISBN = "SELECT l FROM Libro l INNER JOIN Genere g ON l.genere.id = g.id WHERE isbn=:isbn";
	
	private static final String FIND_BY_ID = "SELECT * FROM libro INNER JOIN genere ON libro.genere_id = genere.id WHERE libro.id=?";
	//private static final String FIND_BY_GENERE = "select * from libro as l inner join genere as g on l.genere_id=g.id where g.nome= ?";
	//JPA
	private static final String FIND_BY_GENERE = "select l from Libro l inner join Genere g on l.genere.id=g.id where g.nome=:nome";
	
	
	
	//private static final String FIND_BY_AUTORE = "select l.id,l.isbn,l.titolo,l.descrizione,g.nome as genere,l.numero_pagine,l.data_pubblicazione from libro as l inner join libro_autore  as la on l.id=la.autore_id inner join autore as a on a.id=la.autore_id inner join genere as g on l.genere_id=g.id where a.cognome= ?";
	//conJPA
	private static final String FIND_BY_AUTORE = "select l from Libro l inner join LibroAutore  la on l.id=la.libro.id inner join Autore a on a.id=la.autore.id where a.nome=:nome";

	
	//select * from libro as l inner join libro_autore  as la on l.id=la.autore_id inner join autore as a on a.id=la.autore_id where a.cognome="cognome1"
	private static final String DELETE = "delete from libro where id=?";
	// private static final String FIND_AUTORI = "Select * from autore as a inner
	// join libro_autore as la on a.id=la.autore_id where la.libro_id=?";
	// con JPA
	private static final String FIND_AUTORI = "Select a from Autore a inner join LibroAutore la on  a.id=la.autore.id where la.libro.id=:idLibro";

	private static final String UPDATE = "update libro set isbn='?',titolo='?', descrizione='?',genere_id=?,numero_pagine=?,data_pubblicazione=? where id=?";
	// private PreparedStatement prepareStatement;
	// Uso di JPA
	private EntityManagerFactory emf = PersistenceUtil.getEmf();

//select * from libro as l inner join genere as g on l.genere_id=g.id where g.nome="genere1"
//select l.isbn,l.titolo,g.nome as genere from libro as l inner join libro_autore  as la on l.id=la.autore_id inner join autore as a on a.id=la.autore_id inner join genere as g on l.genere_id=g.id where a.cognome="cognome1"
//select * from libro as l inner join genere as g on l.  genere_id=g.id;
	@Override
	public List<Libro> FindAll() {
		List<Libro> listLibro = new LinkedList<>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Libro> query = em.createQuery(FIND_ALL, Libro.class);
		
			listLibro = query.getResultList();

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
		System.out.println("Dentro metodo findbyall:listLibro=  "+listLibro);
		return listLibro;
//		Connection connection = null;
//		
//		PreparedStatement prepareStatement = null;
//		ResultSet resulset = null;
//		try {
//			connection = DataSource.getInstance().getConnection();
//			prepareStatement = connection.prepareStatement(FIND_ALL);
//			resulset = prepareStatement.executeQuery();
//			while (resulset.next()) {
//				Libro libro = new Libro();
//				libro.setId(resulset.getInt(1));
//				libro.setIsbn(resulset.getString(2));
//				libro.setTitolo(resulset.getString(3));
//				libro.setDescrizione(resulset.getString(4));
//
//				Genere genere = new Genere();
//				genere.setId(resulset.getInt(8));
//				genere.setNome(resulset.getString(9));
//				libro.setGenere(genere);
//				// java.sql.Date date = resulset.getDate(7);
//				libro.setNumero_pagine(6);
//				Date data = new Date(resulset.getDate(7).getTime());
//				libro.setData_pubblicazione(data);
//
//				listLibro.add(libro);
//
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			DBUtil.close(resulset);
//			DBUtil.close(prepareStatement);
//			DBUtil.close(connection);
//		}

	}

	@Override
	public Libro findByIsbn(String isbn) {
		Libro libro = null;
		EntityManager em =null;
		EntityTransaction tx =null;
		
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			TypedQuery<Libro> query = em.createQuery(FIND_BY_ISBN,Libro.class);
			query.setParameter("isbn", isbn);
			
			libro=query.getSingleResult();
			
			tx.commit();
		} catch (Exception e) {
			if(tx!=null && tx.isActive())
				tx.rollback();
		}finally {
			if(em!=null)
			em.close();
		}
		
//		Connection connection = null;
//		
//		PreparedStatement prepareStatement = null;
//		ResultSet resulset = null;
//
//		try {
//			connection = DataSource.getInstance().getConnection();
//			prepareStatement = connection.prepareStatement(FIND_BY_ISBN);
//			prepareStatement.setString(1, isbn);
//			resulset = prepareStatement.executeQuery();
//			if (resulset.next()) {
//				libro.setIsbn(isbn);
//				libro.setId(resulset.getInt(1));
//				libro.setTitolo(resulset.getString(3));
//				libro.setDescrizione(resulset.getString(4));
//				Genere genere = new Genere();
//				genere.setNome(resulset.getString(9));
//				libro.setGenere(genere);
//				libro.setNumero_pagine(6);
//				Date data = new Date(resulset.getDate(7).getTime());
//				libro.setData_pubblicazione(data);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			DBUtil.close(resulset);
//			DBUtil.close(prepareStatement);
//			DBUtil.close(connection);
//		}
		System.out.println("Dentro metodo findbyISBN:listLibro=  "+libro);
		return libro;
	}

	@Override
	public List<Libro> findByAutore(String autore) {
		List<Libro> listLibro = new LinkedList<>();
		EntityManager em =null;
		EntityTransaction tx =null;
		
		try {
				em = emf.createEntityManager();
		tx = em.getTransaction();
		tx.begin();
		
		TypedQuery<Libro> query = em.createQuery(FIND_BY_AUTORE, Libro.class);
		query.setParameter("nome", autore);
		listLibro = query.getResultList();
		
		tx.commit();
		} catch (PersistenceException e) {
			if(tx!=null && tx.isActive()) {
				tx.rollback();
			}
		}finally {
			if(em!=null)
			em.close();
		}
	
		System.out.println("libro dentro metodo findByAutore"+listLibro);
		
		return listLibro;
//		Connection connection = null;
//		
//		PreparedStatement prepareStatement = null;
//		ResultSet resulset = null;
//		try {
//			connection = DataSource.getInstance().getConnection();
//			prepareStatement = connection.prepareStatement(FIND_BY_AUTORE);
//			prepareStatement.setString(1, autore);
//			resulset = prepareStatement.executeQuery();
//
//			while (resulset.next()) {
//				Libro libro = new Libro();
//				libro.setId(resulset.getInt(1));
//				libro.setIsbn(resulset.getString(2));
//				libro.setTitolo(resulset.getString(3));
//				libro.setDescrizione(resulset.getString(4));
//
//				Genere genere = new Genere();
//				genere.setNome(resulset.getString(5));
//				libro.setGenere(genere);
//
//				libro.setNumero_pagine(6);
//				Date data = new Date(resulset.getDate(7).getTime());
//				libro.setData_pubblicazione(data);
//
//				listLibro.add(libro);
//
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			DBUtil.close(resulset);
//			DBUtil.close(prepareStatement);
//			DBUtil.close(connection);
//		}

	}

	@Override
	public void insert(Libro libro) {
		EntityTransaction tx = null;
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			em.persist(libro);

			tx.commit();
		} catch (PersistenceException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {
			if (em != null)
				em.close();

		}
	

//		String sql = "insert into libro(isbn,titolo,descrizione,genere_id,numero_pagine,data_pubblicazione) values (?,?,?,?,?,?);";
//
//		Connection connection = null;
//		PreparedStatement prepareStatement = null;
//
//		try {
//			connection = DataSource.getInstance().getConnection();
//			prepareStatement = connection.prepareStatement(sql);
//			prepareStatement.setString(1, libro.getIsbn());
//			prepareStatement.setString(2, libro.getTitolo());
//			prepareStatement.setString(3, libro.getDescrizione());
//			prepareStatement.setInt(4, libro.getGenere().getId());
//			prepareStatement.setInt(5, libro.getNumero_pagine());
//			prepareStatement.setDate(6, new java.sql.Date(libro.getData_pubblicazione().getTime()));
//			prepareStatement.executeUpdate();
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//
//			DBUtil.close(prepareStatement);
//			DBUtil.close(connection);
//		}

	}

	@Override
	public List<Libro> findByGenere(String genere) {
		List<Libro> listLibro = new LinkedList<>();
		EntityManager em = null;
		EntityTransaction tx =null;
		
		
		try {
			em = emf.createEntityManager();
		 tx = em.getTransaction();
		tx.begin();
		
		TypedQuery<Libro> query = em.createQuery(FIND_BY_GENERE, Libro.class);
		query.setParameter("nome", genere);
		listLibro = query.getResultList();
		tx.commit();
		} catch (Exception e) {
			if(tx!=null && tx.isActive())
				tx.rollback();
		}finally {
			if(em!=null)
				em.close();
		}
		System.out.println("Dentro metodo findByGenere:listLibro=  "+listLibro);
		return listLibro;
//		Connection connection = null;
//		
//		PreparedStatement prepareStatement = null;
//		ResultSet resulset = null;
//		try {
//			connection = DataSource.getInstance().getConnection();
//			prepareStatement = connection.prepareStatement(FIND_BY_GENERE);
//			prepareStatement.setString(1, genere);
//			resulset = prepareStatement.executeQuery();
//			while (resulset.next()) {
//				Libro libro = new Libro();
//				libro.setId(resulset.getInt(1));
//				libro.setIsbn(resulset.getString(2));
//				libro.setTitolo(resulset.getString(3));
//				libro.setDescrizione(resulset.getString(4));
//				Genere genereO = new Genere();
//				genereO.setId(resulset.getInt(5));
//				genereO.setNome(resulset.getString(9));
//				libro.setGenere(genereO);
//
//				libro.setNumero_pagine(6);
//				Date data = new Date(resulset.getDate(7).getTime());
//				libro.setData_pubblicazione(data);
//
//				listLibro.add(libro);
//
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			DBUtil.close(resulset);
//			DBUtil.close(prepareStatement);
//			DBUtil.close(connection);
//		}

	}

	@Override
	public List<Libro> findByTitoloLike(String titolo) {
		List<Libro> libri = new ArrayList<Libro>();
		EntityManager em =null;
		EntityTransaction tx =null;
		try {
		em = emf.createEntityManager();
		tx = em.getTransaction();
		tx.begin();
		
		TypedQuery<Libro> query=em.createQuery(FIND_BY_TITOLO, Libro.class);
		query.setParameter("titolo", titolo);
		libri = query.getResultList();
		
		tx.commit();
		} catch (PersistenceException e) {
			if(tx!=null && tx.isActive()) {
				tx.rollback();
			}
		}finally {
			if(em!=null)
				em.close();
		}
	
		
		
		System.out.println("Dentro metodo findByTitolo:listLibro=  "+libri);
		return libri;

//		Connection connection = null;
//		PreparedStatement statement = null;
//		ResultSet resultSet = null;
//		try {
//			connection = DataSource.getInstance().getConnection();
//			statement = connection.prepareStatement(FIND_BY_TITOLO);
//			statement.setString(1, "%" + titolo + "%");
//			resultSet = statement.executeQuery();
//			while (resultSet.next()) {
//				Libro libro = new Libro();
//				libro.setId(resultSet.getInt(1));
//				libro.setIsbn(resultSet.getString(2));
//				libro.setTitolo(resultSet.getString(3));
//				libro.setDescrizione(resultSet.getString(4));
//				libro.setNumero_pagine(resultSet.getInt(6));
//				libro.setData_pubblicazione(new Date(resultSet.getDate(7).getTime()));
//				Genere genere = new Genere();
//				genere.setId(resultSet.getInt(8));
//				genere.setNome(resultSet.getString(9));
//				libro.setGenere(genere);
//				libri.add(libro);
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
	public void deleteLibro(int id) {
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Libro libro = em.find(Libro.class, id);
			System.err.println("dentro metodo delete libro con id= "+id);
			System.err.println("LIBRO= "+libro);
			em.remove(libro);
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
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			DBUtil.close(prepareStatement);
//			DBUtil.close(connection);
//		}

	}

	@Override
	public void update(Libro libro) {
		EntityTransaction tx = null;
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			em.merge(libro);
			System.out.println("Estoy dentro metodo update de libro");
			System.err.println("Datos del libro :"+libro);
			
			tx.commit();
		} catch (PersistenceException e) {
			if (tx!= null && tx.isActive()) {
				tx.rollback();
			}
		} finally {
			if (em!= null)
				em.close();

		}
//		Connection connection = null;
//		PreparedStatement prepareStatement = null;
//		try {
//			connection = DataSource.getInstance().getConnection();
//			prepareStatement = connection.prepareStatement(UPDATE);
//			//private static final String UPDATE = "update libro set isbn='?',titolo='?', 
//			//descrizione='?',genere_id=?,numero_pagine=?,data_pubblicazione=? where id=?";
//			prepareStatement.setString(1, libro.getIsbn());
//			prepareStatement.setString(2, libro.getTitolo());
//			prepareStatement.setString(3, libro.getDescrizione());
//			prepareStatement.setInt(4, libro.getGenere().getId());
//			prepareStatement.setInt(5, libro.getNumero_pagine());
//			prepareStatement.setDate(6,new java.sql.Date(libro.getData_pubblicazione().getTime()));
//			prepareStatement.setInt(7, libro.getId());
//			
//			prepareStatement.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			DBUtil.close(prepareStatement);
//			DBUtil.close(connection);
//		}

	}

	@Override
	public List<Autore> listAutoribyIdLIbro(int idLibro) {
		List<Autore> listAutori = new LinkedList<>();
		EntityManager em =null;
		EntityTransaction tx =null;
		
		try
		{
		em = emf.createEntityManager();
		tx = em.getTransaction();
		tx.begin();
		TypedQuery<Autore> query= em.createQuery(FIND_AUTORI,Autore.class);
		query.setParameter("idLibro", idLibro);
		listAutori =query.getResultList();
		
		tx.commit();	
		}catch (PersistenceException e) {
			if(tx!=null && tx.isActive()) {
				tx.rollback();
			}
		}finally {
			if(em!=null) {
			em.close();}
		}
		
		
		System.out.println("Dentro metodo listAutoribyIdLIbro:listAutori=  "+listAutori);
		
		return listAutori;
//		Connection connection = null;
//		prepareStatement = null;
//		ResultSet resultSet =null;
//		try {
//			connection = DataSource.getInstance().getConnection();
//			prepareStatement = connection.prepareStatement(FIND_AUTORI);
//			prepareStatement.setInt(1, idLibro);
//			resultSet = prepareStatement.executeQuery();
//			while (resultSet.next()) {
//				Autore a= new Autore();
//				a.setId(resultSet.getInt(1));
//				a.setNome(resultSet.getString(2));
//				a.setCognome(resultSet.getString(3));
//				
//				a.setData_nascita(new Date(resultSet.getDate(4).getTime()));
//				listAutori.add(a);
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			DBUtil.close(resultSet);
//			DBUtil.close(prepareStatement);
//			DBUtil.close(connection);
//		}
//		

	}

	@Override
	public Libro findById(int id) {
		Libro libro = new Libro();
		EntityManager em =null;
		EntityTransaction tx = null;
		try {
			 em = emf.createEntityManager();
			  tx = em.getTransaction();
			  tx.begin();
			  
			  libro=em.find(Libro.class, id);
			  tx.commit();
			 
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			
		}
		
		return libro;
		// Connection connection = null;
//		
//		PreparedStatement prepareStatement = null;
//		ResultSet resulset = null;
//
//		try {
//			connection = DataSource.getInstance().getConnection();
//			prepareStatement = connection.prepareStatement(FIND_BY_ID);
//			prepareStatement.setInt(1, id);
//			resulset = prepareStatement.executeQuery();
//			if (resulset.next()) {
//				libro.setId(resulset.getInt(1));
//				libro.setIsbn(resulset.getString(2));				
//				libro.setTitolo(resulset.getString(3));
//				libro.setDescrizione(resulset.getString(4));
//				Genere genere = new Genere();
//				genere.setNome(resulset.getString(9));
//				libro.setGenere(genere);
//				libro.setNumero_pagine(6);
//				Date data = new Date(resulset.getDate(7).getTime());
//				libro.setData_pubblicazione(data);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			DBUtil.close(resulset);
//			DBUtil.close(prepareStatement);
//			DBUtil.close(connection);
//		}

	}

}
