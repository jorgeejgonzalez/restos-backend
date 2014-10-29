package idisoft.restos.rest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import idisoft.restos.data.ProductoRepository;
import idisoft.restos.entities.Producto;
import idisoft.restos.entities.json.ProductoJSON;
import idisoft.restos.util.ConstantesREST;

import javax.inject.Inject;
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

@Path(ConstantesREST.REST_PRODUCTOS)
public class ProductoREST extends RestService {
	
	@Inject
	private ProductoRepository repositorio; 
	
	public Response.ResponseBuilder listarProductos(List<Producto> productos, String funcion)
	{
		Response.ResponseBuilder builder = null;
		
		if(productos!=null)
		{
			if(productos.size()>0)
			{
				List<ProductoJSON> productosjson=new ArrayList<ProductoJSON>();
				Iterator<Producto> iterator=productos.iterator();
				
				builder=this.builderProvider(Status.OK, MediaType.APPLICATION_JSON);
				
				while(iterator.hasNext())
				{
					ProductoJSON prj=new ProductoJSON();
					prj.parseProducto(iterator.next());
					productosjson.add(prj);
				}
				
				builder.entity(productosjson);
				
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
	@Path(ConstantesREST.REST_PRODUCTOS_FUNCION_LISTAR)
	public Response listarProductosActivos()
	{
		Response.ResponseBuilder builder = null;
		
		List<Producto> productos=repositorio.findAllActive();
		
		builder=listarProductos(productos, ConstantesREST.REST_PRODUCTOS+ConstantesREST.REST_PRODUCTOS_FUNCION_LISTAR);
		
		return builder.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_PRODUCTOS_FUNCION_LISTAR_INACTIVOS)
	public Response listarProductosInActivos()
	{
		Response.ResponseBuilder builder = null;
		
		List<Producto> productos=repositorio.findAllInactive();
		
		builder=listarProductos(productos, ConstantesREST.REST_PRODUCTOS+ConstantesREST.REST_PRODUCTOS_FUNCION_LISTAR_INACTIVOS);
		
		return builder.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_PRODUCTOS_FUNCION_LISTAR_ELIMINADOS)
	public Response listarProductosEliminados()
	{
		Response.ResponseBuilder builder = null;
		
		List<Producto> productos=repositorio.findAllDeleted();
		
		builder=listarProductos(productos, ConstantesREST.REST_PRODUCTOS+ConstantesREST.REST_PRODUCTOS_FUNCION_LISTAR_ELIMINADOS);
		
		return builder.build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_PRODUCTOS_FUNCION_CREAR)
	public Response crearProducto(Producto producto)
	{
		/*
		 * TO-DO: Implement function
		 */
		return null;
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_PRODUCTOS_FUNCION_ACTUALIZAR)
	public Response actualizarProducto(@PathParam("id") int id, Producto producto)
	{
		/*
		 * TO-DO: implement function
		 */	
		return null;
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.REST_PRODUCTOS_FUNCION_ELIMINAR)
	public Response eliminarProducto(@PathParam("id") int id)
	{
		/*
		 * TO-DO: implement function
		 */	
		return null;
	}

}
