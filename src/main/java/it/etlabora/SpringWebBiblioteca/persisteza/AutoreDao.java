package it.etlabora.SpringWebBiblioteca.persisteza;

import java.util.List;

import it.etlabora.SpringWebBiblioteca.modello.Autore;
import it.etlabora.SpringWebBiblioteca.modello.Libro;

public interface AutoreDao {
	// da implementare

	void update(Autore autore);

	void delete(int id);

//implementati
	void insert(Autore autore);

	List<Autore> findAll();

	List<Autore> findAllNomeCognomeLIKE(String nome, String cognome);

	Autore FindById(int id);

	List<Libro> listLibribyIdAutore(int idAutore);

}
