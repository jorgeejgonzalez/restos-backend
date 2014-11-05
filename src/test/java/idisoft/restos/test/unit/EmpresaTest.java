package idisoft.restos.test.unit;

import static org.junit.Assert.*;

import java.util.Set;

import idisoft.restos.entities.Empresa;
import idisoft.restos.util.ConstantesEntidades;

import javax.validation.ConstraintViolation;

import org.junit.Test;

public class EmpresaTest {
	
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
		
		String msg=ConstantesEntidades.ENTIDAD_EMPRESA_RIF+ConstantesEntidades.VALIDACION_STRING_VALOR_LONGITUD+"10 y 10";
		
		assertEquals(1,violaciones.size());
		assertEquals(msg, violaciones.iterator().next().getMessage());

	}
	
	@Test
	public void rifIsMoreThanSize() 
	{	
		Empresa r=new Empresa("J1234567890",
				"BIBAS CAFE",
				"Bellas Artes");
		
		Set<ConstraintViolation<Empresa>> violaciones=r.validarInstancia();
		
		String msg=ConstantesEntidades.ENTIDAD_EMPRESA_RIF+ConstantesEntidades.VALIDACION_STRING_VALOR_LONGITUD+"10 y 10";
		
		assertEquals(1,violaciones.size());
		assertEquals(msg, violaciones.iterator().next().getMessage());

	}
	
	@Test
	public void rifBeginningIsNotLetter() 
	{	
		Empresa r=new Empresa("0123456789",
				"BIBAS CAFE",
				"Bellas Artes");
		
		Set<ConstraintViolation<Empresa>> violaciones=r.validarInstancia();
		
		String msg=ConstantesEntidades.ENTIDAD_EMPRESA_RIF+ConstantesEntidades.VALIDACION_STRING_FORMATO_VENEZOLANO;
		
		assertEquals(1,violaciones.size());
		assertEquals(msg, violaciones.iterator().next().getMessage());

	}
	
	@Test
	public void rifBeginningLetterIsNotPermitted() 
	{	
		Empresa r=new Empresa("A123456789",
				"BIBAS CAFE",
				"Bellas Artes");
		
		Set<ConstraintViolation<Empresa>> violaciones=r.validarInstancia();
		
		String msg=ConstantesEntidades.ENTIDAD_EMPRESA_RIF+ConstantesEntidades.VALIDACION_STRING_FORMATO_VENEZOLANO;
		
		assertEquals(1,violaciones.size());
		assertEquals(msg, violaciones.iterator().next().getMessage());

	}
		
	
	@Test
	public void rifIsEmpty() 
	{	
		Empresa r=new Empresa("",
				"BIBAS CAFE",
				"Bellas Artes");		
		
		Set<ConstraintViolation<Empresa>> violaciones=r.validarInstancia();
		
		//String msg=ConstantesEntidades.ENTIDAD_EMPRESA_RIF+ConstantesEntidades.VALIDACION_STRING_VALOR_LONGITUD+"10 y 10";
		
		assertEquals(2,violaciones.size());
		//assertEquals(msg, violaciones.iterator().next().getMessage());	
	}
	
		
	@Test
	public void razonSocialIsEmpty() 
	{	
		Empresa r=new Empresa("J123456789",
				"",
				"Bellas Artes");		
		
		Set<ConstraintViolation<Empresa>> violaciones=r.validarInstancia();
		
		String msg=ConstantesEntidades.ENTIDAD_EMPRESA_RAZON_SOCIAL+ConstantesEntidades.VALIDACION_STRING_VALOR_LONGITUD+"10 y 100";
		
		assertEquals(1,violaciones.size());
		assertEquals(msg, violaciones.iterator().next().getMessage());
	}

	
	
	@Test
	public void direccionFiscalIsEmpty() 
	{	
		Empresa r=new Empresa("J123456789",
				"BIBAS CAFE",
				"");		

		Set<ConstraintViolation<Empresa>> violaciones=r.validarInstancia();
		
		String msg=ConstantesEntidades.ENTIDAD_EMPRESA_DIRECCION_FISCAL+ConstantesEntidades.VALIDACION_STRING_VALOR_LONGITUD+"10 y 100";
		
		assertEquals(1,violaciones.size());
		assertEquals(msg, violaciones.iterator().next().getMessage());
	}
	
	@Test
	public void empresasAreEquals()
	{
		Empresa e1=new Empresa();
		e1.setRif("J123456789");
		e1.setRazonSocial("empresa de prueba");
		e1.setDireccionFiscal("direccion falsa");
		
		
		Empresa e2=new Empresa();
		e2.setRif("J123456789");
		e2.setRazonSocial("empresa de prueba");
		e2.setDireccionFiscal("direccion falsa");
		
		Empresa e3=new Empresa();
		e3.setRif("J123456788");
		e3.setRazonSocial("empresa de prueba 1");
		e3.setDireccionFiscal("direccion falsa");
		
		assertEquals(true, e1.equals(e2));
		assertEquals(false, e1.equals(e3));
		
	}	
}
