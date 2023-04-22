package it.etlabora.SpringWebBiblioteca.controllo;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.etlabora.SpringWebBiblioteca.AppConfig;
import it.etlabora.SpringWebBiblioteca.modello.Autore;
import it.etlabora.SpringWebBiblioteca.modello.Libro;
import it.etlabora.SpringWebBiblioteca.modello.Utente;
import it.etlabora.SpringWebBiblioteca.persisteza.AutoreDao;
import it.etlabora.SpringWebBiblioteca.persisteza.LibroDao;
import it.etlabora.SpringWebBiblioteca.persisteza.PersistenceUtil;
import it.etlabora.SpringWebBiblioteca.persisteza.UtenteDao;
import it.etlabora.SpringWebBiblioteca.persisteza.impl.AutoreDaoImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //private UtenteDao utenteDao= new UtenteDaoImpl();
	//Spring clase de conf que scanea los beans
	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	
	

	 
	//private UtenteDao utenteDao;//con spring
	private UtenteDao utenteDao=ctx.getBean(UtenteDao.class);
	private LibroDao libroDao=ctx.getBean(LibroDao.class);
	private AutoreDao autoreDao=ctx.getBean(AutoreDao.class);
    //private LibroDao libroDao= new LibroDaoImpl();
   // private AutoreDao autoreDao= new AutoreDaoImpl();
   
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String unsername=request.getParameter("username");
		String password=request.getParameter("password");
		System.out.println("user:"+unsername+" pass:"+password);
		System.out.println("Estoy dentro la servlet: doPost");
		Utente u=utenteDao.findByUsername(unsername);
		System.out.println(u.getNome());
		System.out.println(u.getPassword());
		if(u!=null && u.getPassword().equals(password)) {
			System.out.println("Estoy dentro la servlet: usuario corretto");
			//request.setAttribute("unsername", u.getNome());
			HttpSession session = request.getSession();
			session.setAttribute("unsername", u.getNome());
			session.setAttribute("utenteId", u.getId());
			
			/*Enviar datos de los libri y autore para mostrar las tablas en index.jsp*/
			List<Libro> listLibro= new LinkedList();
			List<Autore> listAutore= new LinkedList();
			listLibro=libroDao.FindAll();
			listAutore=autoreDao.findAll();
			request.setAttribute("listLibro", listLibro);
			request.setAttribute("listAutore", listAutore);
			
			/**/
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {
			System.out.println("Estoy dentro la servlet: usuario incorretto");
			response.sendRedirect("login.jsp");
		}
		
	}

}
