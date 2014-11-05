package idisoft.restos.test.integration;

import idisoft.restos.entities.*;
import idisoft.restos.entities.json.*;

import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import org.hibernate.exception.ConstraintViolationException;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class UsuarioControlTests {
	
	@Deployment
	public static Archive<?> createTestArchive()
	{
		return UsuarioControlTests.archivoWeb(); 
	}
	
	@Test(expected=NoResultException.class)
	public void  listaUsuariosActivosIsEmpty()
	{
		Assert.fail("TODO: Implementar prueba");
	}
	
	@Test(expected=NoResultException.class)
	public void  listaUsuariosInactivosIsEmpty()
	{
		Assert.fail("TODO: Implementar prueba");
	}
	
	@Test(expected=NoResultException.class)
	public void  listaUsuariosEliminadosIsEmpty()
	{
		Assert.fail("TODO: Implementar prueba");
	}
	
	@Test(expected=EntityNotFoundException.class)
	public void usuarioNotFound()
	{
		Assert.fail("TODO: Implementar prueba");
	}
	
	@Test(expected=EntityExistsException.class)
	public void usuarioExists()
	{
		Assert.fail("TODO: Implementar prueba");
	}
	
	@Test(expected=ConstraintViolationException.class)
	public void usuarioConstraintViolation()
	{
		Assert.fail("TODO: Implementar prueba");
	}
	
	private static WebArchive archivoWeb()
	{
		WebArchive war=ShrinkWrap.create(WebArchive.class,"test.war")
				//.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		System.out.println(war.toString(true));
		
		war.addClasses(
				Catalogo.class,
				CategoriaProducto.class,
				ElementoCatalogo.class,
				Empresa.class,
				EstatusCatalogo.class,
				EstatusPedido.class,
				EstatusRegistro.class,
				Pedido.class,
				Producto.class,
				Sede.class,
				TipoProducto.class,
				TipoUsuario.class,
				Usuario.class);
		
		
		war.addClasses(
				CatalogoJSON.class,
				CategoriaProductoJSON.class,
				ElementoCatalogoJSON.class,
				EmpresaJSON.class,
				PedidoJSON.class,
				SedeJSON.class,
				TipoProductoJSON.class,
				ProductoJSON.class,
				UsuarioJSON.class);
		System.out.println(war.toString(true));
		return war;
	}
	
	private static JavaArchive archivoJar()
	{
		JavaArchive jar=ShrinkWrap.create(JavaArchive.class,"test.jar")
				.addAsManifestResource(EmptyAsset.INSTANCE,"beans.xml");
		System.out.println(jar.toString(true));
		return jar;
	}
	

}
