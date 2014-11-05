package idisoft.restos.test.unit;

import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;

import idisoft.restos.entities.Catalogo;
import idisoft.restos.entities.ElementoCatalogo;
import idisoft.restos.entities.Empresa;
import idisoft.restos.entities.EstatusCatalogo;
import idisoft.restos.entities.EstatusRegistro;
import idisoft.restos.entities.Sede;
import idisoft.restos.util.ConstantesEntidades;

import org.junit.BeforeClass;
import org.junit.Test;

public class CatalogoTest {
	
	private Catalogo catalogo;
	
	private void ensamblarDatos()
	{
		Empresa empresa = new Empresa();
		empresa.setRif("J123456789");
		empresa.setRazonSocial("Empresa de Pruebas JUnit C.A.");
		empresa.setDireccionFiscal("direccion fiscal falsa");
		empresa.setEstatusRegistro(EstatusRegistro.ACTIVO);
		
		Sede sede=new Sede();
		sede.setId(0);
		sede.setNombre("sede de pruebas JUnit");
		sede.setRif("J123456789");
		sede.setEmail("falso@email.com.ve");
		sede.setDireccionFisica("direccion falsa");
		sede.setTelefono("02617000000");
		sede.setEstatusRegistro(EstatusRegistro.ACTIVO);
		empresa.getSedes().add(sede);
		sede.setEmpresa(empresa);
		
		catalogo=new Catalogo();		
		catalogo.setId(200);
		catalogo.setNombre("catalogo de pruebas JUnit");
		catalogo.setEstatus(EstatusCatalogo.DISPONIBLE);
		catalogo.setEstatusRegistro(EstatusRegistro.ACTIVO);
		sede.getCatalogos().add(catalogo);
		catalogo.setSede(sede);
		
		ElementoCatalogo elemento1=new ElementoCatalogo();
		elemento1.setId(0);
		elemento1.setNombre("elemento ficticio 1");
		elemento1.setDescripcion("descripcion falsa de objeto");
		elemento1.setPrecio(100.0f);
		elemento1.setEstatus(EstatusCatalogo.DISPONIBLE);		
		elemento1.setEstatusRegistro(EstatusRegistro.ACTIVO);
		elemento1.setCatalogo(catalogo);
		catalogo.getElementos().add(elemento1);
		
		
		ElementoCatalogo elemento2=new ElementoCatalogo();
		elemento2.setId(1);
		elemento2.setNombre("elemento ficticio 1");
		elemento2.setDescripcion("descripcion falsa de objeto");
		elemento2.setPrecio(100.0f);
		elemento2.setEstatus(EstatusCatalogo.DISPONIBLE);		
		elemento2.setEstatusRegistro(EstatusRegistro.ACTIVO);
		elemento2.setCatalogo(catalogo);
		catalogo.getElementos().add(elemento2);
		
	}
	
	@BeforeClass
	public static void setUp()
	{
		//ensamblarCatalogo();
	}
	
	@Test
	public void nombreIsLessThanSize()
	{
		ensamblarDatos();
		catalogo.setNombre("AS");
		
		Set<ConstraintViolation<Catalogo>> violations= catalogo.validarInstancia();
		
		assertEquals(false, violations.isEmpty());
		assertEquals(1, violations.size());
		assertEquals(ConstantesEntidades.ENTIDAD_CATALOGO_NOMBRE+ConstantesEntidades.VALIDACION_STRING_VALOR_LONGITUD+"10 y 100",
				violations.iterator().next().getMessage());
	}
	
	@Test
	public void sedeIsNotValid()
	{
		ensamblarDatos();
		catalogo.setSede(new Sede());
		
		Set<ConstraintViolation<Catalogo>> violations= catalogo.validarInstancia();
		
		assertEquals(false, violations.isEmpty());
		assertEquals(6, violations.size());		
	}
	
	@Test
	public void empresaIsNotValid()
	{
		ensamblarDatos();
		catalogo.getSede().setEmpresa(new Empresa());
		
		Set<ConstraintViolation<Catalogo>> violations= catalogo.validarInstancia();
		
		assertEquals(false, violations.isEmpty());
		assertEquals(2, violations.size());		
	}
	
	@Test
	public void catalogoIsNotOfSede()
	{
		ensamblarDatos();
		
		Sede sede=new Sede();
		sede.setId(1);
		sede.setNombre("sede de pruebas JUnit");
		sede.setRif("J123456789");
		sede.setEmail("falso@email.com.ve");
		sede.setDireccionFisica("direccion falsa");
		sede.setTelefono("02617000000");
		sede.setEstatusRegistro(EstatusRegistro.ACTIVO);
		
		assertEquals(false,catalogo.isOfSede(sede));		
	}
	
	@Test
	public void sedeIsNotOfEmpresa()
	{
		ensamblarDatos();
		
		Empresa empresa = new Empresa();
		empresa.setRif("J987654321");
		empresa.setRazonSocial("Empresa de Pruebas JUnit C.A.");
		empresa.setDireccionFiscal("direccion fiscal falsa");
		empresa.setEstatusRegistro(EstatusRegistro.ACTIVO);		
		assertEquals(false,catalogo.getSede().isOfEmpresa(empresa));		
	}
	
	@Test
	public void elementosSetDoesNotDuplicate()
	{
		ensamblarDatos();
		
		ElementoCatalogo elemento3=new ElementoCatalogo();
		elemento3.setId(0);
		elemento3.setNombre("elemento ficticio 1");
		elemento3.setDescripcion("descripcion falsa de objeto");
		elemento3.setPrecio(100.0f);
		elemento3.setEstatus(EstatusCatalogo.DISPONIBLE);		
		elemento3.setEstatusRegistro(EstatusRegistro.ACTIVO);
		elemento3.setCatalogo(catalogo);
		catalogo.getElementos().add(elemento3);
		
		assertEquals(2, catalogo.getElementos().size());
	}
	
	@Test
	public void elementosHashCodeEquals()
	{
		ensamblarDatos();
		
		ElementoCatalogo elemento1=new ElementoCatalogo();
		elemento1.setId(0);
		elemento1.setNombre("elemento ficticio 1");
		elemento1.setDescripcion("descripcion falsa de objeto");
		elemento1.setPrecio(100.0f);
		elemento1.setEstatus(EstatusCatalogo.DISPONIBLE);		
		elemento1.setEstatusRegistro(EstatusRegistro.ACTIVO);
		elemento1.setCatalogo(catalogo);
		
		ElementoCatalogo elemento2=new ElementoCatalogo();
		elemento2.setId(0);
		elemento2.setNombre("elemento ficticio 1");
		elemento2.setDescripcion("descripcion falsa de objeto");
		elemento2.setPrecio(100.0f);
		elemento2.setEstatus(EstatusCatalogo.DISPONIBLE);		
		elemento2.setEstatusRegistro(EstatusRegistro.ACTIVO);
		elemento2.setCatalogo(catalogo);
		
		assertEquals(true, elemento1.hashCode()==elemento2.hashCode());
	}
	
	
	@Test
	public void elementosAreEquals()
	{
		ensamblarDatos();
		
		ElementoCatalogo e1=new ElementoCatalogo();
		e1.setId(0);
		e1.setNombre("elemento ficticio 1");
		e1.setDescripcion("descripcion falsa de objeto");
		e1.setPrecio(100.0f);
		e1.setEstatus(EstatusCatalogo.DISPONIBLE);		
		e1.setEstatusRegistro(EstatusRegistro.ACTIVO);
		e1.setCatalogo(catalogo);
		
		ElementoCatalogo e2=new ElementoCatalogo();
		e2.setId(0);
		e2.setNombre("elemento ficticio 1");
		e2.setDescripcion("descripcion falsa de objeto");
		e2.setPrecio(100.0f);
		e2.setEstatus(EstatusCatalogo.DISPONIBLE);		
		e2.setEstatusRegistro(EstatusRegistro.ACTIVO);
		e2.setCatalogo(catalogo);
		
		ElementoCatalogo e3=new ElementoCatalogo();
		e3.setId(0);
		e3.setNombre("elemento ficticio 1");
		e3.setDescripcion("descripcion falsa de objeto");
		e3.setPrecio(100.0f);
		e3.setEstatus(EstatusCatalogo.DISPONIBLE);		
		e3.setEstatusRegistro(EstatusRegistro.ACTIVO);
		e3.setCatalogo(new Catalogo());
		
		assertEquals(true, e1.equals(e2));
		assertEquals(false, e1.equals(e3));
	}
	
	@Test
	public void catalogosAreEquals()
	{
		ensamblarDatos();
		
		Catalogo c1=new Catalogo();		
		c1=new Catalogo();		
		c1.setId(200);
		c1.setNombre("catalogo de pruebas JUnit");
		c1.setEstatus(EstatusCatalogo.DISPONIBLE);
		c1.setEstatusRegistro(EstatusRegistro.ACTIVO);
		Sede s1=catalogo.getSede();
		c1.setSede(s1);
		
		Catalogo c2=new Catalogo();
		c2=new Catalogo();		
		c2.setId(200);
		c2.setNombre("catalogo de pruebas JUnit");
		c2.setEstatus(EstatusCatalogo.DISPONIBLE);
		c2.setEstatusRegistro(EstatusRegistro.ACTIVO);
		Sede s2=catalogo.getSede();
		c2.setSede(s2);
		
		Catalogo c3=new Catalogo();
		c3=new Catalogo();		
		c3.setId(200);
		c3.setNombre("catalogo de pruebas JUnit3");
		c3.setEstatus(EstatusCatalogo.DISPONIBLE);
		c3.setEstatusRegistro(EstatusRegistro.ACTIVO);
		Sede s3=catalogo.getSede();
		c3.setSede(s3);
		
		Catalogo c4=new Catalogo();
		c4=new Catalogo();		
		c4.setId(200);
		c4.setNombre("catalogo de pruebas JUnit");
		c4.setEstatus(EstatusCatalogo.DISPONIBLE);
		c4.setEstatusRegistro(EstatusRegistro.ACTIVO);
		
		assertEquals(true, c1.equals(c2));
		assertEquals(false, c1.equals(c3));
	}
	

}
