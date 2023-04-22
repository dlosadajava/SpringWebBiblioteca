package it.etlabora.SpringWebBiblioteca.controllo;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.etlabora.SpringWebBiblioteca.AppConfig;
import it.etlabora.SpringWebBiblioteca.modello.Autore;
import it.etlabora.SpringWebBiblioteca.modello.Genere;
import it.etlabora.SpringWebBiblioteca.modello.Libro;
import it.etlabora.SpringWebBiblioteca.modello.LibroAutore;
import it.etlabora.SpringWebBiblioteca.persisteza.AutoreDao;
import it.etlabora.SpringWebBiblioteca.persisteza.GenereDao;
import it.etlabora.SpringWebBiblioteca.persisteza.LibroAutoreDao;
import it.etlabora.SpringWebBiblioteca.persisteza.LibroDao;
import it.etlabora.SpringWebBiblioteca.persisteza.impl.AutoreDaoImpl;
import it.etlabora.SpringWebBiblioteca.persisteza.impl.GenereDaoImpl;
import it.etlabora.SpringWebBiblioteca.persisteza.impl.LibroAutoreDaoImpl;
import it.etlabora.SpringWebBiblioteca.persisteza.impl.LibroDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class InserisceLibro
 */
@WebServlet("/InserisceLibro")
public class InserisceLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AnnotationConfigApplicationContext ctx= new AnnotationConfigApplicationContext(AppConfig.class);
	
    //private GenereDao genereDao = new GenereDaoImpl();
	private GenereDao genereDao =ctx.getBean(GenereDao.class);//spring
    //private LibroDao libroDao= new LibroDaoImpl();
	private LibroDao libroDao=ctx.getBean(LibroDao.class);//spring
    //private AutoreDao autoreDao= new AutoreDaoImpl();
	private AutoreDao autoreDao=ctx.getBean(AutoreDao.class);
    
   // private LibroAutoreDao libroAutoreDao= new LibroAutoreDaoImpl();
	private LibroAutoreDao libroAutoreDao=ctx.getBean(LibroAutoreDao.class);//spring
	
//metodo para comprobar que en la sesion la variable username(utenteId) del form login existe
    
    private boolean checkSession(HttpSession session) {
		if (session.getAttribute("utenteId") == null) {
			return false;
		}
		return true;
	}

    
    
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//ver si el utente esta registrado en la sesion
		//crear metodo session
		if (!checkSession(req.getSession())) {
			resp.sendRedirect("login.jsp");
			return;
		}
		
		//logica del caso de uso
		List<Genere> listGenere = genereDao.FindAll();
		List<Autore> listAutore = autoreDao.findAll();
		System.out.println(listGenere.toString());
		req.setAttribute("listGenere", listGenere);
		req.setAttribute("listAutore", listAutore);
		req.getRequestDispatcher("inserisce-libro.jsp").forward(req, resp);
		}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*VALIDATION SI ES NECESARIO??*/
		
		
		String isbn = request.getParameter("isbn");
		String titolo = request.getParameter("titolo");
		String descrizione = request.getParameter("descrizione");
		
		String genereString = request.getParameter("genere");
		int idGenere=Integer.parseInt(genereString);
		
		String numeroPString=request.getParameter("numeroPagina");
		int numeroPagina = Integer.parseInt(numeroPString);
		
		String dateString=request.getParameter("dataP");
		
		Libro libro= new Libro();
		libro.setIsbn(isbn);
		libro.setTitolo(titolo);
		libro.setDescrizione(descrizione);
		
		
		Genere genere= new Genere();
		genere.setId(idGenere);		
		//genere.setNome(genereString);
		libro.setGenere(genere);
		
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-dd-MM");
		Date data=null;
		try {
			data = sdf.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		libro.setData_pubblicazione(data);
		libro.setNumero_pagine(numeroPagina);		
		
		
		
		
		
		
		System.out.println("libro en la servlet antes de annadirlo"+libro.toString());
		
		libroDao.insert(libro);
		
		
		//inserir en la relacion entre libro y autore
		//debo insertar primero el libro para que la bd le asigne un id y luego recupererlo
		
		List<Autore> listSelectAutore= new LinkedList<>();
		String[] listaSelectAutore=request.getParameterValues("autore");
		
		System.out.println("autori seleccionati: ");
		for (String idAutore :listaSelectAutore) {
			System.out.println("-"+idAutore);
			LibroAutore la= new LibroAutore();
			
			//la.setLibro(libroDao.findByIsbn(isbn));
			la.setLibro(libro);
			la.setAutore(autoreDao.FindById(Integer.parseInt( idAutore)));
			libroAutoreDao.insert(la);
			//libroAutoreDao.insert(libroDao.findByIsbn(isbn).getId(),Integer.parseInt( idAutore));
			System.out.println("En la tabla LibroAutore se agregaron:"+libro.getId()+","+Integer.parseInt( idAutore));
		}
		
		System.out.println("libro , lista de autores inseridos"+libro.getListAutore().toString());
		
		////////////////////////////////////////////////
		
		response.sendRedirect("libri");
		
	}

}
