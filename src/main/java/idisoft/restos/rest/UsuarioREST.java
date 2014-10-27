package idisoft.restos.rest;

import idisoft.restos.data.UsuarioRepository;
import idisoft.restos.entities.Usuario;
import idisoft.restos.entities.json.UsuarioJSON;
import idisoft.restos.services.UsuarioRegistro;
import idisoft.restos.util.ConstantesREST;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path(ConstantesREST.REST_USUARIOS)
public class UsuarioREST {
	
	@Inject
	private Logger logger;
	
	@Inject
	private UsuarioRepository repositorio;
	
	@Inject
	UsuarioRegistro registro;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_USUARIOS_FUNCION_LISTAR)
	public Response listarUsuarios()
	{
		Response.ResponseBuilder builder = null;
		
		List<Usuario> usuarios=repositorio.findAll();
		
		if(usuarios!=null)
		{
			if(usuarios.size()>0)
			{
				List<UsuarioJSON> usuariosjson=new ArrayList<UsuarioJSON>();
				Iterator<Usuario> iterator=usuarios.iterator();
				
				builder=Response.status(Status.OK);
				
				while(iterator.hasNext())
				{
					UsuarioJSON usj=new UsuarioJSON();
					usj.parseUsuario(iterator.next());
					usuariosjson.add(usj);
				}
				
				builder.entity(usuariosjson);
				
			}
			else
			{
				String msg=ConstantesREST.REST_USUARIOS+ConstantesREST.REST_USUARIOS_FUNCION_LISTAR+": "+ConstantesREST.REST_MENSAJE_LISTA_VACIA;
				builder=Response.status(Status.NOT_FOUND);
				builder.entity(msg);
				logger.log(Level.WARNING,msg);
			}
		}
		else
		{
			String msg=ConstantesREST.REST_USUARIOS+ConstantesREST.REST_USUARIOS_FUNCION_LISTAR+": "+ConstantesREST.REST_MENSAJE_LISTA_NULA;
			builder=Response.status(Status.NOT_FOUND);			
			builder.entity(msg);
			logger.log(Level.SEVERE,msg);
		}
		
		builder.header(ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_ORIGIN, ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_ORIGIN_VALUE);		
		builder.header(ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_HEADERS,ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_HEADERS_VALUE);
        builder.header(ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_CREDENTIALS,ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_CREDENTIALS_VALUE);
        builder.header(ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_METHODS,ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_METHODS_VALUE);
        builder.header(ConstantesREST.REST_HEADER_ACCESS_CONTROL_MAX_AGE,ConstantesREST.REST_HEADER_ACCESS_CONTROL_MAX_AGE_VALUE);
		
		builder.type(MediaType.APPLICATION_JSON);
		
		return builder.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_USUARIOS_FUNCION_LOGIN)
	public Response realizarLogin(Usuario usuario)
	{
		Response.ResponseBuilder builder = null;
		
		Usuario	retorno=repositorio.findByLogin(usuario.getLogin());
		
		if(retorno!=null)
		{
			if(usuario.getPassword().equals(retorno.getPassword()))
			{
				UsuarioJSON retornoJSON=new UsuarioJSON();
				retornoJSON.parseUsuario(retorno);
				
				builder=Response.status(Status.OK);
				builder.entity(retornoJSON);
			}
			else
			{
				String msg=ConstantesREST.REST_USUARIOS+ConstantesREST.REST_USUARIOS_FUNCION_LOGIN+": "+ConstantesREST.REST_USUARIOS_MENSAJE_LOGIN_FALLIDO;
				builder=Response.status(Status.NOT_FOUND);
				builder.entity(msg);
				logger.log(Level.WARNING,msg);
			}
		}
		else
		{
			String msg=ConstantesREST.REST_USUARIOS+ConstantesREST.REST_USUARIOS_FUNCION_LOGIN+": "+ConstantesREST.REST_MENSAJE_ENTIDAD_NULA;
			builder=Response.status(Status.NOT_FOUND);			
			builder.entity(msg);
			logger.log(Level.SEVERE,msg);
		}
		
		builder.header(ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_ORIGIN, ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_ORIGIN_VALUE);		
		builder.header(ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_HEADERS,ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_HEADERS_VALUE);
        builder.header(ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_CREDENTIALS,ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_CREDENTIALS_VALUE);
        builder.header(ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_METHODS,ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_METHODS_VALUE);
        builder.header(ConstantesREST.REST_HEADER_ACCESS_CONTROL_MAX_AGE,ConstantesREST.REST_HEADER_ACCESS_CONTROL_MAX_AGE_VALUE);
		
		builder.type(MediaType.APPLICATION_JSON);
		
		return builder.build();
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_USUARIOS_FUNCION_CREAR)
	public Response crearUsuario(Usuario usuario)
	{
		Response.ResponseBuilder builder = null;
		
		Set<ConstraintViolation<Usuario>> violaciones=usuario.validarInstancia();
		
		if(violaciones.size()==0)
		{
			String msg="";
			
			if(repositorio.findByCedula(usuario.getCedula())!=null)
			{
				msg= ConstantesREST.REST_MENSAJE_ENTIDAD_DUPLICADA;
				builder=Response.status(Status.CONFLICT);
			}
			else if(repositorio.findByEmail(usuario.getEmail())!=null)
			{
				msg= ConstantesREST.REST_USUARIOS_MENSAJE_EMAIL_DUPLICADO;
				builder=Response.status(Status.CONFLICT);
			}
			else
			{
				try
				{
					registro.registrarUsuario(usuario);			
					msg= ConstantesREST.REST_MENSAJE_ENTIDAD_REGISTRADA;
					builder = Response.ok();
				}
				
				catch(Exception ex)
				{
					msg= ConstantesREST.REST_MENSAJE_EXCEPCION_GENERICA+ ex.getMessage();
					logger.log(Level.SEVERE,msg);					
					builder=Response.status(Status.INTERNAL_SERVER_ERROR);
				}
			}
			builder.entity(msg);
		}
		else
		{
			List<String> msgs=new ArrayList<String>();
			Iterator<ConstraintViolation<Usuario>> iterator=violaciones.iterator(); 
			while(iterator.hasNext())
			{
				String msg=iterator.next().getMessage();
				logger.log(Level.SEVERE,msg);
				msgs.add(msg);
			}
			
			builder=Response.status(Status.INTERNAL_SERVER_ERROR);
			builder.entity(msgs);
			
		}
		
		builder.header(ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_ORIGIN, ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_ORIGIN_VALUE);		
		builder.header(ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_HEADERS,ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_HEADERS_VALUE);
        builder.header(ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_CREDENTIALS,ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_CREDENTIALS_VALUE);
        builder.header(ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_METHODS,ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_METHODS_VALUE);
        builder.header(ConstantesREST.REST_HEADER_ACCESS_CONTROL_MAX_AGE,ConstantesREST.REST_HEADER_ACCESS_CONTROL_MAX_AGE_VALUE);
		
		builder.type(MediaType.APPLICATION_JSON);
		
		return builder.build();
	}

}
