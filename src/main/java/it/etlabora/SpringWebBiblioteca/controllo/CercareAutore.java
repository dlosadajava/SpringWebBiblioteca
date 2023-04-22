package it.etlabora.SpringWebBiblioteca.controllo;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import it.etlabora.SpringWebBiblioteca.modello.Autore;
import it.etlabora.SpringWebBiblioteca.persisteza.AutoreDao;
import it.etlabora.SpringWebBiblioteca.persisteza.impl.AutoreDaoImpl;

/**
 * Servlet implementation class cercareAutore
 */
@WebServlet("/CercareAutore")
public class CercareAutore extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AutoreDao autoreDao= new AutoreDaoImpl(); 
    
//metodo para comprobar que en la sesion la variable username(utenteId) del form login existe
    
    private boolean checkSession(HttpSession session) {
		if (session.getAttribute("utenteId") == null) {
			return false;
		}
		return true;
	}

    
    /////////////////////////////7
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//ver si el utente esta registrado en la sesion
				//crear metodo session
				if (!checkSession(request.getSession())) {
					response.sendRedirect("login.jsp");
					return;
				}
				
				//logica del caso de uso
		
		List<Autore> listFindAll=new LinkedList<>();
		
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		if(nome!=null || cognome!=null ) {
			
			listFindAll=autoreDao.findAllNomeCognomeLIKE(nome, cognome);
		}else {
			listFindAll=autoreDao.findAll();
		}
		
		
		
		request.setAttribute("listAutore", listFindAll);
		
		request.getRequestDispatcher("cercareAutore.jsp").forward(request, response);
	
	
	}

	

}
