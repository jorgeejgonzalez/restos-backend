package idisoft.restos.test.integration;


import static org.junit.Assert.*;

import java.util.List;

import idisoft.restos.data.ListRecords;
import idisoft.restos.data.Repository;
import idisoft.restos.data.UsuarioRepository;
import idisoft.restos.entities.Usuario;
import idisoft.restos.test.util.Archiver;

import javax.inject.Inject;
import javax.persistence.NoResultException;



import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class UsuarioRepositoryTests {
	
	@Inject
	private UsuarioRepository repositorio;
	
	@Deployment
	public static Archive<?> createTestArchive()
	{
		WebArchive war=Archiver.archivoWeb();
		war.addClass(ListRecords.class);
		war.addClass(Repository.class);
		war.addClass(UsuarioRepository.class);
		return  war;
	}
	
	@Test(expected=NoResultException.class)
	public void  listaUsuariosVaciaRetornaExcepcion()
	{
		List<Usuario> usuarios=repositorio.findAll();
		assertEquals(0,usuarios.size());		
	}
	
	@Test(expected=NoResultException.class)
	public void  listaUsuariosActivosVaciaRetornaExcepcion()
	{
		List<Usuario> usuarios=repositorio.findAllActive();
		assertEquals(0,usuarios.size());		
	}
	
	@Test(expected=NoResultException.class)
	public void  listaUsuariosInactivosrVaciaRetornaExcepcion()
	{
		List<Usuario> usuarios=repositorio.findAllInactive();
		assertEquals(0,usuarios.size());
	}
	
	@Test(expected=NoResultException.class)
	public void  listaUsuariosEliminadosVaciaRetornaExcepcion()
	{
		List<Usuario> usuarios=repositorio.findAllDeleted();
		assertEquals(0,usuarios.size());
	}
	
	@Test(expected=NoResultException.class)
	public void busquedaPorCedulaFallidaRetornaExcepcion()
	{
		Usuario usuario=repositorio.findByCedula("V00000000");
		assertEquals(null,usuario);		
	}
	
	@Test(expected=NoResultException.class)
	public void busquedaPorLoginFallidaRetornaExcepcion()
	{
		Usuario usuario=repositorio.findByLogin("falsofalso");
		assertEquals(null,usuario);		
	}
	
	@Test(expected=NoResultException.class)
	public void busquedaPorEmailFallidaRetornaExcepcion()
	{
		Usuario usuario=repositorio.findByEmail("falso@falso.com");
		assertEquals(null,usuario);		
	}
	
	@Test
	@UsingDataSet("usuarios.yml")
	public void busquedaPorCedulaDevuelveUsuario()
	{
		Usuario usuario=repositorio.findByCedula("V17230971");
		assertEquals("jorge",usuario.getNombre());
	}
	
	@Test
	@UsingDataSet("usuarios.yml")
	public void busquedaPorLoginDevuelveUsuario()
	{
		Usuario usuario=repositorio.findByLogin("jorgeejgonzalez");
		assertEquals("jorge",usuario.getNombre());
	}
	
	@Test
	@UsingDataSet("usuarios.yml")
	public void busquedaPorEmailDevuelveUsuario()
	{
		Usuario usuario=repositorio.findByEmail("jorge@algo.com");
		assertEquals("jorge",usuario.getNombre());
	}
	
	@Test
	@UsingDataSet("usuarios.yml")
	public void listaUsuariosRetornaList()
	{
		List<Usuario> usuarios=repositorio.findAll();
		assertEquals(3, usuarios.size());
	}
}
