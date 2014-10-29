package idisoft.restos.util;

public class ConstantesREST {
	
	/*
	 * Entorno REST
	 */
	
	public static final String REST_ENTORNO="/rest";
	
	/*
	 * Headers para funciones REST
	 */
	
	public static final String REST_HEADER_ACCESS_CONTROL_ALLOW_ORIGIN="Access-Control-Allow-Origin";	
	public static final String REST_HEADER_ACCESS_CONTROL_ALLOW_ORIGIN_VALUE="*";
	public static final String REST_HEADER_ACCESS_CONTROL_ALLOW_HEADERS="Access-Control-Allow-Headers";
	public static final String REST_HEADER_ACCESS_CONTROL_ALLOW_HEADERS_VALUE="origin, conent-type, accept, authorization";
	public static final String REST_HEADER_ACCESS_CONTROL_ALLOW_CREDENTIALS="Access-Control-Allow-Credentials";
	public static final String REST_HEADER_ACCESS_CONTROL_ALLOW_CREDENTIALS_VALUE="true";
	public static final String REST_HEADER_ACCESS_CONTROL_ALLOW_METHODS="Access-Control-Allow-Methods";
	public static final String REST_HEADER_ACCESS_CONTROL_ALLOW_METHODS_VALUE="GET, POST, PUT, DELETE, OPTIONS, HEAD";
	public static final String REST_HEADER_ACCESS_CONTROL_MAX_AGE="Access-Control-Max-Age";
	public static final String REST_HEADER_ACCESS_CONTROL_MAX_AGE_VALUE="1209600";
	
	
	/*
	 * REST de manejo de usuarios
	 */
	
	public static final String REST_USUARIOS="/usuarios";
	public static final String REST_USUARIOS_FUNCION_LISTAR="/";
	public static final String REST_USUARIOS_FUNCION_LISTAR_INACTIVOS=REST_USUARIOS_FUNCION_LISTAR+"inactivos";
	public static final String REST_USUARIOS_FUNCION_LISTAR_ELIMINADOS=REST_USUARIOS_FUNCION_LISTAR+"eliminados";
	public static final String REST_USUARIOS_FUNCION_LOGIN="/entrada";
	public static final String REST_USUARIOS_FUNCION_CREAR="/nuevo";
	public static final String REST_USUARIOS_FUNCION_ACTUALIZAR="/{cedula:[V,E][0-9]*}";
	public static final String REST_USUARIOS_FUNCION_ACTUALIZAR_PASSWORD=REST_USUARIOS_FUNCION_ACTUALIZAR+"/password";
	public static final String REST_USUARIOS_FUNCION_ACTIVAR=REST_USUARIOS_FUNCION_ACTUALIZAR+"/activar";
	public static final String REST_USUARIOS_FUNCION_ELIMINAR="/{cedula:[V,E][0-9]*}";
	public static final String REST_USUARIOS_FUNCION_DISPONIBILIDAD_EMAIL="/disponible/email";
	public static final String REST_USUARIOS_FUNCION_DISPONIBILIDAD_LOGIN="/disponible/login";
	public static final String REST_USUARIOS_MENSAJE_LOGIN_FALLIDO="Autenticacion fallida";
	public static final String REST_USUARIOS_MENSAJE_LOGIN_DISPONIBLE="Login no se encuentra registrado en el sistema";
	public static final String REST_USUARIOS_MENSAJE_LOGIN_DUPLICADO="Login ya se encuentra registrado en el sistema";
	public static final String REST_USUARIOS_MENSAJE_EMAIL_DISPONIBLE="Email no se encuentra registrado en el sistema";
	public static final String REST_USUARIOS_MENSAJE_EMAIL_DUPLICADO="Email ya se encuentra registrado en el sistema";
	
	
	/*
	 * REST de manejo de productos
	 */
	
	public static final String REST_PRODUCTOS="/productos";
	public static final String REST_PRODUCTOS_FUNCION_LISTAR="/";
	public static final String REST_PRODUCTOS_FUNCION_LISTAR_INACTIVOS=REST_PRODUCTOS_FUNCION_LISTAR+"inactivos";
	public static final String REST_PRODUCTOS_FUNCION_LISTAR_ELIMINADOS=REST_PRODUCTOS_FUNCION_LISTAR+"eliminados";
	public static final String REST_PRODUCTOS_FUNCION_CREAR="/nuevo";
	public static final String REST_PRODUCTOS_FUNCION_ACTUALIZAR="/{id:[0-9]*}";
	public static final String REST_PRODUCTOS_FUNCION_ELIMINAR="/{id:[0-9]*}";
	
	public static final String REST_PRODUCTOS_CATEGORIAS="/categorias";
	public static final String REST_PRODUCTOS_CATEGORIAS_FUNCION_LISTAR="/";
	public static final String REST_PRODUCTOS_CATEGORIAS_FUNCION_LISTAR_INACTIVOS=REST_PRODUCTOS_CATEGORIAS_FUNCION_LISTAR+"inactivos";
	public static final String REST_PRODUCTOS_CATEGORIAS_FUNCION_LISTAR_ELIMINADOS=REST_PRODUCTOS_CATEGORIAS_FUNCION_LISTAR+"eliminados";
	
	
	/*
	 * Mensajes genericos para el uso de mensajes en log
	 */
	public static final String REST_MENSAJE_EXCEPCION_GENERICA="Se disparo una excepcion: ";
	public static final String REST_MENSAJE_ENTIDAD_REGISTRADA="Registro agregado exitosamente";
	public static final String REST_MENSAJE_ENTIDAD_ACTUALIZADA="Registro actualizado exitosamente";
	public static final String REST_MENSAJE_ENTIDAD_ELIMINADA="Registro eliminado exitosamente";
	public static final String REST_MENSAJE_ENTIDAD_NULA="Registro no se encuentra en sistema";
	public static final String REST_MENSAJE_ENTIDAD_DUPLICADA="Registro ya existe en sistema";
	public static final String REST_MENSAJE_LISTA_VACIA="Lista no devolvio valores";
	public static final String REST_MENSAJE_LISTA_NULA="Lista de valores es nula";

}

