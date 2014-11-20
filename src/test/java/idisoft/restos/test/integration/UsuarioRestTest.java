package idisoft.restos.test.integration;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import idisoft.restos.data.EntitiesFactory;
import idisoft.restos.entities.TipoUsuario;
import idisoft.restos.entities.Usuario;
import idisoft.restos.entities.json.UsuarioJSON;
import idisoft.restos.rest.UsuarioREST;
import idisoft.restos.test.util.Archiver;
import idisoft.restos.test.util.ConstantesPruebas;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.Cleanup;
import org.jboss.arquillian.persistence.CleanupStrategy;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class UsuarioRestTest {
	
	@Deployment
	public static Archive<?> createTestArchive()
	{
		WebArchive war=Archiver.archivoWeb();		
		return  war;
	}
	
	@Inject
	private UsuarioREST rest;
	
	@Inject
	private EntitiesFactory factory;
	
	@Test
	public void listaUsuariosVaciaDevuelveNoContent()
	{
		Response respuesta=rest.listarUsuariosActivos();
		assertEquals(204,respuesta.getStatus());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@UsingDataSet(ConstantesPruebas.ARCHIVO_DATOS_USUARIOS_JSON)
	@Transactional(TransactionMode.ROLLBACK)
	@Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
	public void listaUsuariosDevuelveJSON()
	{
		Response respuesta=rest.listarUsuariosInActivos();
		assertEquals(200, respuesta.getStatus());
		assertEquals(true, respuesta.getEntity() instanceof List<?>);
		List<UsuarioJSON> lista=(List<UsuarioJSON>)respuesta.getEntity();
		
		Iterator<UsuarioJSON> iterator=lista.iterator();
		
		while(iterator.hasNext())
		{
			UsuarioJSON ujs=iterator.next();
			assertEquals(TipoUsuario.MASTER, ujs.getTipo());
		}
	}
	
	@Test
	@UsingDataSet(ConstantesPruebas.ARCHIVO_DATOS_USUARIOS_JSON)
	@Transactional(TransactionMode.ROLLBACK)
	@Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
	public void validaDisponibilidadDeLogin()
	{
		Usuario usuario=factory.crearUsuarioFinal();
		
		usuario.setLogin("jorgegonzalez");
		
		Response respuesta=rest.disponibilidadLogin(usuario);
		
		assertEquals(Status.OK.getStatusCode(), respuesta.getStatus());
		
		usuario.setLogin("jorgeejgonzalez");
		respuesta=rest.disponibilidadLogin(usuario);
		
		assertEquals(Status.CONFLICT.getStatusCode(), respuesta.getStatus());
	}
	
	@Test
	public void validaDisponibilidadDeEmail()
	{
		Usuario usuario=factory.crearUsuarioFinal();
		
		usuario.setEmail("moody@web.com");
		
		Response respuesta=rest.disponibilidadEmail(usuario);
		
		assertEquals(Status.OK.getStatusCode(), respuesta.getStatus());
		
		usuario.setEmail("paul@algo.com");
		respuesta=rest.disponibilidadEmail(usuario);
		
		assertEquals(Status.CONFLICT.getStatusCode(), respuesta.getStatus());
	}
	
	@Test
	public void creaUsuarioNuevoSiPasaValidacion()
	{
		fail("not yet implemented");
	}
	
	@Test
	public void modificaDatosDeUsuarioSiPasaValidacion()
	{
		fail("not yet implemented");
	}
	
	@Test
	public void activaUsuariosInactivos()
	{
		fail("not yet implemented");
	}
	
	public void realizaLoginAlSistema()
	{
		fail("not yet implemented");
	}

}
