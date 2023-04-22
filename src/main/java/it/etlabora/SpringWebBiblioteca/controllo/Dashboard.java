package it.etlabora.SpringWebBiblioteca.controllo;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.etlabora.SpringWebBiblioteca.AppConfig;
import it.etlabora.SpringWebBiblioteca.modello.Autore;
import it.etlabora.SpringWebBiblioteca.modello.Libro;
import it.etlabora.SpringWebBiblioteca.persisteza.AutoreDao;
import it.etlabora.SpringWebBiblioteca.persisteza.LibroDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	private LibroDao libroDao=ctx.getBean(LibroDao.class);
	private AutoreDao autoreDao=ctx.getBean(AutoreDao.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Enviar datos de los libri y autore para mostrar las tablas en index.jsp*/
		List<Libro> listLibro= new LinkedList();
		List<Autore> listAutore= new LinkedList();
		listLibro=libroDao.FindAll();
		listAutore=autoreDao.findAll();
		request.setAttribute("listLibro", listLibro);
		request.setAttribute("listAutore", listAutore);
		
		/**/
		System.out.println("Entra en el doget de LoginServlet");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	
}
