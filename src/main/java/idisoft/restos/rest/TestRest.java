package idisoft.restos.rest;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
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

import idisoft.restos.data.RestauranteRepository;
import idisoft.restos.data.UsuarioRepository;
import idisoft.restos.entities.*;
import idisoft.restos.entities.json.RestauranteJSON;
import idisoft.restos.entities.json.SedeJSON;
import idisoft.restos.services.UsuarioRegistro;

@Path("/test")
public class TestRest {
	
	@Inject
	private Logger logger;
	
	@Inject
	private UsuarioRepository usuarioRepository;
	
	@Inject 
	private RestauranteRepository restauranteRepository;

	@Inject
	UsuarioRegistro registration;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/unusuario")	
	public Usuario UnUsuario()
	{
		Usuario r=new Usuario();
		r.setLogin("paulmoody");
		r.setPassword("ABCD1234");		
		r.setCedula("V15000000");
		r.setDireccion("lago azul");
		r.setNombre("paul");
		r.setApellido("moody");		
		r.setEmail("paul@moody.com");
		r.setTelefono("02617000000");
		r.setTipo(TipoUsuario.ADMINISTRADOR);
		
		Set<ConstraintViolation<Usuario>> violaciones=r.validarInstancia();
		
		if(violaciones.size()==0)
		{
			logger.log(Level.INFO,"constraints pasados "+ r.getCedula());
			if(usuarioRepository.findByCedula(r.getCedula())!=null)
			{
				logger.log(Level.SEVERE,"usuario ya existe "+ r.getCedula());				
				return r;
			}
			
			if(usuarioRepository.findByEmail(r.getEmail())!=null)
			{
				logger.log(Level.SEVERE,"email ya existe "+ r.getCedula());				
				return r;
			}
			
			try
			{
				registration.registrarUsuario(r);
			}
			catch(Exception ex)
			{
				logger.log(Level.SEVERE,"Excepcion disparada: "+ ex.getMessage());
			}
						
			return r;
		}
		else
		{
			for(int i=0;i<violaciones.size();i++)
			{
				logger.log(Level.SEVERE,violaciones.iterator().next().getMessage());
			}
			return null;
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/usuarios")	
	public List<Usuario> getUsuarios()
	{
		return usuarioRepository.findAll(); 
	}	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("usuarios/{cedula:[V,E][0-9]*}")
	public Usuario consultarUsuarioPorCedula(@PathParam("cedula") String cedula)
	{
		return usuarioRepository.findByCedula(cedula);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/generausuario")
	public String GenerarUsuario(Usuario r)
	{
		Set<ConstraintViolation<Usuario>> violaciones=r.validarInstancia();
		
		if(violaciones.size()==0)
		{
			logger.log(Level.INFO,"constraints pasados "+ r.getCedula());
			
			if(usuarioRepository.findByCedula(r.getCedula())!=null)
			{
				logger.log(Level.SEVERE,"usuario ya existe "+ r.getCedula());				
				return "Usuario ya existe";
			}
			
			if(usuarioRepository.findByEmail(r.getEmail())!=null)
			{
				logger.log(Level.SEVERE,"email ya existe "+ r.getCedula());				
				return "email ya existe";
			}
			
			try
			{
				registration.registrarUsuario(r);			
				return "usuario registrado";
			}
			
			catch(Exception ex)
			{
				logger.log(Level.SEVERE,"Excepcion disparada: "+ ex.getMessage());
				return "Excepcion disparada: "+ ex.getMessage();
			}
			
		}
		else
		{
			String msg="";
			for(int i=0;i<violaciones.size();i++)
			{
				msg+=violaciones.iterator().next().getMessage();
			}
			logger.log(Level.SEVERE,msg);
			return msg;
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/restaurantes")	
	public List<RestauranteJSON> getRestaurantes()
	{
		List<Restaurante> lista=(List<Restaurante>)restauranteRepository.findAllRestaurantes();
		
		List<RestauranteJSON> retorno=new ArrayList<RestauranteJSON>();
		
		for(int i=0; i<lista.size();++i)
		{
			RestauranteJSON r=new RestauranteJSON();
			r.parseRestaurante(lista.get(i));
			retorno.add(r);
		}
		
		return  retorno;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/sedes")	
	public List<SedeJSON> getSedes()
	{
		List<Sede> lista=(List<Sede>)restauranteRepository.findAllSedes();
		
		List<SedeJSON> retorno=new ArrayList<SedeJSON>();
		
		for(int i=0; i<lista.size();++i)
		{
			SedeJSON s=new SedeJSON();
			s.parseSede(lista.get(i));
			retorno.add(s);
		}
		
		return  retorno;
		 
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/unpedido")	
	public Pedido UnPedido()
	{
		Usuario cliente=new Usuario();
		cliente.setLogin("jorge");
		cliente.setNombre("jorge");
		cliente.setApellido("gonzalez");
		cliente.setDireccion("sanfra");		
		cliente.setEmail("aqui@aqui");
		cliente.setTelefono("123456789");
		
		Sede local=new Sede();
		
		local.setNombre("Bibas Cafe");
		local.setEmail("tato@bibas");
		local.setDireccionFisica("Al lado del bellas artes");
		local.setTelefono("1234567890");
		
		Date fecha=new Date(System.currentTimeMillis());
		Time hora =new Time(System.currentTimeMillis());
		
		
		String direccionEntrega="san francisco";
		String telefonoEntrega="04140675922";
		
		EstatusPedido estatus=EstatusPedido.PENDIENTE;
		
				
		float subTotal=100.0f;
		float porcentajeIVA=14.0f;
		float montoIVA=14.0f;
		float total=114.0f;
		
		Pedido p=new Pedido();
		p.setCliente(cliente);
		p.setLocal(local);
		p.setDireccionEntrega(direccionEntrega);
		p.setTelefonoEntrega(telefonoEntrega);
		p.setEstatus(estatus);
		p.setHora(hora);
		p.setFecha(fecha);		
		p.setSubTotal(subTotal);
		p.setPorcentajeIVA(porcentajeIVA);
		p.setMontoIVA(montoIVA);
		p.setTotal(total);
		
		return p;
	}
	
	
	

}
