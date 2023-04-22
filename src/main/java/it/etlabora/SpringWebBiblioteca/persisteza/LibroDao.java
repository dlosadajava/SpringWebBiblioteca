package it.etlabora.SpringWebBiblioteca.persisteza;

import java.util.List;

import it.etlabora.SpringWebBiblioteca.modello.Autore;
import it.etlabora.SpringWebBiblioteca.modello.Libro;

public interface LibroDao {
	//da implementare
	void update(Libro libro);
	
	
//implementati
	void insert(Libro libro);

	void deleteLibro(int id);

	List<Libro> FindAll();

	Libro findByIsbn(String isbn);
	Libro findById(int id);

	List<Libro> findByTitoloLike(String titolo);

	List<Libro> findByAutore(String autore);

	List<Libro> findByGenere(String genere);
	
	List<Autore> listAutoribyIdLIbro(int idLibro);

}
