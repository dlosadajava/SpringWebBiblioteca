package it.etlabora.SpringWebBiblioteca.modello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity//JPA
@Table(name = "libro_autore")//JPA
public class LibroAutore {
/*id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
libro_id INT NOT NULL REFERENCES libro(id),
autore_id INT NOT NULL REFERENCES autore(id),
UNIQUE(libro_id,autore_id)*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "autore_id")
	private Autore autore;
	
	@ManyToOne
	@JoinColumn(name = "libro_id")
	private Libro libro;
	
	public LibroAutore() {
		// TODO Auto-generated constructor stub
	}

	public LibroAutore(int id, Autore autore, Libro libro) {
		super();
		this.id = id;
		this.autore = autore;
		this.libro = libro;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Autore getAutore() {
		return autore;
	}

	public void setAutore(Autore autore) {
		this.autore = autore;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	@Override
	public String toString() {
		return "LibroAutore [id=" + id + ", autore=" + autore + ", libro=" + libro + "]";
	}
	
}
