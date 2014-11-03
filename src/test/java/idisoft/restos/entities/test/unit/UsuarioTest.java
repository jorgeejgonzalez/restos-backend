package idisoft.restos.entities.test.unit;

import static org.junit.Assert.*;

import java.util.Set;

import idisoft.restos.entities.TipoUsuario;
import idisoft.restos.entities.Usuario;
import idisoft.restos.util.MensajesEntidades;

import javax.validation.ConstraintViolation;

import org.junit.Test;

public class UsuarioTest {
	
	/* Data para las pruebas
				"jorgegonzalez",
				"algo",
				TipoUsuario.MASTER,
				"jorge@algo.com",
				"jorge",
				"gonzalez",
				"V17230971",
				"san francisco",
				"04140675922"
	 */

	@Test
	public void cedulaIsNull() {
		Usuario u=new Usuario("jorgegonzalez",
				"algoalgo",
				TipoUsuario.MASTER,
				"jorge@algo.com",
				"jorge",
				"gonzalez",
				null,
				"san francisco",
				"04140675922");
		Set<ConstraintViolation<Usuario>> violaciones=u.validarInstancia();
		
		String msgassert="usuario.cedula: "+MensajesEntidades.VALIDACION_VALOR_NULO;
		
		assertEquals(1,violaciones.size());
		assertEquals(msgassert, violaciones.iterator().next().getMessage());
	}
	
	@Test
	public void cedulaIsLessThanSize() {
		Usuario u=new Usuario("jorgegonzalez",
				"algoalgo",
				TipoUsuario.MASTER,
				"jorge@algo.com",
				"jorge",
				"gonzalez",
				"V172309",
				"san francisco",
				"04140675922");
		Set<ConstraintViolation<Usuario>> violaciones=u.validarInstancia();
		
		String msgassert="usuario.cedula: "+MensajesEntidades.VALIDACION_STRING_VALOR_LONGITUD+"8 y 9";
		
		assertEquals(1,violaciones.size());
		assertEquals(msgassert, violaciones.iterator().next().getMessage());
	}
	
	@Test
	public void cedulaIsMoreThanSize() {
		Usuario u=new Usuario("jorgegonzalez",
				"algoalgoalgo",
				TipoUsuario.MASTER,
				"jorge@algo.com",
				"jorge",
				"gonzalez",
				"V172309710",
				"san francisco",
				"04140675922");
		Set<ConstraintViolation<Usuario>> violaciones=u.validarInstancia();
		
		String msgassert="usuario.cedula: "+MensajesEntidades.VALIDACION_STRING_VALOR_LONGITUD+"8 y 9";
		
		assertEquals(1,violaciones.size());
		assertEquals(msgassert, violaciones.iterator().next().getMessage());
	}
	
	@Test
	public void cedulaBeginningIsNotLetter() {
		Usuario u=new Usuario("jorgegonzalez",
				"algoalgoalgo",
				TipoUsuario.MASTER,
				"jorge@algo.com",
				"jorge",
				"gonzalez",
				"17230971",
				"san francisco",
				"04140675922");
		Set<ConstraintViolation<Usuario>> violaciones=u.validarInstancia();
		
		String msgassert="usuario.cedula: "+MensajesEntidades.VALIDACION_STRING_FORMATO_VENEZOLANO;
		
		assertEquals(1,violaciones.size());
		assertEquals(msgassert, violaciones.iterator().next().getMessage());
	}
	
	@Test
	public void cedulaFormatHasLetters() {
		Usuario u=new Usuario("jorgegonzalez",
				"algoalgoalgo",
				TipoUsuario.MASTER,
				"jorge@algo.com",
				"jorge",
				"gonzalez",
				"V1723097 ",
				"san francisco",
				"04140675922");
		Set<ConstraintViolation<Usuario>> violaciones=u.validarInstancia();
		
		String msgassert=MensajesEntidades.ENTIDAD_USUARIO_CEDULA+MensajesEntidades.VALIDACION_STRING_FORMATO_VENEZOLANO;
		
		assertEquals(1,violaciones.size());
		assertEquals(msgassert, violaciones.iterator().next().getMessage());		
		
	}
	
	@Test
	public void loginIsNull()
	{
		Usuario u=new Usuario(null,
				"algoalgoalgo",
				TipoUsuario.MASTER,
				"jorge@algo.com",
				"jorge",
				"gonzalez",
				"V17230971",
				"san francisco",
				"04140675922");
		Set<ConstraintViolation<Usuario>> violaciones=u.validarInstancia();
		
		String msgassert="usuario.login: "+MensajesEntidades.VALIDACION_VALOR_NULO;
		
		assertEquals(1,violaciones.size());
		assertEquals(msgassert, violaciones.iterator().next().getMessage());
		
	}
	
	@Test
	public void loginIsLessThanSize() {
		Usuario u=new Usuario("jorge",
				"algoalgoalgo",
				TipoUsuario.MASTER,
				"jorge@algo.com",
				"jorge",
				"gonzalez",
				"V17230971",
				"san francisco",
				"04140675922");
		Set<ConstraintViolation<Usuario>> violaciones=u.validarInstancia();
		
		String msgassert="usuario.login: "+MensajesEntidades.VALIDACION_STRING_VALOR_LONGITUD+"6 y 20";
		
		assertEquals(1,violaciones.size());
		assertEquals(msgassert, violaciones.iterator().next().getMessage());
	}
	
	@Test
	public void loginIsMoreThanSize() {
		Usuario u=new Usuario("jorgeenriquejosegonzalezalvarado",
				"algoalgoalgo",
				TipoUsuario.MASTER,
				"jorge@algo.com",
				"jorge",
				"gonzalez",
				"V17230971",
				"san francisco",
				"04140675922");
		Set<ConstraintViolation<Usuario>> violaciones=u.validarInstancia();
		
		String msgassert="usuario.login: "+MensajesEntidades.VALIDACION_STRING_VALOR_LONGITUD+"6 y 20";
		
		assertEquals(1,violaciones.size());
		assertEquals(msgassert, violaciones.iterator().next().getMessage());
		
	}
		
	@Test
	public void passwordIsNull()
	{
		Usuario u=new Usuario("jorgegonzalez",
				null,
				TipoUsuario.MASTER,
				"jorge@algo.com",
				"jorge",
				"gonzalez",
				"V17230971",
				"san francisco",
				"04140675922");
		Set<ConstraintViolation<Usuario>> violaciones=u.validarInstancia();
		
		String msgassert="usuario.password: "+MensajesEntidades.VALIDACION_VALOR_NULO;
		
		assertEquals(1,violaciones.size());
		assertEquals(msgassert, violaciones.iterator().next().getMessage());
	}
	
	@Test
	public void passwordIsLessThanSize() {
		Usuario u=new Usuario("jorgegonzalez",
				"algo",
				TipoUsuario.MASTER,
				"jorge@algo.com",
				"jorge",
				"gonzalez",
				"V17230971",
				"san francisco",
				"04140675922");
		Set<ConstraintViolation<Usuario>> violaciones=u.validarInstancia();
		
		String msgassert="usuario.password: "+MensajesEntidades.VALIDACION_STRING_VALOR_LONGITUD+"8 y 20";
		
		assertEquals(1,violaciones.size());
		assertEquals(msgassert, violaciones.iterator().next().getMessage());
	}
	
	@Test
	public void passwordIsMoreThanSize() {
		Usuario u=new Usuario("jorgegonzalez",
				"algoalgoalgoalgoalgoalgo",
				TipoUsuario.MASTER,
				"jorge@algo.com",
				"jorge",
				"gonzalez",
				"V17230971",
				"san francisco",
				"04140675922");
		Set<ConstraintViolation<Usuario>> violaciones=u.validarInstancia();
		
		String msgassert="usuario.password: "+MensajesEntidades.VALIDACION_STRING_VALOR_LONGITUD+"8 y 20";
		
		assertEquals(1,violaciones.size());
		assertEquals(msgassert, violaciones.iterator().next().getMessage());
	}

	@Test
	public void tipoIsNull()
	{
		Usuario u=new Usuario("jorgegonzalez",
				"algoalgoalgo",
				null,
				"jorge@algo.com",
				"jorge",
				"gonzalez",
				"V17230971",
				"san francisco",
				"04140675922");
		Set<ConstraintViolation<Usuario>> violaciones=u.validarInstancia();
		
		String msgassert="usuario.tipo: "+MensajesEntidades.VALIDACION_VALOR_NULO;
		
		assertEquals(1,violaciones.size());
		assertEquals(msgassert, violaciones.iterator().next().getMessage());
	}

	@Test
	public void emailIsNull()
	{
		Usuario u=new Usuario("jorgegonzalez",
				"algoalgoalgo",
				TipoUsuario.MASTER,
				null,
				"jorge",
				"gonzalez",
				"V17230971",
				"san francisco",
				"04140675922");
		Set<ConstraintViolation<Usuario>> violaciones=u.validarInstancia();
		
		String msgassert="usuario.email: "+MensajesEntidades.VALIDACION_VALOR_NULO;
		
		assertEquals(1,violaciones.size());
		assertEquals(msgassert, violaciones.iterator().next().getMessage());
	}
	
	@Test
	public void emailIsNotFormatted()
	{
		Usuario u=new Usuario("jorgegonzalez",
				"algoalgoalgo",
				TipoUsuario.MASTER,
				"jorge",
				"jorge",
				"gonzalez",
				"V17230971",
				"san francisco",
				"04140675922");
		Set<ConstraintViolation<Usuario>> violaciones=u.validarInstancia();
		
		String msgassert="usuario.email: "+MensajesEntidades.VALIDACION_STRING_FORMATO_EMAIL;
		
		assertEquals(1,violaciones.size());
		assertEquals(msgassert, violaciones.iterator().next().getMessage());
	}

	@Test
	public void nombreIsNull()
	{
		Usuario u=new Usuario("jorgegonzalez",
				"algoalgoalgo",
				TipoUsuario.MASTER,
				"jorge@algo.com",
				null,
				"gonzalez",
				"V17230971",
				"san francisco",
				"04140675922");
		Set<ConstraintViolation<Usuario>> violaciones=u.validarInstancia();
		
		String msgassert="usuario.nombre: "+MensajesEntidades.VALIDACION_VALOR_NULO;
		
		assertEquals(1,violaciones.size());
		assertEquals(msgassert, violaciones.iterator().next().getMessage());
	}
	
	@Test
	public void apellidoIsNull()
	{
		Usuario u=new Usuario("jorgegonzalez",
				"algoalgoalgo",
				TipoUsuario.MASTER,
				"jorge@algo.com",
				"jorge",
				null,
				"V17230971",
				"san francisco",
				"04140675922");
		Set<ConstraintViolation<Usuario>> violaciones=u.validarInstancia();
		
		String msgassert="usuario.apellido: "+MensajesEntidades.VALIDACION_VALOR_NULO;
		
		assertEquals(1,violaciones.size());
		assertEquals(msgassert, violaciones.iterator().next().getMessage());
	}
	
	@Test 
	public void telefonoIsNull()
	{
		Usuario u=new Usuario("jorgegonzalez",
				"algoalgoalgo",
				TipoUsuario.MASTER,
				"jorge@algo.com",
				"jorge",
				"gonzalez",
				"V17230971",
				"san francisco",
				null);
		Set<ConstraintViolation<Usuario>> violaciones=u.validarInstancia();
		
		String msgassert="usuario.telefono: "+MensajesEntidades.VALIDACION_VALOR_NULO;
		
		assertEquals(1,violaciones.size());
		assertEquals(msgassert, violaciones.iterator().next().getMessage());
	}

	@Test
	public void telefonoIsLessThanSize()
	{
		Usuario u=new Usuario("jorgegonzalez",
				"algoalgoalgo",
				TipoUsuario.MASTER,
				"jorge@algo.com",
				"jorge",
				"gonzalez",
				"V17230971",
				"san francisco",
				"0414067592");
		Set<ConstraintViolation<Usuario>> violaciones=u.validarInstancia();
		
		String msgassert="usuario.telefono: "+MensajesEntidades.VALIDACION_STRING_VALOR_LONGITUD+"11 y 11";
		
		assertEquals(1,violaciones.size());
		assertEquals(msgassert, violaciones.iterator().next().getMessage());
	}
	
	@Test
	public void telefonoIsMoreThanSize()
	{
		Usuario u=new Usuario("jorgegonzalez",
				"algoalgoalgo",
				TipoUsuario.MASTER,
				"jorge@algo.com",
				"jorge",
				"gonzalez",
				"V17230971",
				"san francisco",
				"041406759222");
		Set<ConstraintViolation<Usuario>> violaciones=u.validarInstancia();
		
		String msgassert="usuario.telefono: "+MensajesEntidades.VALIDACION_STRING_VALOR_LONGITUD+"11 y 11";
		
		assertEquals(1,violaciones.size());
		assertEquals(msgassert, violaciones.iterator().next().getMessage());
	}
	
	@Test
	public void telefonoIsNotFormatted()
	{
		Usuario u=new Usuario("jorgegonzalez",
				"algoalgoalgo",
				TipoUsuario.MASTER,
				"jorge@algo.com",
				"jorge",
				"gonzalez",
				"V17230971",
				"san francisco",
				"0414 675922");
		
		Set<ConstraintViolation<Usuario>> violaciones=u.validarInstancia();
		
		String msgassert="usuario.telefono: "+MensajesEntidades.VALIDACION_STRING_VALOR_NUMERICO;
		
		assertEquals(1,violaciones.size());
		assertEquals(msgassert, violaciones.iterator().next().getMessage());
	}
	
}
