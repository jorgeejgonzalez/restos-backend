package idisoft.restos.rest;

import idisoft.restos.data.CatalogosRepository;
import idisoft.restos.data.EntitiesFactory;
import idisoft.restos.data.EntitiesFactoryJSON;
import idisoft.restos.data.PedidoRepository;
import idisoft.restos.data.UsuarioRepository;
import idisoft.restos.entities.ElementoCatalogo;
import idisoft.restos.entities.EstatusPedido;
import idisoft.restos.entities.EstatusRegistro;
import idisoft.restos.entities.Pedido;
import idisoft.restos.entities.Usuario;
import idisoft.restos.entities.json.PedidoJSON;
import idisoft.restos.entities.json.UsuarioJSON;
import idisoft.restos.services.UsuarioRegistry;
import idisoft.restos.util.ConstantesEntidades;
import idisoft.restos.util.ConstantesREST;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
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
	private PedidoRepository repositorioPedidos;
	
	@Inject
	private CatalogosRepository repositorioCatalogos;
	
	@Inject
	private UsuarioRegistry registro;
	
	@Inject 
	private EntitiesFactory factory;
	
	@Inject 
	private EntitiesFactoryJSON factoryJSON;
	
	
	private List<UsuarioJSON> usuariosParseJSON(List<Usuario> usuarios)
	{
		return factoryJSON.parseListaUsuarios(usuarios);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_USUARIOS_FUNCION_LISTAR)
	public Response listarUsuariosActivos()
	{
		Response.ResponseBuilder builder = null;
		try
		{
			List<UsuarioJSON> usuarios= usuariosParseJSON(repositorio.findAllActive());
			
			if(usuarios.isEmpty())
			{
				throw new NoResultException(ConstantesEntidades.MENSAJE_LISTA_VACIA);
			}
			builder=builderProvider(Status.OK);
			builder.entity(usuarios);
		}
		catch(NoResultException ex)
		{
			String msg=ConstantesREST.REST_USUARIOS+ConstantesREST.REST_USUARIOS_FUNCION_LISTAR+": "+ex.getMessage();
			builder=builderProvider(Status.NO_CONTENT);
			builder.entity(msg);
			logger.warning(msg);
		}
		return builder.build();
	}
	
		
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_USUARIOS_FUNCION_LISTAR_INACTIVOS)
	public Response listarUsuariosInActivos()
	{
		Response.ResponseBuilder builder = null;
		try
		{
			List<UsuarioJSON> usuarios= usuariosParseJSON(repositorio.findAllInactive());
			
			if(usuarios.isEmpty())
			{
				throw new NoResultException(ConstantesEntidades.MENSAJE_LISTA_VACIA);
			}
			builder=builderProvider(Status.OK);
			builder.entity(usuarios);
		}
		catch(NoResultException ex)
		{
			String msg=ConstantesREST.REST_USUARIOS+ConstantesREST.REST_USUARIOS_FUNCION_LISTAR_INACTIVOS+": "+ex.getMessage();
			builder=builderProvider(Status.NO_CONTENT);
			builder.entity(msg);
			logger.warning(msg);
		}
		return builder.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_USUARIOS_FUNCION_LISTAR_ELIMINADOS)
	public Response listarUsuariosEliminados()
	{
		Response.ResponseBuilder builder = null;
		try
		{
			List<UsuarioJSON> usuarios= usuariosParseJSON(repositorio.findAllDeleted());
			
			if(usuarios.isEmpty())
			{
				throw new NoResultException(ConstantesEntidades.MENSAJE_LISTA_VACIA);
			}
			builder=builderProvider(Status.OK);
			builder.entity(usuarios);
		}
		catch(NoResultException ex)
		{
			String msg=ConstantesREST.REST_USUARIOS+ConstantesREST.REST_USUARIOS_FUNCION_LISTAR_ELIMINADOS+": "+ex.getMessage();
			builder=builderProvider(Status.NO_CONTENT);
			builder.entity(msg);
			logger.warning(msg);
		}
		return builder.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_USUARIOS_FUNCION_LOGIN)
	public Response realizarLogin(Usuario usuario)
	{
		Response.ResponseBuilder builder = null;
		
		try
		{
			Usuario	retorno=repositorio.findByLogin(usuario.getLogin());
			
			if(usuario.getPassword().equals(retorno.getPassword()))
			{
				UsuarioJSON retornoJSON=factoryJSON.parseUsuario(retorno);
				
				builder=this.builderProvider(Status.OK,MediaType.APPLICATION_JSON);
				builder.entity(retornoJSON);
			}
			else
			{
				String msg=ConstantesREST.REST_USUARIOS+ConstantesREST.REST_USUARIOS_FUNCION_LOGIN+": "+ConstantesREST.REST_USUARIOS_MENSAJE_LOGIN_FALLIDO;
				builder=this.builderProvider(Status.UNAUTHORIZED);
				builder.entity(msg);				
			}
		}
		catch(NoResultException ex)
		{
			String msg=ConstantesREST.REST_USUARIOS+ConstantesREST.REST_USUARIOS_FUNCION_LOGIN+": "+ex.getMessage();
			builder=this.builderProvider(Status.NOT_FOUND);		
			builder.entity(msg);
			logger.severe(msg);
		}
		
		return builder.build();
	}
	
	public Response busquedaCedula(String cedula, EstatusRegistro estatus)
	{
		Response.ResponseBuilder builder = null;
		
		try
		{
			Usuario	usuario=repositorio.findByCedula(cedula);
			
			if(usuario.getEstatusRegistro()==estatus)
			{
				UsuarioJSON usuariojson=factoryJSON.parseUsuario(usuario);				
				builder=this.builderProvider(Status.OK);
				builder.entity(usuariojson);
			}
			else
			{
				throw new NoResultException(ConstantesREST.REST_MENSAJE_ENTIDAD_NULA);				
			}
		}
		catch(NoResultException ex)
		{
			String msg=ConstantesREST.REST_USUARIOS+ConstantesREST.REST_USUARIOS_FUNCION_BUSCAR+": "+ex.getMessage();
			builder=this.builderProvider(Status.NOT_FOUND, MediaType.APPLICATION_JSON);		
			builder.entity(msg);
			logger.warning(msg);;
		}
		
		return builder.build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_USUARIOS_FUNCION_BUSCAR)
	public Response busquedaActivo(@PathParam("cedula") String cedula)
	{
		return busquedaCedula(cedula, EstatusRegistro.ACTIVO);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_USUARIOS_FUNCION_DISPONIBILIDAD_EMAIL)
	public Response disponibilidadEmail(Usuario usuario)
	{
		Response.ResponseBuilder builder = null;
		
		try
		{
			Usuario retorno=repositorio.findByEmail(usuario.getEmail());
			builder=this.builderProvider(Status.CONFLICT, MediaType.APPLICATION_JSON);
			builder.entity(ConstantesREST.REST_USUARIOS_MENSAJE_EMAIL_DUPLICADO+": "+retorno.getEmail());
		}
		catch(NoResultException ex)
		{
			builder=this.builderProvider(Status.OK, MediaType.APPLICATION_JSON);
			builder.entity(ConstantesREST.REST_USUARIOS_MENSAJE_LOGIN_DISPONIBLE);
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
		
		try
		{
			Usuario retorno=repositorio.findByLogin(usuario.getLogin());
			builder=this.builderProvider(Status.CONFLICT, MediaType.APPLICATION_JSON);
			builder.entity(ConstantesREST.REST_USUARIOS_MENSAJE_LOGIN_DUPLICADO+": "+retorno.getLogin());
		}
		catch(NoResultException ex)
		{
			builder=this.builderProvider(Status.OK, MediaType.APPLICATION_JSON);
			builder.entity(ConstantesREST.REST_USUARIOS_MENSAJE_LOGIN_DISPONIBLE);
		}
		
		return builder.build();
	
	}

	public Response crearUsuario(Usuario usuario)
	{
		Response.ResponseBuilder builder = null;
		
		Set<ConstraintViolation<Usuario>> violaciones=factory.validarUsuario(usuario);
		
		if(violaciones.isEmpty())
		{
			Usuario validador=null;
			try
			{
				validador=repositorio.findByCedula(usuario.getCedula());
				String msg= ConstantesREST.REST_MENSAJE_ENTIDAD_DUPLICADA+": "+validador.getCedula();
				builder=this.builderProvider(Status.CONFLICT);
				builder.entity(msg);
				return builder.build();
			}
			catch(NoResultException ex)
			{
				validador=null;
			}
			
			try
			{
				validador=repositorio.findByLogin(usuario.getLogin());
				String msg= ConstantesREST.REST_USUARIOS_MENSAJE_LOGIN_DUPLICADO+": "+validador.getLogin();
				builder=this.builderProvider(Status.CONFLICT);
				builder.entity(msg);
				return builder.build();
			}
			catch(NoResultException ex)
			{
				validador=null;
			}
			
			try
			{
				validador=repositorio.findByEmail(usuario.getEmail());
				String msg= ConstantesREST.REST_USUARIOS_MENSAJE_EMAIL_DUPLICADO+": "+validador.getEmail();
				builder=this.builderProvider(Status.CONFLICT);
				builder.entity(msg);
				return builder.build();
			}
			catch(NoResultException ex)
			{
				validador=null;
			}
			
			try
			{
				UsuarioJSON usuariojson=factoryJSON.parseUsuario(usuario);			
				builder = this.builderProvider(Status.OK);
				builder.entity(usuariojson);				
			}
			catch(Exception ex)
			{
				String msg= ConstantesREST.REST_MENSAJE_EXCEPCION_GENERICA+ ex.getMessage();
				logger.log(Level.SEVERE,msg);					
				builder=this.builderProvider(Status.INTERNAL_SERVER_ERROR);				
			}
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
			
			builder=this.builderProvider(Status.INTERNAL_SERVER_ERROR);
			builder.entity(msgs);			
		}
		
		return builder.build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_USUARIOS_FUNCION_CREAR)
	public Response crearUsuarioFinal(Usuario usuario)
	{
		Usuario nuevo=factory.crearUsuarioFinal(usuario);
		
		return crearUsuario(nuevo);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_USUARIOS_FUNCION_ACTUALIZAR)
	public Response actualizarUsuario(@PathParam("cedula") String cedula, Usuario usuario)
	{
		Response.ResponseBuilder builder = null;		
		Set<ConstraintViolation<Usuario>> violaciones=factory.validarUsuario(usuario);
		
		if(violaciones.isEmpty())
		{
			try
			{
				Usuario retorno=repositorio.findByCedula(cedula);
				UsuarioJSON retornoJSON=factoryJSON.parseUsuario(registro.modificar(retorno, usuario));
				builder=this.builderProvider(Status.OK);
				builder.entity(retornoJSON);
			}
			catch(NoResultException ex)
			{
				String msg=ConstantesREST.REST_USUARIOS+ConstantesREST.REST_USUARIOS_FUNCION_ACTIVAR+": "+ex.getMessage();
				builder= this.builderProvider(Status.NOT_FOUND);
				builder.entity(msg);
			}
			catch(Exception ex)
			{
				String msg=ConstantesREST.REST_MENSAJE_EXCEPCION_GENERICA+ ex.getMessage();
				logger.severe(msg);
				builder=this.builderProvider(Status.INTERNAL_SERVER_ERROR);
			}
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
			builder=this.builderProvider(Status.INTERNAL_SERVER_ERROR);
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

		try
		{
			Usuario retorno=repositorio.findByCedula(cedula);
			UsuarioJSON retornoJSON=factoryJSON.parseUsuario(registro.modificarPassword(retorno, usuario));
			builder=this.builderProvider(Status.OK);
			builder.entity(retornoJSON);
		}
		catch(NoResultException ex)
		{
			String msg=ConstantesREST.REST_USUARIOS+ConstantesREST.REST_USUARIOS_FUNCION_ACTIVAR+": "+ex.getMessage();
			builder= this.builderProvider(Status.NOT_FOUND);
			builder.entity(msg);
		}
		catch(Exception ex)
		{
			String msg=ConstantesREST.REST_MENSAJE_EXCEPCION_GENERICA+ ex.getMessage();
			logger.severe(msg);
			builder=this.builderProvider(Status.INTERNAL_SERVER_ERROR);
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
		
		try
		{
			Usuario usuario=repositorio.findByCedula(cedula);
			
			if(usuario.getEstatusRegistro()==EstatusRegistro.ELIMINADO)
			{
				throw new NoResultException(ConstantesREST.REST_MENSAJE_ENTIDAD_NULA);
			}
			
			usuario=registro.eliminar(usuario);
			if(usuario.getEstatusRegistro()==EstatusRegistro.ELIMINADO)
			{
				msg=ConstantesREST.REST_USUARIOS+ConstantesREST.REST_USUARIOS_FUNCION_ACTIVAR+": "+ConstantesREST.REST_MENSAJE_ENTIDAD_ELIMINADA;
				builder=this.builderProvider(Status.OK);
				builder.entity(msg);
			}
			else
			{
				throw new EntityExistsException(ConstantesREST.REST_MENSAJE_ENTIDAD_INTACTA);
			}
		}
		catch(EntityExistsException ex)
		{
			msg=ConstantesREST.REST_USUARIOS+ConstantesREST.REST_USUARIOS_FUNCION_ACTIVAR+": "+ex.getMessage();
			builder=this.builderProvider(Status.NOT_MODIFIED);
			builder.entity(msg);
		}
		catch(NoResultException ex)
		{
			msg=ConstantesREST.REST_USUARIOS+ConstantesREST.REST_USUARIOS_FUNCION_ELIMINAR+": "+ex.getMessage();
			builder=this.builderProvider(Status.NOT_FOUND);
			builder.entity(msg);
		}
		catch(Exception ex)
		{
			msg=ConstantesREST.REST_USUARIOS+ConstantesREST.REST_USUARIOS_FUNCION_ELIMINAR+": "+ConstantesREST.REST_MENSAJE_EXCEPCION_GENERICA+ex.getMessage();
			builder=this.builderProvider(Status.INTERNAL_SERVER_ERROR);
			logger.severe(msg);
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
		try
		{
			Usuario retorno=repositorio.findByCedula(cedula);
			
			if(retorno.getEstatusRegistro()==EstatusRegistro.ACTIVO)
			{
				throw new EntityExistsException(ConstantesREST.REST_MENSAJE_ENTIDAD_INTACTA);
			}
			else if(retorno.getEstatusRegistro()==EstatusRegistro.ELIMINADO)
			{
				throw new NoResultException(ConstantesREST.REST_MENSAJE_ENTIDAD_NULA);
			}
			else
			{
				UsuarioJSON usuariojson=factoryJSON.parseUsuario(registro.actualizar(retorno));
				builder=this.builderProvider(Status.OK);
				builder.entity(usuariojson);
			}
		}		
		catch(EntityExistsException ex)
		{
			msg=ConstantesREST.REST_USUARIOS+ConstantesREST.REST_USUARIOS_FUNCION_ACTIVAR+": "+ex.getMessage();
			builder=this.builderProvider(Status.NOT_MODIFIED);
			builder.entity(msg);
		}
		catch(NoResultException ex)
		{
			msg=ConstantesREST.REST_USUARIOS+ConstantesREST.REST_USUARIOS_FUNCION_ACTIVAR+": "+ex.getMessage();
			builder=this.builderProvider(Status.NOT_FOUND);
			builder.entity(msg);
		}
		catch(Exception ex)
		{
			msg=ConstantesREST.REST_USUARIOS+ConstantesREST.REST_USUARIOS_FUNCION_ACTIVAR+": "+ConstantesREST.REST_MENSAJE_EXCEPCION_GENERICA+ex.getMessage();
			builder=this.builderProvider(Status.INTERNAL_SERVER_ERROR);
			logger.severe(msg);
			builder.entity(msg);
		}
				
		return builder.build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_USUARIOS_PEDIDOS_FUNCION_NUEVO)
	public Response crearPedido(@PathParam("cedula") String cedula)
	{
		Response.ResponseBuilder builder=null;
		
		String msg="";
		
		Usuario usuario=repositorio.findByCedula(cedula);
		
		if(usuario==null)
		{
			builder=this.builderProvider(Status.NOT_FOUND, MediaType.APPLICATION_JSON);
			msg=ConstantesREST.REST_MENSAJE_ENTIDAD_NULA;
			builder.entity(msg);
		}
		else if(usuario.getEstatusRegistro()==EstatusRegistro.INACTIVO || usuario.getEstatusRegistro()==EstatusRegistro.ELIMINADO)
		{
			builder=this.builderProvider(Status.NOT_FOUND, MediaType.APPLICATION_JSON);
			msg=ConstantesREST.REST_MENSAJE_ENTIDAD_NULA;
			builder.entity(msg);
		}
		else
		
		{
			Pedido pedido=new Pedido();
			
			pedido.setCliente(usuario);
			
			pedido.setDireccionEntrega(usuario.getDireccion());
			pedido.setTelefonoEntrega(usuario.getTelefono());
			
			java.util.Date date=new java.util.Date();
			
			pedido.setFecha(new java.sql.Date(date.getTime()));
			pedido.setHora(new java.sql.Time(date.getTime()));
			
			pedido.setSubTotal(0.0f);
			pedido.setIvaPorcentaje(ConstantesREST.REST_USUARIOS_PEDIDOS_FLOAT_PORCENTAJE_IVA_VALUE);
			pedido.setIvaMonto(0.0f);
			pedido.setTotal(0.0f);
			
			pedido.setEstatus(EstatusPedido.CREADO);
			pedido.setEstatusRegistro(EstatusRegistro.ACTIVO);
			
			try
			{
				pedido=registro.generarPedido(pedido);
				
				usuario.getPedidos().add(pedido);
				
				PedidoJSON pedidojson=new PedidoJSON();
				pedidojson.parsePedidoFromUsuario(pedido);
				
				builder=this.builderProvider(Status.OK, MediaType.APPLICATION_JSON);
				builder.entity(pedidojson);
			}
			catch(Exception ex)
			{
				msg=ConstantesREST.REST_MENSAJE_EXCEPCION_GENERICA+ex.getMessage();
				builder=this.builderProvider(Status.INTERNAL_SERVER_ERROR, MediaType.APPLICATION_JSON);
				builder.entity(msg);
			}
		}		
		
		return builder.build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_USUARIOS_PEDIDOS_FUNCION_PROCESAR)
	public Response procesarPedido(@PathParam("cedula") String cedula, @PathParam("id") int pedidoid, Pedido pedido)
	{
		Response.ResponseBuilder builder=null;
		
		String msg="";
		
		Usuario usuario=repositorio.findByCedula(cedula);
		Pedido actualizar=repositorioPedidos.findById(pedidoid);
		
		if(usuario==null || actualizar==null)
		{
			builder=this.builderProvider(Status.NOT_FOUND, MediaType.APPLICATION_JSON);
			msg=ConstantesREST.REST_MENSAJE_ENTIDAD_NULA;
			builder.entity(msg);
		}
		else if(usuario.getEstatusRegistro()==EstatusRegistro.INACTIVO || usuario.getEstatusRegistro()==EstatusRegistro.ELIMINADO 
				|| actualizar.getEstatusRegistro()==EstatusRegistro.INACTIVO || actualizar.getEstatusRegistro()==EstatusRegistro.ELIMINADO)
		{
			builder=this.builderProvider(Status.NOT_FOUND, MediaType.APPLICATION_JSON);
			msg=ConstantesREST.REST_MENSAJE_ENTIDAD_NULA;
			builder.entity(msg);
		}
		else if(!actualizar.getCliente().getCedula().equals(usuario.getCedula()))
		{
			builder=this.builderProvider(Status.CONFLICT, MediaType.APPLICATION_JSON);
			//msg=ConstantesREST.RESTMENS;
			builder.entity(msg);
		}
		else		
		{
			actualizar.setDireccionEntrega(pedido.getDireccionEntrega());
			actualizar.setTelefonoEntrega(pedido.getTelefonoEntrega());
			
			actualizar.setFecha(pedido.getFecha());
			actualizar.setHora(pedido.getHora());
			
			actualizar.setIvaPorcentaje(pedido.getIvaPorcentaje());
			
			if(actualizar.getElementos()==null)
			{
				actualizar.setSubTotal(0.0f);
				actualizar.setIvaMonto(0.0f);
				actualizar.setTotal(0.0f);
			}
			else if(actualizar.getElementos().isEmpty())
			{
				actualizar.setSubTotal(0.0f);
				actualizar.setIvaMonto(0.0f);
				actualizar.setTotal(0.0f);
			}
			else
			{
				Iterator<ElementoCatalogo> iterator=actualizar.getElementos().iterator();
				float subtotal=0.0f;
				
				while(iterator.hasNext())
				{
					ElementoCatalogo elemento=iterator.next();
					if(elemento.getEstatusRegistro()==EstatusRegistro.ACTIVO)
					{
						subtotal+=elemento.getPrecio();
					}
					
				}
				
				actualizar.setSubTotal(subtotal);
				actualizar.setIvaMonto(subtotal*(pedido.getIvaPorcentaje()/100.0f));
				actualizar.setTotal(subtotal+actualizar.getIvaMonto());
			}
			
			
			actualizar.setEstatus(EstatusPedido.PROCESADO);			
			
			try
			
			{
				actualizar=registro.actualizarPedido(actualizar);
				
				PedidoJSON pedidojson=new PedidoJSON();
				pedidojson.parsePedidoFromUsuario(actualizar);
				
				builder=this.builderProvider(Status.OK, MediaType.APPLICATION_JSON);
				builder.entity(pedidojson);
			}
			catch(Exception ex)
			{
				msg=ConstantesREST.REST_MENSAJE_EXCEPCION_GENERICA+ex.getMessage();
				builder=this.builderProvider(Status.INTERNAL_SERVER_ERROR, MediaType.APPLICATION_JSON);
				builder.entity(msg);
			}
		}		
		
		return builder.build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_USUARIOS_PEDIDOS_ELEMENTOS_FUNCION_ADJUNTAR)
	public Response actualizarPedidoElementoAdjuntar(@PathParam("cedula") String cedula, @PathParam("id") int pedidoid, @PathParam("elemento") int elementoid)
	{
		Response.ResponseBuilder builder=null;
		
		String msg="";
		
		Usuario usuario=repositorio.findByCedula(cedula);
		Pedido pedido=repositorioPedidos.findById(pedidoid);
		ElementoCatalogo elemento=repositorioCatalogos.findElementoById(elementoid);
		
		if(usuario==null || pedido==null|| elemento==null)
		{
			builder=this.builderProvider(Status.CONFLICT, MediaType.APPLICATION_JSON);
			msg=ConstantesREST.REST_MENSAJE_ENTIDAD_NULA;
			builder.entity(msg);
		}
		else if(usuario.getEstatusRegistro()==EstatusRegistro.INACTIVO || usuario.getEstatusRegistro()==EstatusRegistro.ELIMINADO 
				|| pedido.getEstatusRegistro()==EstatusRegistro.INACTIVO || pedido.getEstatusRegistro()==EstatusRegistro.ELIMINADO
				|| elemento.getEstatusRegistro()==EstatusRegistro.INACTIVO || elemento.getEstatusRegistro()==EstatusRegistro.ELIMINADO)
		{
			builder=this.builderProvider(Status.CONFLICT, MediaType.APPLICATION_JSON);
			msg=ConstantesREST.REST_MENSAJE_ENTIDAD_NULA;
			builder.entity(pedido);
		}
		else if(!pedido.isOfUsuario(usuario))
		{
			builder=this.builderProvider(Status.CONFLICT, MediaType.APPLICATION_JSON);
			//msg=ConstantesREST.RESTMENS;
			builder.entity(msg);
		}
		else		
		{
			
			if(pedido.getElementos()==null)
			{
				pedido.setElementos(new HashSet<ElementoCatalogo>(0));
			}
			
			pedido.getElementos().add(elemento);
			
			Iterator<ElementoCatalogo> iterator=pedido.getElementos().iterator();
			float subtotal=0.0f;
			
			while(iterator.hasNext())
			{
				ElementoCatalogo el=iterator.next();
				if(elemento.getEstatusRegistro()==EstatusRegistro.ACTIVO)
				{
					subtotal+=el.getPrecio();
				}
				
			}
			
			pedido.setSubTotal(subtotal);
			pedido.setIvaMonto(subtotal*(pedido.getIvaPorcentaje()/100.0f));
			pedido.setTotal(subtotal+pedido.getIvaMonto());
			
			try			
			{
				pedido=registro.actualizarPedido(pedido);
				
				PedidoJSON pedidojson=new PedidoJSON();
				pedidojson.parsePedidoFromUsuario(pedido);
				
				builder=this.builderProvider(Status.OK, MediaType.APPLICATION_JSON);
				builder.entity(pedidojson);
			}
			catch(Exception ex)
			{
				msg=ConstantesREST.REST_MENSAJE_EXCEPCION_GENERICA+ex.getMessage();
				builder=this.builderProvider(Status.INTERNAL_SERVER_ERROR, MediaType.APPLICATION_JSON);
				builder.entity(msg);
			}
		}		
		
		return builder.build();
	}

}
