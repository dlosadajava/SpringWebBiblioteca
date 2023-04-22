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
import it.etlabora.SpringWebBiblioteca.persisteza.AutoreDao;
import it.etlabora.SpringWebBiblioteca.persisteza.GenereDao;
import it.etlabora.SpringWebBiblioteca.persisteza.LibroDao;
import it.etlabora.SpringWebBiblioteca.persisteza.impl.AutoreDaoImpl;
import it.etlabora.SpringWebBiblioteca.persisteza.impl.GenereDaoImpl;
import it.etlabora.SpringWebBiblioteca.persisteza.impl.LibroDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewAndEditSchedaLibro
 */
@WebServlet("/ViewAndEditSchedaLibro")
public class ViewAndEditSchedaLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AnnotationConfigApplicationContext ctx= new AnnotationConfigApplicationContext(AppConfig.class);
	LibroDao libroDao =ctx.getBean(LibroDao.class);//spring
	GenereDao genereDao =ctx.getBean(GenereDao.class);//spring
	AutoreDao autoreDao=ctx.getBean(AutoreDao.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idLibro = Integer.parseInt(req.getParameter("idLibro"));
		System.out.println("idlibro seleccionado="+idLibro);
		Libro libro = libroDao.findById(idLibro);
		System.out.println("libro pasado por attributo:"+libro);
		List<Genere> listGenere = genereDao.FindAll();
		List<Autore> listAutore = autoreDao.findAll();//todos los autores que existen
		List<Autore> listAutoreDelLibro = libro.getListAutore();//solo los del libro
		System.err.println("Lista de autores del libro: "+listAutoreDelLibro);
		System.out.println("Lista de autore: "+listAutore);
		
		req.setAttribute("libro", libro);
		
	//START eliminar de la lista de autores los autores de este libro
		for (int i = 0; i < listAutore.size(); i++) {
			for (int j = 0; j < listAutoreDelLibro.size(); j++) {
				if(listAutore.get(i).getId()==listAutoreDelLibro.get(j).getId()) {
					listAutore.remove(i);
					
				}
			}
		}
			
		
		
	//END eliminar de la lista de autores los autores de este libro
		
		
		
		req.setAttribute("listGenere", listGenere);
		req.setAttribute("listAutore", listAutore);
		req.setAttribute("listAutoreDelLibro", listAutoreDelLibro);
		
		
		
		
		req.getRequestDispatcher("ViewAndEditSchedaLibro.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*VALIDATION MANCA*/
		
		
		
		String isbn = request.getParameter("isbn");
		String titolo = request.getParameter("titolo");
		String descrizione = request.getParameter("descrizione");

		int idGenero = Integer.parseInt(request.getParameter("genere"));
		Genere genere=genereDao.FindById(idGenero);
		
		List<Autore> listAutore= new LinkedList<>();
		String[] arrayAutore=request.getParameterValues("autore");
		
		//validacion , nota: tambien hecha en javascript dentro de buttonedit.js
		if(arrayAutore!=null) {
		
		
		 for (String stringIdAutore : arrayAutore) {
			int idAutore=Integer.parseInt(stringIdAutore);
			Autore autore=autoreDao.FindById(idAutore);
			//autore.setId(idAutore);
			
			
			listAutore.add(autore);
		}
		 
		}
		
		if(isbn == null || isbn.isBlank() || arrayAutore==null) {
			
			doGet(request, response);
			//request.getRequestDispatcher("libri.jsp").forward(request, response);
		}
		
		
		 int numeroPagina= Integer.parseInt(request.getParameter("numero_pagine"));
		 
		 String dateString=request.getParameter("data_pubblicazione");
		 SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		 Date data = new Date();
		try {
			data = sdf.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		Libro libro = new Libro(idGenero, isbn, titolo, descrizione, genere, numeroPagina, data);
		//optener el id del libro pasado nascosto desde la jsp
		int idLibro=Integer.parseInt(request.getParameter("idLibro"));
		libro.setId(idLibro);
		
		libro.setListAutore(listAutore);
		System.out.println("lista de autore a insertarto en libro"+libro.getListAutore());
		libroDao.update(libro);
		response.sendRedirect("libri");
	}

}
