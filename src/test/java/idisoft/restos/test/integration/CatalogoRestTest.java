package idisoft.restos.test.integration;

import static org.junit.Assert.*;

import idisoft.restos.data.EntitiesFactory;
import idisoft.restos.rest.CatalogoREST;
import idisoft.restos.test.util.Archiver;

import javax.inject.Inject;

import org.junit.Test;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CatalogoRestTest {
	
	@Deployment
	public static Archive<?> createTestArchive()
	{
		WebArchive war=Archiver.archivoWeb();		
		return  war;
	}
	
	@Inject
	private EntitiesFactory factory;
	
	@Inject
	private CatalogoREST rest;
	
	@Test
	public void debeListarLosDiferentesCatalogos()
	{
		fail("not yet implemented");
	}
	
	@Test
	public void debeBuscarCatalogoPorId()
	{
		fail("not yet implemented");
	}
	
	@Test
	public void debeCrearCatalogosNuevosSiPasanValidacion()
	{
		fail("not yet implemented");
	}
	
	@Test
	public void debeModificarCatalogosSiPasanValidacion()
	{
		fail("not yet implemented");
	}
	
	@Test
	public void debeEliminarCatalogos()
	{
		fail("not yet implemented");
	}
	
	@Test
	public void debeCrearElementosDeCatalogo()
	{
		fail("not yet implemented");
	}
	
	@Test
	public void debeModificarElementosDeCatalogo()
	{
		fail("not yet implemented");
	}
	
	@Test
	public void debeEliminarElementosDeCatalogo()
	{
		fail("not yet implemented");
	}
	
	@Test
	public void debeAgregarElementosIndividualesAlCatalogo()
	{
		fail("not yet implemented");
	}
	
	@Test
	public void debeAgregarListaDeElementosAlCatalogo()
	{
		fail("not yet implemented");
	}

}
