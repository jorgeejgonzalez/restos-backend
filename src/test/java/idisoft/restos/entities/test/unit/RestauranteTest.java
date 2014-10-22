package idisoft.restos.entities.test.unit;

import static org.junit.Assert.*;

import java.util.Set;
import idisoft.restos.entities.Restaurante;
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
		Restaurante r=new Restaurante("J12345678",
				"BIBAS CAFE",
				"Bellas Artes");
		
		Set<ConstraintViolation<Restaurante>> violaciones=r.validarInstancia();
		
		assertEquals(1,violaciones.size());
		assertEquals("el tamaño tiene que estar entre 10 y 10", violaciones.iterator().next().getMessage());

	}
	
	@Test
	public void rifIsMoreThanSize() 
	{	
		Restaurante r=new Restaurante("J1234567890",
				"BIBAS CAFE",
				"Bellas Artes");
		
		Set<ConstraintViolation<Restaurante>> violaciones=r.validarInstancia();
		
		assertEquals(1,violaciones.size());
		assertEquals("el tamaño tiene que estar entre 10 y 10", violaciones.iterator().next().getMessage());

	}
	
	@Test
	public void rifBeginningIsNotLetter() 
	{	
		Restaurante r=new Restaurante("0123456789",
				"BIBAS CAFE",
				"Bellas Artes");
		
		Set<ConstraintViolation<Restaurante>> violaciones=r.validarInstancia();
		
		assertEquals(1,violaciones.size());
		assertEquals("debe cumplir el formato de rif sin guiones", violaciones.iterator().next().getMessage());

	}
	
	@Test
	public void rifBeginningLetterIsNotPermitted() 
	{	
		Restaurante r=new Restaurante("A123456789",
				"BIBAS CAFE",
				"Bellas Artes");
		
		Set<ConstraintViolation<Restaurante>> violaciones=r.validarInstancia();
		
		assertEquals(1,violaciones.size());
		assertEquals("debe cumplir el formato de rif sin guiones", violaciones.iterator().next().getMessage());

	}
	
	@Test
	public void rifIsNull() 
	{	
		Restaurante r=new Restaurante(null,
				"BIBAS CAFE",
				"Bellas Artes");
		
		Set<ConstraintViolation<Restaurante>> violaciones=r.validarInstancia();
		
		assertEquals(1,violaciones.size());
		assertEquals("no puede ser null", violaciones.iterator().next().getMessage());
	}
	
	@Test
	public void rifIsEmpty() 
	{	
		Restaurante r=new Restaurante(null,
				"BIBAS CAFE",
				"Bellas Artes");
		r.setRif("");
		
		Set<ConstraintViolation<Restaurante>> violaciones=r.validarInstancia();
		
		assertEquals(2,violaciones.size());
		//assertEquals("debe cumplir el formato de rif sin guiones", violaciones.iterator().next().getMessage());
		//assertEquals("el tamaño tiene que estar entre 10 y 10", violaciones.iterator().next().getMessage());		
	}
	
	@Test
	public void razonSocialIsNull() 
	{	
		Restaurante r=new Restaurante("J123456789",
				null,
				"Bellas Artes");
		
		Set<ConstraintViolation<Restaurante>> violaciones=r.validarInstancia();
		
		assertEquals(1,violaciones.size());
		assertEquals("no puede ser null", violaciones.iterator().next().getMessage());
	}
	
	@Test
	public void razonSocialIsEmpty() 
	{	
		Restaurante r=new Restaurante("J123456789",
				null,
				"Bellas Artes");
		r.setRazonSocial("");
		
		Set<ConstraintViolation<Restaurante>> violaciones=r.validarInstancia();
		
		assertEquals(1,violaciones.size());
		assertEquals("el tamaño tiene que estar entre 10 y 100", violaciones.iterator().next().getMessage());
	}

	@Test
	public void direccionFiscalIsNull() 
	{	
		Restaurante r=new Restaurante("J123456789",
				"BIBAS CAFE",
				null);
		
		Set<ConstraintViolation<Restaurante>> violaciones=r.validarInstancia();
		
		assertEquals(1,violaciones.size());
		assertEquals("no puede ser null", violaciones.iterator().next().getMessage());
	}
	
	@Test
	public void direccionFiscalIsEmpty() 
	{	
		Restaurante r=new Restaurante("J123456789",
				"BIBAS CAFE",
				null);
		r.setDireccionFiscal("");

		Set<ConstraintViolation<Restaurante>> violaciones=r.validarInstancia();
		
		assertEquals(1,violaciones.size());
		assertEquals("el tamaño tiene que estar entre 10 y 100", violaciones.iterator().next().getMessage());
	}


}
