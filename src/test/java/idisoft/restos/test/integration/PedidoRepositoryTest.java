package idisoft.restos.test.integration;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import idisoft.restos.data.PedidoRepository;
import idisoft.restos.entities.Pedido;
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
public class PedidoRepositoryTest {
	
	@Deployment
	public static Archive<?> createTestArchive()
	{
		WebArchive war=Archiver.archivoWeb();		
		return  war;
	}
	
	@Inject
	private PedidoRepository repositorio;
	
	@Test(expected=NoResultException.class)
	public void busquedaFallidaDePedidoGeneraExcepcion()
	{
		Pedido p=repositorio.findById(0);
		assertEquals(null,p);		
	}
	
	@Test(expected=NoResultException.class)
	public void listaPedidosActivosVaciaGeneraExcepcion()
	{
		List<Pedido> p=repositorio.findAllActive();
		assertEquals(0,p.size());		
	}
	
	@Test(expected=NoResultException.class)
	public void listaPedidosInactivosVaciaGeneraExcepcion()
	{
		List<Pedido> p=repositorio.findAllInactive();
		assertEquals(0,p.size());		
	}
	
	@Test(expected=NoResultException.class)
	public void listaPedidosEliminadosVaciaGeneraExcepcion()
	{
		List<Pedido> p=repositorio.findAllDeleted();
		assertEquals(0,p.size());		
	}
	
	@Test(expected=NoResultException.class)
	public void busquedaPedidosDeUsuarioFallidoGeneraExcepcion()
	{
		List<Pedido> p=repositorio.findAllActiveByUsuario("V17000000");
		assertEquals(0,p.size());
	}
	
	@Test(expected=NoResultException.class)
	@UsingDataSet(ConstantesPruebas.ARCHIVO_DATOS_JSON)
	@Transactional(TransactionMode.ROLLBACK)
	@Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
	public void busquedaPedidosDeUsuarioVaciaGeneraExcepcion()
	{
		List<Pedido> p=repositorio.findAllInactiveByUsuario("V17230971");
		assertEquals(0,p.size());
	}
	
	
}
