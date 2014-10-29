package idisoft.restos.rest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import idisoft.restos.data.UsuarioRepository;
import idisoft.restos.entities.*;
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
