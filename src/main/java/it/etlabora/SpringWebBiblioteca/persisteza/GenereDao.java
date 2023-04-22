package it.etlabora.SpringWebBiblioteca.persisteza;

import java.util.List;

import it.etlabora.SpringWebBiblioteca.modello.Genere;


public interface GenereDao {
	//da implementare
	void insert(Genere genere);
	void update(Genere genere);
	void delete(int id);
	
	
	
	//implementati
	List<Genere> FindAll();
	Genere FindById(int id);
}
