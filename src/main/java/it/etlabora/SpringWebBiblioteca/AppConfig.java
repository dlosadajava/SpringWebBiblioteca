package it.etlabora.SpringWebBiblioteca;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import it.etlabora.SpringWebBiblioteca.persisteza.AutoreDao;
import it.etlabora.SpringWebBiblioteca.persisteza.LibroDao;
import it.etlabora.SpringWebBiblioteca.persisteza.UtenteDao;
import it.etlabora.SpringWebBiblioteca.persisteza.impl.AutoreDaoImpl;
import it.etlabora.SpringWebBiblioteca.persisteza.impl.LibroDaoImpl;
import it.etlabora.SpringWebBiblioteca.persisteza.impl.UtenteDaoImpl;

@Configuration
//@PropertySource("classpath:application.properties")
@ComponentScan
public class AppConfig {


	
//	@Bean
//	public UtenteDao getUtenteDao() {
//		UtenteDao ud=new UtenteDaoImpl();
//		
//		return ud;
//		
//	}
//	
//	@Bean
//	public LibroDao getLibroDao() {
//		LibroDao ld=new LibroDaoImpl();
//		
//		return ld;
//		
//	}
//	
//	@Bean
//	public AutoreDao getAutoreDao() {
//		AutoreDao ad=new AutoreDaoImpl();
//		
//		return ad;
//		
//	}
	//LibroDao
	//AutoreDao

}
