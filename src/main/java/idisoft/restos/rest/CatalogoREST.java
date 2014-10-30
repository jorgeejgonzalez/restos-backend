package idisoft.restos.rest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import idisoft.restos.data.CatalogosRepository;
import idisoft.restos.entities.Catalogo;
import idisoft.restos.entities.json.CatalogoJSON;
import idisoft.restos.util.ConstantesREST;

@Path(ConstantesREST.REST_CATALOGOS)
public class CatalogoREST extends RestService {
	
	@Inject
	private CatalogosRepository repository;

	
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
		List<Catalogo> catalogos=repository.findAllActive();
		
		Response.ResponseBuilder builder=listarCatalogos(catalogos, ConstantesREST.REST_CATALOGOS+ConstantesREST.REST_CATALOGOS_FUNCION_LISTAR);
		
		return builder.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_CATALOGOS_FUNCION_LISTAR_INACTIVOS)
	public Response listarCatalogosInactivos()
	{
		List<Catalogo> catalogos=repository.findAllInactive();
		
		Response.ResponseBuilder builder=listarCatalogos(catalogos, ConstantesREST.REST_CATALOGOS+ConstantesREST.REST_CATALOGOS_FUNCION_LISTAR_INACTIVOS);
		
		return builder.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_CATALOGOS_FUNCION_LISTAR_ELIMINADOS)
	public Response listarCatalogosEliminados()
	{
		List<Catalogo> catalogos=repository.findAllDeleted();
		
		Response.ResponseBuilder builder=listarCatalogos(catalogos, ConstantesREST.REST_CATALOGOS+ConstantesREST.REST_CATALOGOS_FUNCION_LISTAR_ELIMINADOS);
		
		return builder.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_CATALOGOS_SEDE_FUNCION_LISTAR)
	public Response listarCatalogosActivosPorSede(@PathParam("id") int sedeid)
	{
		List<Catalogo> catalogos=repository.findAllActiveBySede(sedeid);
		
		Response.ResponseBuilder builder=listarCatalogos(catalogos, ConstantesREST.REST_CATALOGOS+ConstantesREST.REST_CATALOGOS_SEDE_FUNCION_LISTAR);
		
		return builder.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_CATALOGOS_SEDE_FUNCION_LISTAR_INACTIVOS)
	public Response listarCatalogosInactivosPorSede(@PathParam("id") int sedeid)
	{
		List<Catalogo> catalogos=repository.findAllInactiveBySede(sedeid);
		
		Response.ResponseBuilder builder=listarCatalogos(catalogos, ConstantesREST.REST_CATALOGOS+ConstantesREST.REST_CATALOGOS_SEDE_FUNCION_LISTAR_INACTIVOS);
		
		return builder.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_CATALOGOS_SEDE_FUNCION_LISTAR_ELIMINADOS)
	public Response listarCatalogosEliminadosPorSede(@PathParam("id") int sedeid)
	{
		List<Catalogo> catalogos=repository.findAllActiveBySede(sedeid);
		
		Response.ResponseBuilder builder=listarCatalogos(catalogos, ConstantesREST.REST_CATALOGOS+ConstantesREST.REST_CATALOGOS_SEDE_FUNCION_LISTAR_ELIMINADOS);
		
		return builder.build();
	}
	
}
