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
	private EntitiesFactory factory;
	
	@Inject
	private UsuarioREST rest;
	
	
	@Test
	public void listaUsuariosVaciaDevuelveNoContent()
	{
		Response respuesta=rest.listarUsuariosActivos();
		assertEquals(Status.NO_CONTENT.getStatusCode(),respuesta.getStatus());
	}
	
	@Test
	@UsingDataSet(ConstantesPruebas.ARCHIVO_DATOS_USUARIOS_JSON)
	@Transactional(TransactionMode.ROLLBACK)
	@Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
	public void debePermitirBusquedaPorCedula()
	{
		/*
		 * las busquedas exitosas devuelven OK
		 * las busquedas fallidas devuelven NOT_FOUND
		 */		
		
		Usuario usuario=null;
		Response respuesta=null;
		
		usuario=factory.crearUsuarioFinal();
		
		usuario.setCedula("V17230972");		
		respuesta=rest.busquedaActivo(usuario.getCedula());
		assertEquals(Status.NOT_FOUND.getStatusCode(),respuesta.getStatus());
		
		usuario.setCedula("V17230971");		
		respuesta=rest.busquedaActivo(usuario.getCedula());		
		assertEquals(Status.OK.getStatusCode(),respuesta.getStatus());
		
		UsuarioJSON usuariojson=(UsuarioJSON) respuesta.getEntity();		
		assertEquals(true,usuariojson.getCedula().equals(usuario.getCedula()));
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@UsingDataSet(ConstantesPruebas.ARCHIVO_DATOS_USUARIOS_JSON)
	@Transactional(TransactionMode.ROLLBACK)
	@Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
	public void listaUsuariosDevuelveJSON()
	{
		Response respuesta=rest.listarUsuariosInActivos();
		assertEquals(Status.OK.getStatusCode(), respuesta.getStatus());
		assertEquals(true, respuesta.getEntity() instanceof List<?>);
		List<UsuarioJSON> lista=(List<UsuarioJSON>)respuesta.getEntity();
		assertEquals(1,lista.size());
	}
	
	@Test
	@UsingDataSet(ConstantesPruebas.ARCHIVO_DATOS_USUARIOS_JSON)
	@Transactional(TransactionMode.ROLLBACK)
	@Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
	public void debeValidarDisponibilidadDeLogin()
	{
		/*
		 * si el login esta disponible devuelve OK
		 * si el login ya esta usado devuelve CONFLICT
		 */
		
		Usuario usuario=null;
		Response respuesta=null;
		
		usuario=factory.crearUsuarioFinal();
		
		usuario.setLogin("jorgegonzalez");		
		respuesta=rest.disponibilidadLogin(usuario);		
		assertEquals(Status.OK.getStatusCode(), respuesta.getStatus());
		
		usuario.setLogin("jorgeejgonzalez");
		respuesta=rest.disponibilidadLogin(usuario);		
		assertEquals(Status.CONFLICT.getStatusCode(), respuesta.getStatus());
	}
	
	@Test
	@UsingDataSet(ConstantesPruebas.ARCHIVO_DATOS_USUARIOS_JSON)
	@Transactional(TransactionMode.ROLLBACK)
	@Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
	public void debeValidarDisponibilidadDeEmail()
	{
		/*
		 * si el email esta disponible devuelve OK
		 * si el email ya esta usado devuelve CONFLICT
		 */
		
		Usuario usuario=null;
		Response respuesta=null;
		
		usuario=factory.crearUsuarioFinal();
		
		usuario.setEmail("moody@web.com");
		respuesta=rest.disponibilidadEmail(usuario);
		assertEquals(Status.OK.getStatusCode(), respuesta.getStatus());
		
		usuario.setEmail("paul@algo.com");
		respuesta=rest.disponibilidadEmail(usuario);
		assertEquals(Status.CONFLICT.getStatusCode(), respuesta.getStatus());
	}
	
	@Test
	@UsingDataSet(ConstantesPruebas.ARCHIVO_DATOS_USUARIOS_JSON)
	@Transactional(TransactionMode.ROLLBACK)
	@Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
	public void debeCrearUsuariosNuevoSiPasanValidacion()
	{
		/*
		 * si la beanValidation del usuario falla, devuelve INTERNAL_SERVER_ERROR
		 * si la cedula, el login o el email no estan disponibles, devuelve CONFLICT
		 * Si ocurre alguna excepcion no controlada, devuelve INTERNAL_SERVER_ERROR
		 * la operacion es exitosa devuelve OK
		 */
		
		Usuario usuario=factory.crearUsuarioFinal();
		Response respuesta=rest.crearUsuarioFinal(usuario);
		assertEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), respuesta.getStatus());
		
		usuario.setCedula("V17230971");
		usuario.setLogin("jorgeejgonzalez");
		usuario.setPassword("abcd1234");
		usuario.setEmail("jorge@algo.com");
		usuario.setNombre("jorge");
		usuario.setApellido("gonzalez");
		usuario.setDireccion("san francisco");
		usuario.setTelefono("02617000000");
		
		respuesta=rest.crearUsuarioFinal(usuario);
		assertEquals(Status.CONFLICT.getStatusCode(), respuesta.getStatus());
		
		usuario.setCedula("V17230972");
		
		respuesta=rest.crearUsuarioFinal(usuario);
		assertEquals(Status.CONFLICT.getStatusCode(), respuesta.getStatus());
		
		usuario.setLogin("jorgegonzalez");
		
		respuesta=rest.crearUsuarioFinal(usuario);
		assertEquals(Status.CONFLICT.getStatusCode(), respuesta.getStatus());
		
		usuario.setEmail("jorge@gonzalez.com");
		
		respuesta=rest.crearUsuarioFinal(usuario);
		assertEquals(Status.OK.getStatusCode(), respuesta.getStatus());
		
		UsuarioJSON retorno=(UsuarioJSON)respuesta.getEntity();		
		assertEquals(TipoUsuario.FINAL, retorno.getTipo());
	}
	
	@Test
	@UsingDataSet(ConstantesPruebas.ARCHIVO_DATOS_USUARIOS_JSON)
	@Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
	public void debeModificarDatosDeUsuarioSiPasanValidacion()
	{
		/*
		 * si la beanValidation del usuario falla, devuelve INTERNAL_SERVER_ERROR		 * 
		 * si la cedula del usuario no existe devuelve NOT_FOUND
		 * Si ocurre alguna excepcion no controlada, devuelve INTERNAL_SERVER_ERROR
		 * la operacion es exitosa devuelve OK
		 */
		
		Usuario usuario=null;
		Response respuesta=null;
		
		usuario=factory.crearUsuarioFinal();		
		
		usuario.setCedula("V17230971");
		usuario.setLogin("");
		usuario.setPassword("");
		usuario.setEmail("jorgealgo.com");
		usuario.setNombre("jorge");
		usuario.setApellido("gonzalez");
		usuario.setDireccion("san francisco");
		usuario.setTelefono("02617000000");
		
		respuesta=rest.actualizarUsuario(usuario.getCedula(), usuario);
		assertEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), respuesta.getStatus());
		
		usuario.setCedula("V17230970");
		usuario.setLogin("jorgegonzalez");
		usuario.setPassword("abcd1234");
		usuario.setEmail("jorge@algo.com");
		usuario.setNombre("jorge enrique");
		usuario.setApellido("gonzalez");
		usuario.setDireccion("san francisco");
		usuario.setTelefono("02617000000");
		
		respuesta=rest.actualizarUsuario(usuario.getCedula(), usuario);
		assertEquals(Status.NOT_FOUND.getStatusCode(), respuesta.getStatus());
						
		usuario.setCedula("V17230971");
		usuario.setLogin("jorgegonzalez");
		usuario.setPassword("abcd1234");
		usuario.setEmail("jorge@algo.com");
		usuario.setNombre("jorge enrique");
		usuario.setApellido("gonzalez");
		usuario.setDireccion("san francisco");
		usuario.setTelefono("02617000000");
		
		respuesta=rest.actualizarUsuario(usuario.getCedula(), usuario);
		assertEquals(Status.OK.getStatusCode(), respuesta.getStatus());
		
		UsuarioJSON usuariojson=(UsuarioJSON)respuesta.getEntity();
		
		assertEquals(true, usuario.getNombre().equals(usuariojson.getNombre()));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@UsingDataSet(ConstantesPruebas.ARCHIVO_DATOS_USUARIOS_JSON)
	@Transactional(TransactionMode.ROLLBACK)
	@Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
	public void debeEliminarUsuarios()
	{
		/*
		 * Si la cedula del usuario no existe o ya fue eliminado, devuelve NOT_FOUND
		 * Si ocurre alguna excepcion no controlada, devuelve INTERNAL_SERVER_ERROR
		 * Si el registro no cambia su estatus, devuelve NOT_MODIFIED
		 * Si se elimina correctamente, devuelve OK
		 */
		
		Response respuesta=null;
		List<UsuarioJSON> lista=null;
		Iterator<UsuarioJSON> iterator=null;
		
		respuesta=rest.listarUsuariosActivos();
		assertEquals(Status.OK.getStatusCode(), respuesta.getStatus());
		
		lista=(List<UsuarioJSON>)respuesta.getEntity();
		assertEquals(false, lista.isEmpty());
		
		iterator=lista.iterator();
		
		while(iterator.hasNext())
		{
			respuesta=rest.eliminarUsuario(iterator.next().getCedula());
			assertEquals(Status.OK.getStatusCode(), respuesta.getStatus());
		}
		
		respuesta=rest.listarUsuariosEliminados();
		assertEquals(Status.OK.getStatusCode(), respuesta.getStatus());
		
		lista=(List<UsuarioJSON>)respuesta.getEntity();
		assertEquals(false, lista.isEmpty());
		
		iterator=lista.iterator();
		
		while(iterator.hasNext())
		{
			respuesta=rest.eliminarUsuario(iterator.next().getCedula());
			assertEquals(Status.NOT_FOUND.getStatusCode(), respuesta.getStatus());
		}
		
	}	
	
	@SuppressWarnings("unchecked")
	@Test
	@UsingDataSet(ConstantesPruebas.ARCHIVO_DATOS_USUARIOS_JSON)
	@Transactional(TransactionMode.ROLLBACK)
	@Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
	public void debeActivarUsuariosInactivos()
	{
		/*
		 * Si el usuario no existe o esta eliminado devuelve NOT_FOUND
		 * Si el usuario ya se encuentra activo devuelve NOT_MODIFIED
		 * Si se activa correctamente devuelve OK 
		 */
		
		Response respuesta=null;
		List<UsuarioJSON> lista=null;
		Iterator<UsuarioJSON> iterator=null;
		
		respuesta=rest.listarUsuariosActivos();
		assertEquals(Status.OK.getStatusCode(), respuesta.getStatus());
		
		lista=(List<UsuarioJSON>)respuesta.getEntity();
		iterator=lista.iterator();
		
		while(iterator.hasNext())
		{
			respuesta=rest.activarUsuario(iterator.next().getCedula());
			assertEquals(Status.NOT_MODIFIED.getStatusCode(), respuesta.getStatus());
		}		
		
		respuesta=rest.listarUsuariosInActivos();
		assertEquals(Status.OK.getStatusCode(), respuesta.getStatus());
		
		lista=(List<UsuarioJSON>)respuesta.getEntity();
		iterator=lista.iterator();
		
		while(iterator.hasNext())
		{
			respuesta=rest.activarUsuario(iterator.next().getCedula());
			assertEquals(Status.OK.getStatusCode(), respuesta.getStatus());
		}
		
		respuesta=rest.listarUsuariosEliminados();
		assertEquals(Status.OK.getStatusCode(), respuesta.getStatus());
		
		lista=(List<UsuarioJSON>)respuesta.getEntity();
		iterator=lista.iterator();
		
		while(iterator.hasNext())
		{
			respuesta=rest.activarUsuario(iterator.next().getCedula());
			assertEquals(Status.NOT_FOUND.getStatusCode(), respuesta.getStatus());
		}		
				
	}
	
	@Test
	@UsingDataSet(ConstantesPruebas.ARCHIVO_DATOS_USUARIOS_JSON)
	@Transactional(TransactionMode.ROLLBACK)
	@Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
	public void debeRealizarLoginAlSistema()
	{
		/*
		 * Si el login no se encuentra o esta eliminado devuelve NOT_FOUND
		 * Si el password es incorrecto devuelve UNAUTHORIZED
		 * Si se se autentica correctamente devuelve OK 
		 */
		Usuario usuario=null;
		Response respuesta=null;
		
		usuario=factory.crearUsuarioFinal();
		usuario.setLogin("jorge");
		usuario.setPassword("abcd1234");
		
		respuesta=rest.realizarLogin(usuario);
		assertEquals(Status.NOT_FOUND.getStatusCode(),respuesta.getStatus());
		
		usuario.setLogin("jorgeejgonzalez");
		
		respuesta=rest.realizarLogin(usuario);
		assertEquals(Status.UNAUTHORIZED.getStatusCode(),respuesta.getStatus());
		
		usuario.setLogin("jorgeejgonzalez");
		usuario.setPassword("algoalgo");
		
		respuesta=rest.realizarLogin(usuario);
		assertEquals(Status.OK.getStatusCode(),respuesta.getStatus());
	}

}
