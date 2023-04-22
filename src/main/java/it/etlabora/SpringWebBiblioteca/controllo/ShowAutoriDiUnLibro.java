package it.etlabora.SpringWebBiblioteca.controllo;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.etlabora.SpringWebBiblioteca.AppConfig;
import it.etlabora.SpringWebBiblioteca.modello.Autore;
import it.etlabora.SpringWebBiblioteca.persisteza.LibroDao;
import it.etlabora.SpringWebBiblioteca.persisteza.impl.LibroDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowAutoriDiUnLibro
 */
@WebServlet("/ShowAutoriDiUnLibro")
public class ShowAutoriDiUnLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Spring clase de conf que scanea los beans
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		
	private LibroDao libroDao = ctx.getBean(LibroDao.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				int idLibro=Integer.parseInt(request.getParameter("idLibro"));
				System.err.println("idLibro " +idLibro);
				List<Autore> listAutoribyIdLIbro = libroDao.listAutoribyIdLIbro(idLibro);
				System.out.println("Busca lista di libro: " +listAutoribyIdLIbro);
				request.setAttribute("listAutoribyIdLIbro", listAutoribyIdLIbro);
				
				String titoloLibro=request.getParameter("titoloLibro");
				request.setAttribute("titoloLibro", titoloLibro);
				request.getRequestDispatcher("ShowAutoriDiUnLibro.jsp").forward(request, response);
	}

	
}
