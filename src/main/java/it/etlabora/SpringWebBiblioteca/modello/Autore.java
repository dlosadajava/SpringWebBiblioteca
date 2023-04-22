package it.etlabora.SpringWebBiblioteca.modello;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity//jpa
@Table(name = "autore")//jpa

public class Autore {
/*
 * id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  nome VARCHAR(45) NOT NULL,
  cognome VARCHAR(45) NOT NULL,
  data_nascita DATE NOT NULL
 * */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String cognome;
	
	@Temporal(TemporalType.DATE)
	private Date data_nascita;
	
	//@ManyToMany
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	//@ManyToMany(mappedBy = "autore", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "libro_autore",
	joinColumns = @JoinColumn(name="autore_id"),
	inverseJoinColumns =@JoinColumn(name="libro_id") )
	List<Libro> listLibro=new LinkedList<>();
	
//	public Autore() {
//		// TODO Auto-generated constructor stub
//	}
//
//	public Autore(int id, String nome, Date data_nascita) {
//		super();
//		this.id = id;
//		this.nome = nome;
//		this.data_nascita = data_nascita;
//	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public List<Libro> getListLibro() {
		return listLibro;
	}

	public void setListLibro(List<Libro> listLibro) {
		this.listLibro = listLibro;
	}

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

	public Date getData_nascita() {
		return data_nascita;
	}

	public void setData_nascita(Date data_nascita) {
		this.data_nascita = data_nascita;
	}

	@Override
	public String toString() {
		return "Autore [id=" + id + ", nome=" + nome + "]";
	}
	
}
