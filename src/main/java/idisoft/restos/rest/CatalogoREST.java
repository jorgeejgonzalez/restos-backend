package idisoft.restos.rest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import idisoft.restos.data.CatalogosRepository;
import idisoft.restos.entities.Catalogo;
import idisoft.restos.entities.ElementoCatalogo;
import idisoft.restos.entities.EstatusRegistro;
import idisoft.restos.entities.json.CatalogoJSON;
import idisoft.restos.services.CatalogoRegistry;
import idisoft.restos.util.ConstantesREST;

@Path(ConstantesREST.REST_CATALOGOS)
public class CatalogoREST extends RestService {
	
	@Inject
	private CatalogosRepository repositorio;
	
	@Inject
	private CatalogoRegistry registro;

	
	private Response.ResponseBuilder listarCatalogos(List<Catalogo> catalogos, String funcion)
	{
		Response.ResponseBuilder builder=null;
		if(catalogos!=null)
		{
			if(!catalogos.isEmpty())
			{
				List<CatalogoJSON> catalogosjson=new ArrayList<CatalogoJSON>();
				Iterator<Catalogo> iterator=catalogos.iterator();
				
				builder=this.builderProvider(Status.OK, MediaType.APPLICATION_JSON);
				
				while(iterator.hasNext())
				{
					CatalogoJSON caj=new CatalogoJSON();
					caj.parseCatalogo(iterator.next());
					catalogosjson.add(caj);
				}
				
				builder.entity(catalogosjson);
				
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
	@Path(ConstantesREST.REST_CATALOGOS_FUNCION_LISTAR)
	public Response listarCatalogosActivos()
	{
		List<Catalogo> catalogos=repositorio.findAllActive();
		
		Response.ResponseBuilder builder=listarCatalogos(catalogos, ConstantesREST.REST_CATALOGOS+ConstantesREST.REST_CATALOGOS_FUNCION_LISTAR);
		
		return builder.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_CATALOGOS_FUNCION_LISTAR_INACTIVOS)
	public Response listarCatalogosInactivos()
	{
		List<Catalogo> catalogos=repositorio.findAllInactive();
		
		Response.ResponseBuilder builder=listarCatalogos(catalogos, ConstantesREST.REST_CATALOGOS+ConstantesREST.REST_CATALOGOS_FUNCION_LISTAR_INACTIVOS);
		
		return builder.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_CATALOGOS_FUNCION_LISTAR_ELIMINADOS)
	public Response listarCatalogosEliminados()
	{
		List<Catalogo> catalogos=repositorio.findAllDeleted();
		
		Response.ResponseBuilder builder=listarCatalogos(catalogos, ConstantesREST.REST_CATALOGOS+ConstantesREST.REST_CATALOGOS_FUNCION_LISTAR_ELIMINADOS);
		
		return builder.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_CATALOGOS_SEDE_FUNCION_LISTAR)
	public Response listarCatalogosActivosPorSede(@PathParam("id") int sedeid)
	{
		List<Catalogo> catalogos=repositorio.findAllActiveBySede(sedeid);
		
		Response.ResponseBuilder builder=listarCatalogos(catalogos, ConstantesREST.REST_CATALOGOS+ConstantesREST.REST_CATALOGOS_SEDE_FUNCION_LISTAR);
		
		return builder.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_CATALOGOS_SEDE_FUNCION_LISTAR_INACTIVOS)
	public Response listarCatalogosInactivosPorSede(@PathParam("id") int sedeid)
	{
		List<Catalogo> catalogos=repositorio.findAllInactiveBySede(sedeid);
		
		Response.ResponseBuilder builder=listarCatalogos(catalogos, ConstantesREST.REST_CATALOGOS+ConstantesREST.REST_CATALOGOS_SEDE_FUNCION_LISTAR_INACTIVOS);
		
		return builder.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_CATALOGOS_SEDE_FUNCION_LISTAR_ELIMINADOS)
	public Response listarCatalogosEliminadosPorSede(@PathParam("id") int sedeid)
	{
		List<Catalogo> catalogos=repositorio.findAllActiveBySede(sedeid);
		
		Response.ResponseBuilder builder=listarCatalogos(catalogos, ConstantesREST.REST_CATALOGOS+ConstantesREST.REST_CATALOGOS_SEDE_FUNCION_LISTAR_ELIMINADOS);
		
		return builder.build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_CATALOGOS_FUNCION_CREAR)
	public Response crearCatalogo(Catalogo catalogo)
	{	
		Response.ResponseBuilder builder=null;
		
		Set<ConstraintViolation<Catalogo>> violaciones=catalogo.validarInstancia();
		
		if(violaciones.size()==0)
		{
			String msg="";
			
			if(repositorio.findById(catalogo.getId())!=null)
			{
				msg= ConstantesREST.REST_MENSAJE_ENTIDAD_DUPLICADA;
				builder=this.builderProvider(Status.CONFLICT, MediaType.APPLICATION_JSON);
			}
			else
			{
				try
				{
					catalogo.setEstatusRegistro(EstatusRegistro.ACTIVO);
					registro.registrar(catalogo);			
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
			Iterator<ConstraintViolation<Catalogo>> iterator=violaciones.iterator(); 
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
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_CATALOGOS_ELEMENTOS_FUNCION_ADJUNTAR_NUEVO)
	public Response adjuntarCatalogoElementoNuevo(@PathParam("id") int catalogoid,ElementoCatalogo elemento)
	{	
		Response.ResponseBuilder builder=null;
		
		Set<ConstraintViolation<ElementoCatalogo>> violaciones=elemento.validarInstancia();
		
		if(violaciones.size()==0)
		{
			String msg="";
			
			Catalogo catalogo=repositorio.findById(catalogoid);
			
			if(catalogo==null)
			{
				msg= ConstantesREST.REST_MENSAJE_ENTIDAD_NULA;
				builder=this.builderProvider(Status.NOT_FOUND, MediaType.APPLICATION_JSON);
			}
			else
			{
				try
				{
					elemento.setEstatusRegistro(EstatusRegistro.ACTIVO);
					elemento.setCatalogo(catalogo);
					catalogo.getElementosCatalogo().add(elemento);
					registro.adjuntarElemento(elemento, catalogo);
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
			Iterator<ConstraintViolation<ElementoCatalogo>> iterator=violaciones.iterator(); 
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
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_CATALOGOS_ELEMENTOS_FUNCION_ADJUNTAR_EXISTENTE)
	public Response adjuntarCatalogoElementoExistente(@PathParam("id") int catalogoid,@PathParam("elemento") int elementoid)
	{	
		Response.ResponseBuilder builder=null;
		
		
		String msg="";
		
		Catalogo catalogo=repositorio.findById(catalogoid);
		ElementoCatalogo elemento=repositorio.findElementoById(elementoid);
		
		if(catalogo==null)
		{
			msg= ConstantesREST.REST_MENSAJE_ENTIDAD_NULA;
			builder=this.builderProvider(Status.NOT_FOUND, MediaType.APPLICATION_JSON);
		}
		else if(elemento==null)
		{
			msg= ConstantesREST.REST_MENSAJE_ENTIDAD_NULA;
			builder=this.builderProvider(Status.NOT_FOUND, MediaType.APPLICATION_JSON);
		}
		else if(catalogo.getEstatusRegistro()==EstatusRegistro.ELIMINADO || elemento.getEstatusRegistro()==EstatusRegistro.ELIMINADO)
		{
			msg= ConstantesREST.REST_MENSAJE_ENTIDAD_NULA;
			builder=this.builderProvider(Status.NOT_FOUND, MediaType.APPLICATION_JSON);
		}
		else if(catalogo.getEstatusRegistro()==EstatusRegistro.INACTIVO || elemento.getEstatusRegistro()==EstatusRegistro.INACTIVO)
		{
			msg= ConstantesREST.REST_MENSAJE_ENTIDAD_NULA;
			builder=this.builderProvider(Status.CONFLICT, MediaType.APPLICATION_JSON);
		}
		else if(elemento.getCatalogo().getId()==catalogo.getId())
		{
			msg= ConstantesREST.REST_MENSAJE_ENTIDAD_INTACTA;
			builder=this.builderProvider(Status.NOT_MODIFIED, MediaType.APPLICATION_JSON);
		}
		else
		{
			try
			{
				elemento.setEstatusRegistro(EstatusRegistro.ACTIVO);
				elemento.setCatalogo(catalogo);
				catalogo.getElementosCatalogo().add(elemento);
				registro.adjuntarElemento(elemento, catalogo);
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
		
		return builder.build();
	}
	
}
