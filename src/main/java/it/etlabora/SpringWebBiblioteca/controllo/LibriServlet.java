package it.etlabora.SpringWebBiblioteca.controllo;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import it.etlabora.SpringWebBiblioteca.modello.Autore;
import it.etlabora.SpringWebBiblioteca.modello.ExportExcel;
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
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LibriServlet
 */
@WebServlet("/libri")
public class LibriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LibroDao libroDao = new LibroDaoImpl();
	private GenereDao generoDao = new GenereDaoImpl();
	private AutoreDao autoreDao = new AutoreDaoImpl();

	// metodo para comprobar que en la sesion la variable username(utenteId) del
	// form login existe

	private boolean checkSession(HttpSession session) {
		if (session.getAttribute("utenteId") == null) {
			return false;
		}
		return true;
	}

	///////////////////////////// 7

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// checkSession implementado
		if (!checkSession(request.getSession())) {
			response.sendRedirect("login.jsp");
			return;
		}

		List<Libro> listLibro = null;
		List<Genere> listGenero = generoDao.FindAll();

		List<Autore> listAutore = autoreDao.findAll();

		String isbn = request.getParameter("isbn");
		String titolo = request.getParameter("titolo");
		String autore = request.getParameter("autore");
		String genere = request.getParameter("genere");
		
		
		
		
		System.out.println("Valor del isbn dentro servlet: "+isbn);
		System.out.println("Valor del titolo dentro servlet: "+titolo);
		System.out.println("Valor del genere dentro servlet: "+genere);
		System.out.println("Valor del autore dentro servlet: "+autore);

		if ((isbn == null || isbn.isBlank()) && (titolo == null || titolo.isBlank()) && (autore == null || autore.isBlank()) && (genere == null || genere.isBlank())) {
			listLibro = libroDao.FindAll();
			System.out.println("condicion de todos null");
		}else if(isbn!=null && !isbn.isBlank()) {
			System.out.println("condicion de isbn not null");
			Libro libro=libroDao.findByIsbn(isbn);
			//listLibro.add(libro);
			listLibro=Arrays.asList(libro);
		}else {
			if(titolo!=null && !titolo.isBlank()) {
				System.out.println("condicion de titolo not null");
				listLibro=libroDao.findByTitoloLike(titolo);
				
			}
			
			 if(autore!=null /*&& !autore.isBlank()*/ /*!autore.equals(" -- select an option -- ")*/) {
				 System.out.println("condicion de autore not null");
				 listLibro =libroDao.findByAutore(autore);
			}
			 if(genere!=null /*&& !genere.isBlank()*/) {
				 System.out.println("condicion de genere not null");
				listLibro=libroDao.findByGenere(genere);
			}
			
		}
		 
//			

		

		request.setAttribute("libri", listLibro);
		request.setAttribute("generi", listGenero);
		request.setAttribute("autori", listAutore);
		
		/*export in excel in costruzione*/
		ExportExcel ee= new ExportExcel();
		ee.licencia();
		ee.createRow(listLibro);
		ee.getWorksheet ();
		
		
		/*end export in excel */
		
		
		request.getRequestDispatcher("libri.jsp").forward(request, response);

	}

}
