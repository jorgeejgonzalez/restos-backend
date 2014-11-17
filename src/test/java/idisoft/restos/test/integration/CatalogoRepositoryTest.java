package idisoft.restos.test.integration;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import idisoft.restos.data.CatalogosRepository;
import idisoft.restos.data.ListRecords;
import idisoft.restos.data.Repository;
import idisoft.restos.data.UsuarioRepository;
import idisoft.restos.entities.Catalogo;
import idisoft.restos.test.util.Archiver;
import idisoft.restos.test.util.ConstantesPruebas;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.Cleanup;
import org.jboss.arquillian.persistence.CleanupStrategy;
import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CatalogoRepositoryTest {
	
	@Deployment
	public static Archive<?> createTestArchive()
	{
		WebArchive war=Archiver.archivoWeb();
		
		return  war;
	}

	@Inject
	CatalogosRepository repositorio;
	
	@Test(expected=NoResultException.class)
	public void busquedaPorIdFallidaDevuelveExcepcion() {
		Catalogo catalogo=repositorio.findById(0);
		assertEquals(null, catalogo);
	}
	
	@Test(expected=NoResultException.class)
	public void listaCatalogosVaciaDevuelveExcepcion() {
		List<Catalogo> catalogos=repositorio.findAll();
		assertEquals(0, catalogos.size());
	}

	@Test(expected=NoResultException.class)
	public void listaCatalogosActivosVaciaDevuelveExcepcion() {
		List<Catalogo> catalogos=repositorio.findAllActive();
		assertEquals(0, catalogos.size());
	}
	
	@Test(expected=NoResultException.class)
	public void listaCatalogosInactivosVaciaDevuelveExcepcion() {
		List<Catalogo> catalogos=repositorio.findAllInactive();
		assertEquals(0, catalogos.size());
	}
	
	@Test(expected=NoResultException.class)
	public void listaCatalogosEliminadosVaciaDevuelveExcepcion() {
		List<Catalogo> catalogos=repositorio.findAllDeleted();
		assertEquals(0, catalogos.size());
	}

	@Test(expected=NoResultException.class)
	public void listaCatalogosActivosPorSedeVaciaDevuelveExcepcion() {
		List<Catalogo> catalogos=repositorio.findAllActiveBySede(0);
		assertEquals(0, catalogos.size());
	}

	@Test(expected=NoResultException.class)
	public void listaCatalogosInactivosPorSedeVaciaDevuelveExcepcion() {
		List<Catalogo> catalogos=repositorio.findAllInactiveBySede(0);
		assertEquals(0, catalogos.size());
	}

	@Test(expected=NoResultException.class)
	public void listaCatalogosEliminadosPorSedeVaciaDevuelveExcepcion() {
		List<Catalogo> catalogos=repositorio.findAllDeletedBySede(0);
		assertEquals(0, catalogos.size());
	}
	
	
	@Test
	@UsingDataSet(ConstantesPruebas.ARCHIVO_DATOS_JSON)
	@Transactional(TransactionMode.ROLLBACK)
	@Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
	public void busquedaPorIdDevuelveCatalogo()
	{
		Catalogo catalogo=repositorio.findById(1);
		assertEquals("catalogo de prueba", catalogo.getNombre());
		assertEquals(2, catalogo.getElementos().size());
	}
	
	@Test
	@UsingDataSet(ConstantesPruebas.ARCHIVO_DATOS_JSON)
	@Transactional(TransactionMode.ROLLBACK)
	@Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
	public void listaDeCatalogoPorSedeDevuelveLista()
	{
		List<Catalogo> catalogos=repositorio.findAllActiveBySede(1);		
		assertEquals(1, catalogos.size());
	}
	
}
