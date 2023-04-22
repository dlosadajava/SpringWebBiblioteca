package it.etlabora.SpringWebBiblioteca.modello;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity//JPA
@Table(name = "libro")//JPA
public class Libro {
/*id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  isbn CHAR(13) UNIQUE NOT NULL,
  titolo VARCHAR(45) NOT NULL,
  descrizione VARCHAR(255) NOT NULL,
  genere_id INT NOT NULL REFERENCES genere(id),
numero_pagine INT NOT NULL CHECK(numero_pagine>0),
  data_pubblicazione DATE NOT NULL*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String isbn;
	private String titolo;
	private String descrizione;
	
	@ManyToOne
	@JoinColumn(name = "genere_id")
	private Genere genere;
	
	private int numero_pagine;
	
	@Temporal(TemporalType.DATE)
	private Date data_pubblicazione;
	
	//@ManyToMany
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	//Select * from libro inner join libro_autore on libro.id=libro_autore.autore_id;
	@JoinTable(name = "libro_autore",
	joinColumns = @JoinColumn(name="libro_id"),
	inverseJoinColumns =@JoinColumn(name="autore_id") )
	List<Autore> listAutore=new LinkedList<>();
	
	public Libro() {
		// TODO Auto-generated constructor stub
	}

	public Libro(int id, String isbn, String titolo, String descrizione, Genere genere, int numero_pagine,
			Date data_pubblicazione) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.genere = genere;
		this.numero_pagine = numero_pagine;
		this.data_pubblicazione = data_pubblicazione;
	}

	public List<Autore> getListAutore() {
		return listAutore;
	}
	public void setListAutore(List<Autore> listAutore) {
		this.listAutore = listAutore;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Genere getGenere() {
		return genere;
	}

	public void setGenere(Genere genere) {
		this.genere = genere;
	}

	public int getNumero_pagine() {
		return numero_pagine;
	}

	public void setNumero_pagine(int numero_pagine) {
		this.numero_pagine = numero_pagine;
	}

	public Date getData_pubblicazione() {
		return data_pubblicazione;
	}

	public void setData_pubblicazione(Date data_pubblicazione) {
		this.data_pubblicazione = data_pubblicazione;
	}

	@Override
	public String toString() {
		return "libro [id=" + id + ", isbn=" + isbn + ", titolo=" + titolo + ", descrizione=" + descrizione
				+ ", genere=" + genere + ", numero_pagine=" + numero_pagine + ", data_pubblicazione="
				+ data_pubblicazione + "]";
	}
	
}
