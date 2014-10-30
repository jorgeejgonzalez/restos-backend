package idisoft.restos.rest;

import idisoft.restos.data.UsuarioRepository;
import idisoft.restos.entities.EstatusRegistro;
import idisoft.restos.entities.Usuario;
import idisoft.restos.entities.json.UsuarioJSON;
import idisoft.restos.services.UsuarioRegistry;
import idisoft.restos.util.ConstantesREST;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path(ConstantesREST.REST_USUARIOS)
public class UsuarioREST extends RestService{
	
	@Inject
	private UsuarioRepository repositorio;
	
	@Inject
	private UsuarioRegistry registro;
	
	private Response.ResponseBuilder listarUsuarios(List<Usuario> usuarios, String funcion)
	{
		Response.ResponseBuilder builder = null;
		
		if(usuarios!=null)
		{
			if(usuarios.size()>0)
			{
				List<UsuarioJSON> usuariosjson=new ArrayList<UsuarioJSON>();
				Iterator<Usuario> iterator=usuarios.iterator();
				
				builder=this.builderProvider(Status.OK, MediaType.APPLICATION_JSON);
				
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
				String msg=funcion+": "+ConstantesREST.REST_MENSAJE_LISTA_VACIA;
				builder=this.builderProvider(Status.NOT_FOUND,MediaType.APPLICATION_JSON);
				builder.entity(msg);
				logger.log(Level.WARNING,msg);
			}
		}
		else
		{
			String msg=funcion+": "+ConstantesREST.REST_MENSAJE_LISTA_NULA;
			builder=this.builderProvider(Status.NOT_FOUND, MediaType.APPLICATION_JSON);			
			builder.entity(msg);
			logger.log(Level.SEVERE,msg);
		}
		
		return builder;
	
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_USUARIOS_FUNCION_LISTAR)
	public Response listarUsuariosActivos()
	{
		Response.ResponseBuilder builder = null;
		
		List<Usuario> usuarios=repositorio.findAllActive();
		
		builder=listarUsuarios(usuarios, ConstantesREST.REST_USUARIOS+ConstantesREST.REST_USUARIOS_FUNCION_LISTAR);
		
		return builder.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_USUARIOS_FUNCION_LISTAR_INACTIVOS)
	public Response listarUsuariosInActivos()
	{
		Response.ResponseBuilder builder = null;
		
		List<Usuario> usuarios=repositorio.findAllInactive();
		
		builder=listarUsuarios(usuarios, ConstantesREST.REST_USUARIOS+ConstantesREST.REST_USUARIOS_FUNCION_LISTAR_INACTIVOS);
		
		return builder.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_USUARIOS_FUNCION_LISTAR_ELIMINADOS)
	public Response listarUsuariosEliminados()
	{
		Response.ResponseBuilder builder = null;
		
		List<Usuario> usuarios=repositorio.findAllInactive();
		
		builder=listarUsuarios(usuarios, ConstantesREST.REST_USUARIOS+ConstantesREST.REST_USUARIOS_FUNCION_LISTAR_ELIMINADOS);
		
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
				
				builder=this.builderProvider(Status.OK,MediaType.APPLICATION_JSON);
				builder.entity(retornoJSON);
			}
			else
			{
				String msg=ConstantesREST.REST_USUARIOS+ConstantesREST.REST_USUARIOS_FUNCION_LOGIN+": "+ConstantesREST.REST_USUARIOS_MENSAJE_LOGIN_FALLIDO;
				builder=this.builderProvider(Status.NOT_FOUND,MediaType.APPLICATION_JSON);
				builder.entity(msg);
				logger.log(Level.WARNING,msg);
			}
		}
		else
		{
			String msg=ConstantesREST.REST_USUARIOS+ConstantesREST.REST_USUARIOS_FUNCION_LOGIN+": "+ConstantesREST.REST_MENSAJE_ENTIDAD_NULA;
			builder=this.builderProvider(Status.NOT_FOUND, MediaType.APPLICATION_JSON);		
			builder.entity(msg);
			logger.log(Level.SEVERE,msg);
		}
		
		return builder.build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_USUARIOS_FUNCION_DISPONIBILIDAD_EMAIL)
	public Response disponibilidadEmail(Usuario usuario)
	{
		Response.ResponseBuilder builder = null;
		
		if(repositorio.findByEmail(usuario.getEmail())==null)
		{
			builder=this.builderProvider(Status.OK,MediaType.APPLICATION_JSON);
			builder.entity(ConstantesREST.REST_USUARIOS_MENSAJE_EMAIL_DISPONIBLE);
		}
		else
		{
			builder=this.builderProvider(Status.CONFLICT, MediaType.APPLICATION_JSON);
			builder.entity(ConstantesREST.REST_USUARIOS_MENSAJE_EMAIL_DUPLICADO);
		}
		
		return builder.build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_USUARIOS_FUNCION_DISPONIBILIDAD_LOGIN)
	public Response disponibilidadLogin(Usuario usuario)
	{
		Response.ResponseBuilder builder = null;
		
		if(repositorio.findByLogin(usuario.getLogin())==null)
		{
			builder=this.builderProvider(Status.OK, MediaType.APPLICATION_JSON);
			builder.entity(ConstantesREST.REST_USUARIOS_MENSAJE_LOGIN_DISPONIBLE);
		}
		else
		{
			builder=this.builderProvider(Status.CONFLICT, MediaType.APPLICATION_JSON);
			builder.entity(ConstantesREST.REST_USUARIOS_MENSAJE_LOGIN_DUPLICADO);
		}
		
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
				builder=this.builderProvider(Status.CONFLICT, MediaType.APPLICATION_JSON);
			}
			else if(repositorio.findByLogin(usuario.getLogin())!=null)
			{
				msg= ConstantesREST.REST_USUARIOS_MENSAJE_LOGIN_DUPLICADO;
				builder=this.builderProvider(Status.CONFLICT, MediaType.APPLICATION_JSON);
			}
			else if(repositorio.findByEmail(usuario.getEmail())!=null)
			{
				msg= ConstantesREST.REST_USUARIOS_MENSAJE_EMAIL_DUPLICADO;
				builder=this.builderProvider(Status.CONFLICT, MediaType.APPLICATION_JSON);
			}
			else
			{
				try
				{
					usuario.setEstatusRegistro(EstatusRegistro.INACTIVO);
					registro.registrar(usuario);			
					msg= ConstantesREST.REST_MENSAJE_ENTIDAD_REGISTRADA;
					builder = this.builderProvider(Status.OK, MediaType.APPLICATION_JSON);
				}
				
				catch(Exception ex)
				{
					msg= ConstantesREST.REST_MENSAJE_EXCEPCION_GENERICA+ ex.getMessage();
					logger.log(Level.SEVERE,msg);					
					builder=this.builderProvider(Status.INTERNAL_SERVER_ERROR, MediaType.APPLICATION_JSON);
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
			
			builder=this.builderProvider(Status.INTERNAL_SERVER_ERROR, MediaType.APPLICATION_JSON);
			builder.entity(msgs);
			
		}
		
		return builder.build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_USUARIOS_FUNCION_ACTUALIZAR)
	public Response actualizarUsuario(@PathParam("cedula") String cedula, Usuario usuario)
	{
		Response.ResponseBuilder builder = null;
		
		Set<ConstraintViolation<Usuario>> violaciones=usuario.validarInstancia();
		
		if(violaciones.size()==0)
		{
			String msg="";
			Usuario retorno=repositorio.findByCedula(cedula);
			if(retorno==null)
			{
				msg= ConstantesREST.REST_MENSAJE_ENTIDAD_NULA;
				builder= this.builderProvider(Status.NOT_FOUND,MediaType.APPLICATION_JSON);
			}
			else 
			{
				try
				{
					retorno.setNombre(usuario.getNombre());
					retorno.setApellido(usuario.getApellido());
					retorno.setDireccion(usuario.getDireccion());
					retorno.setTelefono(usuario.getTelefono());
					retorno.setTipo(usuario.getTipo());
					retorno.setLogin(usuario.getLogin());
					retorno.setEmail(usuario.getEmail());
					
					registro.actualizar(retorno);			
					
					msg= ConstantesREST.REST_MENSAJE_ENTIDAD_ACTUALIZADA;
					
					builder = this.builderProvider(Status.OK, MediaType.APPLICATION_JSON);
				}
				
				catch(Exception ex)
				{
					msg= ConstantesREST.REST_MENSAJE_EXCEPCION_GENERICA+ ex.getMessage();
				
					logger.log(Level.SEVERE,msg);					
					
					builder=this.builderProvider(Status.INTERNAL_SERVER_ERROR, MediaType.APPLICATION_JSON);
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
			
			builder=this.builderProvider(Status.INTERNAL_SERVER_ERROR, MediaType.APPLICATION_JSON);
			builder.entity(msgs);
			
		}
		
		return builder.build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_USUARIOS_FUNCION_ACTUALIZAR_PASSWORD)
	public Response actualizarPasswordUsuario(@PathParam("cedula") String cedula, Usuario usuario)
	{
		Response.ResponseBuilder builder = null;
		
		Set<ConstraintViolation<Usuario>> violaciones=usuario.validarInstancia();
		
		if(violaciones.size()==0)
		{
			String msg="";
			Usuario retorno=repositorio.findByCedula(cedula);
			if(retorno==null)
			{
				msg= ConstantesREST.REST_MENSAJE_ENTIDAD_NULA;
				builder=this.builderProvider(Status.NOT_FOUND, MediaType.APPLICATION_JSON);
			}
			else 
			{
				try
				{
					retorno.setPassword(usuario.getPassword());
					
					registro.actualizar(retorno);			
					
					msg= ConstantesREST.REST_MENSAJE_ENTIDAD_ACTUALIZADA;
					
					builder = this.builderProvider(Status.OK, MediaType.APPLICATION_JSON);
				}
				
				catch(Exception ex)
				{
					msg= ConstantesREST.REST_MENSAJE_EXCEPCION_GENERICA+ ex.getMessage();
				
					logger.log(Level.SEVERE,msg);					
					
					builder=this.builderProvider(Status.INTERNAL_SERVER_ERROR, MediaType.APPLICATION_JSON);
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
			
			builder=this.builderProvider(Status.INTERNAL_SERVER_ERROR, MediaType.APPLICATION_JSON);
			builder.entity(msgs);
			
		}
		
		return builder.build();
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_USUARIOS_FUNCION_ELIMINAR)
	public Response eliminarUsuario(@PathParam("cedula") String cedula)
	{
		Response.ResponseBuilder builder = null;
		String msg="";
		Usuario retorno=repositorio.findByCedula(cedula);
		if(retorno==null)
		{
			msg= ConstantesREST.REST_MENSAJE_ENTIDAD_NULA;
			builder=this.builderProvider(Status.NOT_FOUND, MediaType.APPLICATION_JSON);
		}
		else if(retorno.getEstatusRegistro()==EstatusRegistro.ELIMINADO)
		{
			msg= ConstantesREST.REST_MENSAJE_ENTIDAD_NULA;
			builder=this.builderProvider(Status.NOT_FOUND, MediaType.APPLICATION_JSON);
		}
		else
		{
			try
			{
				retorno.setEstatusRegistro(EstatusRegistro.ELIMINADO);

				registro.actualizar(retorno);

				msg= ConstantesREST.REST_MENSAJE_ENTIDAD_ELIMINADA;

				builder = this.builderProvider(Status.OK, MediaType.APPLICATION_JSON);

			}
			catch(Exception ex)
			{
				msg= ConstantesREST.REST_MENSAJE_EXCEPCION_GENERICA+ ex.getMessage();
				logger.log(Level.SEVERE,msg);
				builder=this.builderProvider(Status.INTERNAL_SERVER_ERROR, MediaType.APPLICATION_JSON);
				
			}
			
			builder.entity(msg);
			
		}
				
		return builder.build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_USUARIOS_FUNCION_ACTIVAR)
	public Response activarUsuario(@PathParam("cedula") String cedula)
	{
		Response.ResponseBuilder builder = null;
		String msg="";
		Usuario retorno=repositorio.findByCedula(cedula);
		if(retorno==null)
		{
			msg= ConstantesREST.REST_MENSAJE_ENTIDAD_NULA;
			builder=this.builderProvider(Status.NOT_FOUND, MediaType.APPLICATION_JSON);
		}
		else if(retorno.getEstatusRegistro()==EstatusRegistro.ELIMINADO)
		{
			msg= ConstantesREST.REST_MENSAJE_ENTIDAD_NULA;
			builder=this.builderProvider(Status.NOT_FOUND, MediaType.APPLICATION_JSON);
		}
		else
		{
			try
			{
				retorno.setEstatusRegistro(EstatusRegistro.ACTIVO);

				registro.actualizar(retorno);

				msg= ConstantesREST.REST_MENSAJE_ENTIDAD_ACTIVADA;

				builder = this.builderProvider(Status.OK, MediaType.APPLICATION_JSON);

			}
			catch(Exception ex)
			{
				msg= ConstantesREST.REST_MENSAJE_EXCEPCION_GENERICA+ ex.getMessage();
				logger.log(Level.SEVERE,msg);
				builder=this.builderProvider(Status.INTERNAL_SERVER_ERROR, MediaType.APPLICATION_JSON);
				
			}
			
			builder.entity(msg);
			
		}
				
		return builder.build();
	}

}
