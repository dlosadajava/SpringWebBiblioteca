package it.etlabora.SpringWebBiblioteca.persisteza;

import it.etlabora.SpringWebBiblioteca.modello.Utente;

public interface UtenteDao {
	Utente findByUsername(String username);
	
	
}
