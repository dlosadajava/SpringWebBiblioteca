package it.etlabora.SpringWebBiblioteca.controllo;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.etlabora.SpringWebBiblioteca.AppConfig;
import it.etlabora.SpringWebBiblioteca.persisteza.LibroDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class deleteLibro
 */
@WebServlet("/DeleteLibro")
public class DeleteLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //LibroDao libriDao= new LibroDaoImpl();   
	AnnotationConfigApplicationContext ctx= new AnnotationConfigApplicationContext(AppConfig.class);
	private LibroDao libriDao=ctx.getBean(LibroDao.class);//spring
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	int id=Integer.parseInt(request.getParameter("idLibro"));
	libriDao.deleteLibro(id);
	response.sendRedirect("libri");	
	}

	

}
