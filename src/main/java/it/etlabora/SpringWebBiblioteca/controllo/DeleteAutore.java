package it.etlabora.SpringWebBiblioteca.controllo;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.etlabora.SpringWebBiblioteca.AppConfig;
import it.etlabora.SpringWebBiblioteca.persisteza.AutoreDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteAutore
 */
@WebServlet("/DeleteAutore")
public class DeleteAutore extends HttpServlet {
	private static final long serialVersionUID = 1L;
   // private AutoreDao autoreDao= new AutoreDaoImpl();
AnnotationConfigApplicationContext ctx= new AnnotationConfigApplicationContext(AppConfig.class);
	
	//private AutoreDao autoreDao = new AutoreDaoImpl();
       
	private AutoreDao autoreDao =ctx.getBean(AutoreDao.class);//spring
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DeleteAutore?idAutore=6
		int idAutore = Integer.parseInt(request.getParameter("idAutore"));
		System.err.println("id autore a eliminar= "+idAutore);
		autoreDao.delete(idAutore);
		response.sendRedirect("autori");
	}

	

}
