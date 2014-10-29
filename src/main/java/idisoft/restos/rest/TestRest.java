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
	private ProductoRepository productoRepository;

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
	public Response GenerarUsuario(Usuario r)
	{
		Response.ResponseBuilder builder = null;
		
		Set<ConstraintViolation<Usuario>> violaciones=r.validarInstancia();
		
		if(violaciones.size()==0)
		{
			String msg="";
			
			logger.log(Level.INFO,"constraints pasados "+ r.getCedula());
			
			if(usuarioRepository.findByCedula(r.getCedula())!=null)
			{
				logger.log(Level.SEVERE,"usuario ya existe "+ r.getCedula());				
				msg= "Usuario ya existe";
				builder=Response.status(Status.CONFLICT);
			}
			else if(usuarioRepository.findByEmail(r.getEmail())!=null)
			{
				logger.log(Level.SEVERE,"email ya existe "+ r.getCedula());				
				msg= "email ya existe";
				builder=Response.status(Status.CONFLICT);
			}
			else
			{
				try
				{
					registration.registrarUsuario(r);			
					msg= "usuario registrado";
					builder = Response.ok();
				}
				
				catch(Exception ex)
				{
					logger.log(Level.SEVERE,"Excepcion disparada: "+ ex.getMessage());
					msg= "Excepcion disparada: "+ ex.getMessage();
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
		
		
		builder.header("Access-Control-Allow-Origin", "*");
		builder.header("Access-Control-Allow-Headers", "origin, conent-type, accept, authorization");
        builder.header("Access-Control-Allow-Credentials", "true");
        builder.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        builder.header("Access-Control-Max-Age", "1209600");
		
		builder.type(MediaType.APPLICATION_JSON);
		
		return builder.build();
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/restaurantes")	
	public List<EmpresaJSON> getRestaurantes()
	{
		List<Empresa> lista=(List<Empresa>)restauranteRepository.findAllRestaurantes();
		
		List<EmpresaJSON> retorno=new ArrayList<EmpresaJSON>();
		
		for(int i=0; i<lista.size();++i)
		{
			EmpresaJSON r=new EmpresaJSON();
			r.parseEmpresa(lista.get(i));
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
	@Path("/productos")	
	public List<ProductoJSON> getProductos()
	{
		List<Producto> lista=(List<Producto>)productoRepository.findAllProductos();
		
		List<ProductoJSON> retorno=new ArrayList<ProductoJSON>();
		
		for(int i=0; i<lista.size();++i)
		{
			ProductoJSON p=new ProductoJSON();
			p.parseProducto(lista.get(i));
			retorno.add(p);
		}
		
		return  retorno;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/productos/tipos")	
	public List<TipoProductoJSON> getTiposProductos()
	{
		List<TipoProducto> lista=(List<TipoProducto>)productoRepository.findAllTiposProductos();
		
		List<TipoProductoJSON> retorno=new ArrayList<TipoProductoJSON>();
		
		for(int i=0; i<lista.size();++i)
		{
			TipoProductoJSON tp=new TipoProductoJSON();
			tp.parseTipo(lista.get(i));
			retorno.add(tp);
		}
		
		return  retorno;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/productos/categorias")	
	public List<CategoriaProductoJSON> getCategoriasProductos()
	{
		List<CategoriaProducto> lista=(List<CategoriaProducto>)productoRepository.findAllCategoriasProductos();
		
		List<CategoriaProductoJSON> retorno=new ArrayList<CategoriaProductoJSON>();
		
		for(int i=0; i<lista.size();++i)
		{
			CategoriaProductoJSON cp=new CategoriaProductoJSON();
			cp.parseCategoria(lista.get(i));
			retorno.add(cp);
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
		p.setDireccionEntrega(direccionEntrega);
		p.setTelefonoEntrega(telefonoEntrega);
		p.setEstatus(estatus);
		p.setHora(hora);
		p.setFecha(fecha);		
		p.setSubTotal(subTotal);
		p.setIvaPorcentaje(porcentajeIVA);
		p.setIvaMonto(montoIVA);
		p.setTotal(total);
		
		return p;
	}
	
	
	

}
