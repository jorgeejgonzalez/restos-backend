package idisoft.restos.util;

public abstract class ConstantesEntidades {
	
	/*
	 * La mascara para las claves en el JASON de usuarios
	 */	
	public static final String USUARIO_CLAVE_OCULTA="*********";
	
	/*
	 * Los mensajes que utilizan las entidades al validarse
	 */
	public static final String VALIDACION_VALOR_NULO="valor invalido, no puede ser nulo";	
	public static final String VALIDACION_STRING_VALOR_LONGITUD="valor invalido, la longitud debe ser entre ";
	public static final String VALIDACION_STRING_VALOR_NUMERICO="valor invalido, debe ser solamente numeros";
	public static final String VALIDACION_STRING_FORMATO_VENEZOLANO="valor invalido, debe seguir formato venezolano";
	public static final String VALIDACION_STRING_FORMATO_EMAIL="valor invalido, debe seguir formato de correo electronico";
	
	
	/*
	 * Los campos de las entidades
	 */
	
	/*
	 * Entidad Usuario
	 */	
	private static final String ENTIDAD_USUARIO="usuario.";
	public static final String ENTIDAD_USUARIO_CEDULA=ENTIDAD_USUARIO+"cedula: ";
	public static final String ENTIDAD_USUARIO_LOGIN=ENTIDAD_USUARIO+"login: ";
	public static final String ENTIDAD_USUARIO_PASSWORD=ENTIDAD_USUARIO+"password: ";
	public static final String ENTIDAD_USUARIO_TIPO=ENTIDAD_USUARIO+"tipo: ";
	public static final String ENTIDAD_USUARIO_EMAIL=ENTIDAD_USUARIO+"email: ";
	public static final String ENTIDAD_USUARIO_NOMBRE=ENTIDAD_USUARIO+"nombre: ";
	public static final String ENTIDAD_USUARIO_APELLIDO=ENTIDAD_USUARIO+"apellido: ";
	public static final String ENTIDAD_USUARIO_DIRECCION=ENTIDAD_USUARIO+"direccion: ";
	public static final String ENTIDAD_USUARIO_TELEFONO=ENTIDAD_USUARIO+"telefono: ";	
	
	
	
	/*
	 * Entidad Empresa
	 */
	private static final String ENTIDAD_EMPRESA="empresa.";
	public static final String ENTIDAD_EMPRESA_RIF=ENTIDAD_EMPRESA+"rif: ";
	public static final String ENTIDAD_EMPRESA_RAZON_SOCIAL=ENTIDAD_EMPRESA+"razonSocial: ";
	public static final String ENTIDAD_EMPRESA_DIRECCION_FISCAL=ENTIDAD_EMPRESA+"direccionFiscal: ";
	public static final String ENTIDAD_EMPRESA_SEDES=ENTIDAD_EMPRESA+"sedes: ";
	
		
	/*
	 * Entidad Sede
	 */	
	private static final String ENTIDAD_SEDE="sede.";
	public static final String ENTIDAD_SEDE_ID=ENTIDAD_SEDE+"id: ";
	public static final String ENTIDAD_SEDE_RIF=ENTIDAD_SEDE+"rif: ";
	public static final String ENTIDAD_SEDE_NOMBRE=ENTIDAD_SEDE+"nombre: ";
	public static final String ENTIDAD_SEDE_EMAIL=ENTIDAD_SEDE+"email: ";
	public static final String ENTIDAD_SEDE_DIRECCION_FISICA=ENTIDAD_SEDE+"direccionFisica: ";
	public static final String ENTIDAD_SEDE_TELEFONO=ENTIDAD_SEDE+"telefono: ";
	public static final String ENTIDAD_SEDE_EMPRESA=ENTIDAD_SEDE+"empresa: ";
	public static final String ENTIDAD_SEDE_CATALOGOS=ENTIDAD_SEDE+"catalogos: ";
	
		
	/*
	 * Entidad Catalogo
	 */
	private static final String ENTIDAD_CATALOGO="catalogo.";
	public static final String ENTIDAD_CATALOGO_ID=ENTIDAD_CATALOGO+"id: ";
	public static final String ENTIDAD_CATALOGO_NOMBRE=ENTIDAD_CATALOGO+"nombre: ";
	public static final String ENTIDAD_CATALOGO_ESTATUS=ENTIDAD_CATALOGO+"estatus: ";
	public static final String ENTIDAD_CATALOGO_SEDE=ENTIDAD_CATALOGO+"sede: ";
	public static final String ENTIDAD_CATALOGO_ELEMENTOS=ENTIDAD_CATALOGO+"elementos: ";
	
	/*
	 * Entidad ElementoCatalogo
	 */
	
	private static final String ENTIDAD_ELEMENTOCATALOGO="elementocatalogo.";
	public static final String ENTIDAD_ELEMENTOCATALOGO_ID=ENTIDAD_ELEMENTOCATALOGO+"id: ";
	public static final String ENTIDAD_ELEMENTOCATALOGO_NOMBRE=ENTIDAD_ELEMENTOCATALOGO+"nombre: ";
	public static final String ENTIDAD_ELEMENTOCATALOGO_DESCRIPCION=ENTIDAD_ELEMENTOCATALOGO+"descripcion: ";
	public static final String ENTIDAD_ELEMENTOCATALOGO_PRECIO=ENTIDAD_ELEMENTOCATALOGO+"precio: ";
	public static final String ENTIDAD_ELEMENTOCATALOGO_ESTATUS=ENTIDAD_ELEMENTOCATALOGO+"estatus: ";
	public static final String ENTIDAD_ELEMENTOCATALOGO_CATALOGO=ENTIDAD_ELEMENTOCATALOGO+"catalogo: ";
	
	/*
	 * Entidad Pedido
	 */
	private static final String ENTIDAD_PEDIDO="pedido.";
	public static final String ENTIDAD_PEDIDO_ID=ENTIDAD_PEDIDO+"id: ";
	public static final String ENTIDAD_PEDIDO_CLIENTE=ENTIDAD_PEDIDO+"cliente: ";
	public static final String ENTIDAD_PEDIDO_DIRECCION_ENTREGA=ENTIDAD_PEDIDO+"direccionEntrega: ";
	public static final String ENTIDAD_PEDIDO_TELEFONO_ENTREGA=ENTIDAD_PEDIDO+"telefonoEntrega: ";
	public static final String ENTIDAD_PEDIDO_FECHA=ENTIDAD_PEDIDO+"fecha: ";
	public static final String ENTIDAD_PEDIDO_HORA=ENTIDAD_PEDIDO+"direccionEntrega: ";
	public static final String ENTIDAD_PEDIDO_SUBTOTAL=ENTIDAD_PEDIDO+"subTotal: ";
	public static final String ENTIDAD_PEDIDO_IVA_PORCENTAJE=ENTIDAD_PEDIDO+"ivaPorcentaje: ";
	public static final String ENTIDAD_PEDIDO_IVA_MONTO=ENTIDAD_PEDIDO+"ivaMonto: ";
	public static final String ENTIDAD_PEDIDO_TOTAL=ENTIDAD_PEDIDO+"total: ";
	public static final String ENTIDAD_PEDIDO_ESTATUS=ENTIDAD_PEDIDO+"estatus: ";
	public static final String ENTIDAD_PEDIDO_ELEMENTOS=ENTIDAD_PEDIDO+"elementos: ";
	
	
	/*
	 * Valores Primos para la generacion de los HashCodes
	 */
	public static final int ENTIDAD_USUARIO_HASHCODE_PRIME=31;
	public static final int ENTIDAD_EMPRESA_HASHCODE_PRIME=109;
	public static final int ENTIDAD_SEDE_HASHCODE_PRIME=223;
	public static final int ENTIDAD_CATALOGO_HASHCODE_PRIME=311;
	public static final int ENTIDAD_ELEMENTOCATALOGO_HASHCODE_PRIME=421;
	public static final int ENTIDAD_PEDIDO_HASHCODE_PRIME=797;
	
	/*
	 * Mensajes genericos para el uso de mensajes en log
	 */
	public static final String MENSAJE_EXCEPCION_GENERICA="Se disparo una excepcion: ";
	public static final String MENSAJE_ENTIDAD_REGISTRADA="Registro agregado exitosamente";	
	public static final String MENSAJE_ENTIDAD_ACTUALIZADA="Registro actualizado exitosamente";
	public static final String MENSAJE_ENTIDAD_ACTIVADA="Registro activado exitosamente";
	public static final String MENSAJE_ENTIDAD_ELIMINADA="Registro eliminado exitosamente";
	public static final String MENSAJE_ENTIDAD_INTACTA="Registro no se modifico";
	public static final String MENSAJE_ENTIDAD_NULA="Registro no se encuentra en sistema";
	public static final String MENSAJE_ENTIDAD_DUPLICADA="Registro ya existe en sistema";
	public static final String MENSAJE_LISTA_VACIA="Lista no devolvio valores";
	public static final String MENSAJE_LISTA_NULA="Lista de valores es nula";


}
