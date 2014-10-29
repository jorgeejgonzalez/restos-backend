package idisoft.restos.util;

public abstract class MensajesEntidades {
	
	/*
	 * La mascara para las claves en el JASON de usuarios
	 */	
	public static final String USUARIO_CLAVE_OCULTA="*********";
	
	/*
	 * Los mensajes que utilizan las entidades al validarse
	 */
	public static final String VALIDACION_STRING_VALOR_NULO="valor invalido, no puede ser nulo";	
	public static final String VALIDACION_STRING_VALOR_LONGITUD="valor invalido, la longitud debe ser entre ";
	public static final String VALIDACION_STRING_VALOR_NUMERICO="valor invalido, debe ser solamente numeros";
	public static final String VALIDACION_STRING_FORMATO_VENEZOLANO="valor invalido, debe seguir formato venezolano";
	public static final String VALIDACION_STRING_FORMATO_EMAIL="valor invalido, debe seguir formato de correo electronico";

}
