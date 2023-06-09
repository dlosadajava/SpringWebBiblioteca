package it.etlabora.SpringWebBiblioteca.modello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "genere")
public class Genere {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;
 private String nome;
 
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}

@Override
public String toString() {
	return "Genere [id=" + id + ", nome=" + nome +"]";
}

 
}
