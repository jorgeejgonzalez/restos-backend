package idisoft.restos.test.integration;

import idisoft.restos.controller.UsuarioController;

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
	
	@Inject
	private UsuarioController controller;
	
	
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
				.addClass(UsuarioController.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		System.out.println(war.toString(true));
		return null;
	}
	
	private static JavaArchive archivoJar()
	{
		JavaArchive jar=ShrinkWrap.create(JavaArchive.class,"test.jar")
				.addClass(UsuarioController.class)
				.addAsManifestResource(EmptyAsset.INSTANCE,"beans.xml");
		System.out.println(jar.toString(true));
		return jar;
	}
	

}
