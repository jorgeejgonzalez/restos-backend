package idisoft.restos.entities.test.unit;

import static org.junit.Assert.*;

import java.util.Set;
import idisoft.restos.entities.Empresa;
import javax.validation.ConstraintViolation;
import org.junit.Test;

public class RestauranteTest {
	
	/*
	 * Datos para la prueba
	 * 	  
	 * Restaurante r=new Restaurante("J12345678",
				"BIBAS CAFE",
				"Bellas Artes");
	 */
	
	@Test
	public void rifIsLessThanSize() 
	{	
		Empresa r=new Empresa("J12345678",
				"BIBAS CAFE",
				"Bellas Artes");
		
		Set<ConstraintViolation<Empresa>> violaciones=r.validarInstancia();
		
		assertEquals(1,violaciones.size());
		assertEquals("el tamaño tiene que estar entre 10 y 10", violaciones.iterator().next().getMessage());

	}
	
	@Test
	public void rifIsMoreThanSize() 
	{	
		Empresa r=new Empresa("J1234567890",
				"BIBAS CAFE",
				"Bellas Artes");
		
		Set<ConstraintViolation<Empresa>> violaciones=r.validarInstancia();
		
		assertEquals(1,violaciones.size());
		assertEquals("el tamaño tiene que estar entre 10 y 10", violaciones.iterator().next().getMessage());

	}
	
	@Test
	public void rifBeginningIsNotLetter() 
	{	
		Empresa r=new Empresa("0123456789",
				"BIBAS CAFE",
				"Bellas Artes");
		
		Set<ConstraintViolation<Empresa>> violaciones=r.validarInstancia();
		
		assertEquals(1,violaciones.size());
		assertEquals("debe cumplir el formato de rif sin guiones", violaciones.iterator().next().getMessage());

	}
	
	@Test
	public void rifBeginningLetterIsNotPermitted() 
	{	
		Empresa r=new Empresa("A123456789",
				"BIBAS CAFE",
				"Bellas Artes");
		
		Set<ConstraintViolation<Empresa>> violaciones=r.validarInstancia();
		
		assertEquals(1,violaciones.size());
		assertEquals("debe cumplir el formato de rif sin guiones", violaciones.iterator().next().getMessage());

	}
	
	@Test
	public void rifIsNull() 
	{	
		Empresa r=new Empresa(null,
				"BIBAS CAFE",
				"Bellas Artes");
		
		Set<ConstraintViolation<Empresa>> violaciones=r.validarInstancia();
		
		assertEquals(1,violaciones.size());
		assertEquals("no puede ser null", violaciones.iterator().next().getMessage());
	}
	
	@Test
	public void rifIsEmpty() 
	{	
		Empresa r=new Empresa(null,
				"BIBAS CAFE",
				"Bellas Artes");
		r.setRif("");
		
		Set<ConstraintViolation<Empresa>> violaciones=r.validarInstancia();
		
		assertEquals(2,violaciones.size());
		//assertEquals("debe cumplir el formato de rif sin guiones", violaciones.iterator().next().getMessage());
		//assertEquals("el tamaño tiene que estar entre 10 y 10", violaciones.iterator().next().getMessage());		
	}
	
	@Test
	public void razonSocialIsNull() 
	{	
		Empresa r=new Empresa("J123456789",
				null,
				"Bellas Artes");
		
		Set<ConstraintViolation<Empresa>> violaciones=r.validarInstancia();
		
		assertEquals(1,violaciones.size());
		assertEquals("no puede ser null", violaciones.iterator().next().getMessage());
	}
	
	@Test
	public void razonSocialIsEmpty() 
	{	
		Empresa r=new Empresa("J123456789",
				null,
				"Bellas Artes");
		r.setRazonSocial("");
		
		Set<ConstraintViolation<Empresa>> violaciones=r.validarInstancia();
		
		assertEquals(1,violaciones.size());
		assertEquals("el tamaño tiene que estar entre 10 y 100", violaciones.iterator().next().getMessage());
	}

	@Test
	public void direccionFiscalIsNull() 
	{	
		Empresa r=new Empresa("J123456789",
				"BIBAS CAFE",
				null);
		
		Set<ConstraintViolation<Empresa>> violaciones=r.validarInstancia();
		
		assertEquals(1,violaciones.size());
		assertEquals("no puede ser null", violaciones.iterator().next().getMessage());
	}
	
	@Test
	public void direccionFiscalIsEmpty() 
	{	
		Empresa r=new Empresa("J123456789",
				"BIBAS CAFE",
				null);
		r.setDireccionFiscal("");

		Set<ConstraintViolation<Empresa>> violaciones=r.validarInstancia();
		
		assertEquals(1,violaciones.size());
		assertEquals("el tamaño tiene que estar entre 10 y 100", violaciones.iterator().next().getMessage());
	}


}
