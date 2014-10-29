package idisoft.restos.rest;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import idisoft.restos.data.ProductoRepository;
import idisoft.restos.data.RestauranteRepository;
import idisoft.restos.data.UsuarioRepository;
import idisoft.restos.entities.*;
import idisoft.restos.entities.json.CategoriaProductoJSON;
import idisoft.restos.entities.json.ProductoJSON;
import idisoft.restos.entities.json.EmpresaJSON;
import idisoft.restos.entities.json.SedeJSON;
import idisoft.restos.entities.json.TipoProductoJSON;
import idisoft.restos.entities.json.UsuarioJSON;
import idisoft.restos.services.UsuarioRegistry;



@Path("/test")
public class TestRest {
	
	@Inject
	private UsuarioRepository usuarioRepository;

	@Inject
	UsuarioRegistry registration;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)	
	@Path("/usuarios")	
	public Response getUsuarios()
	{
		//return usuarioRepository.findAll(); 
		Response.ResponseBuilder builder = null;
		
		List<Usuario> usuarios=usuarioRepository.findAllActive();
		
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
				builder=Response.status(Status.NOT_FOUND);			
			}
		}
		else
		{
			builder=Response.status(Status.NOT_FOUND);			
		}
		
		
		builder.header("Access-Control-Allow-Origin", "*");
		builder.header("Access-Control-Allow-Headers", "origin, conent-type, accept, authorization");
        builder.header("Access-Control-Allow-Credentials", "true");
        builder.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        builder.header("Access-Control-Max-Age", "1209600");
		
		builder.type(MediaType.APPLICATION_JSON);
		
		return builder.build();
	}
	
}
