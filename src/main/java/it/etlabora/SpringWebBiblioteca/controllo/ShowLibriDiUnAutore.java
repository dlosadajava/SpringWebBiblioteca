package it.etlabora.SpringWebBiblioteca.controllo;

import java.io.IOException;
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

/**
 * Servlet implementation class ShowLibriDiUnAutore
 */
@WebServlet("/ShowLibriDiUnAutore")
public class ShowLibriDiUnAutore extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	
	private AutoreDao autoreDao = ctx.getBean(AutoreDao.class);
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idAutore=Integer.parseInt(request.getParameter("idAutore"));
		System.err.println("idAutore " +idAutore);
		List<Libro> listLibribyIdAutori = autoreDao.listLibribyIdAutore(idAutore);
		
		System.out.println("Busca lista di libro di un autore: " +listLibribyIdAutori);
		request.setAttribute("listLibribyIdAutore", listLibribyIdAutori);
		
		
		String nomeAutore=request.getParameter("nomeAutore");
		request.setAttribute("nomeAutore", nomeAutore);
		
		request.getRequestDispatcher("showLibroDaAutori.jsp").forward(request, response);
	}


}
