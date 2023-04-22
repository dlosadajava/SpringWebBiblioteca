package it.etlabora.SpringWebBiblioteca.controllo;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.etlabora.SpringWebBiblioteca.AppConfig;
import it.etlabora.SpringWebBiblioteca.modello.Autore;
import it.etlabora.SpringWebBiblioteca.persisteza.AutoreDao;
import it.etlabora.SpringWebBiblioteca.persisteza.GenereDao;
import it.etlabora.SpringWebBiblioteca.persisteza.impl.AutoreDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InserisceAutore
 */
@WebServlet("/InserisceAutore")
public class InserisceAutore extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//SPRING
	AnnotationConfigApplicationContext ctx= new AnnotationConfigApplicationContext(AppConfig.class);
	
	//private AutoreDao autoreDao = new AutoreDaoImpl();
       
	private AutoreDao autoreDao =ctx.getBean(AutoreDao.class);//spring
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nomeAutor = request.getParameter("nomeAutorNuovo");
		String cognomeAutor = request.getParameter("cognomeAutorNuovo");
		String dataAutor = request.getParameter("dataAutorNuovo");

		Autore autore = new Autore();
		autore.setNome(nomeAutor);
		autore.setCognome(cognomeAutor);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");

		Date date=null;

		
			try {
				date = formatter.parse(dataAutor);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		autore.setData_nascita(date);
		autoreDao.insert(autore);
		
		

		response.sendRedirect("autori");

	}
	
	//implementardoGet
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

}
