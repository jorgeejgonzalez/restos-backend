package idisoft.restos.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import idisoft.restos.entities.Catalogo;
import idisoft.restos.entities.ElementoCatalogo;
import idisoft.restos.entities.Empresa;
import idisoft.restos.entities.EstatusCatalogo;
import idisoft.restos.entities.EstatusPedido;
import idisoft.restos.entities.EstatusRegistro;
import idisoft.restos.entities.Pedido;
import idisoft.restos.entities.Sede;
import idisoft.restos.entities.TipoUsuario;
import idisoft.restos.entities.Usuario;
import idisoft.restos.util.ConstantesREST;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EntitiesFactory {
	
	public Usuario crearUsuarioFinal()
	{
		Usuario usuario=new Usuario();
		usuario.setCedula("V00000000");
		usuario.setLogin("");
		usuario.setPassword("");
		usuario.setEmail("");
		usuario.setTipo(TipoUsuario.USUARIO_FINAL);
		usuario.setNombre("");
		usuario.setApellido("");
		usuario.setDireccion("");
		usuario.setTelefono("");
		usuario.setEstatusRegistro(EstatusRegistro.INACTIVO);
		usuario.setPedidos(setPedidos());
		return usuario;
	}
	
	public Usuario crearUsuarioAdministrador()
	{
		Usuario usuario=new Usuario();
		usuario.setCedula("V00000000");
		usuario.setLogin("");
		usuario.setPassword("");
		usuario.setEmail("");
		usuario.setTipo(TipoUsuario.ADMINISTRADOR);
		usuario.setNombre("");
		usuario.setApellido("");
		usuario.setDireccion("");
		usuario.setTelefono("");
		usuario.setEstatusRegistro(EstatusRegistro.ACTIVO);
		usuario.setPedidos(setPedidos());
		return usuario;
	}
	
	public Usuario crearUsuarioMaster()
	{
		Usuario usuario=new Usuario();
		usuario.setCedula("V00000000");
		usuario.setLogin("");
		usuario.setPassword("");
		usuario.setEmail("");
		usuario.setTipo(TipoUsuario.MASTER);
		usuario.setNombre("");
		usuario.setApellido("");
		usuario.setDireccion("");
		usuario.setTelefono("");
		usuario.setEstatusRegistro(EstatusRegistro.ACTIVO);
		usuario.setPedidos(setPedidos());
		return usuario;
	}
	
	public Usuario crearUsuarioDespacho()
	{
		Usuario usuario=new Usuario();
		usuario.setCedula("V00000000");
		usuario.setLogin("");
		usuario.setPassword("");
		usuario.setEmail("");
		usuario.setTipo(TipoUsuario.DESPACHO);
		usuario.setNombre("");
		usuario.setApellido("");
		usuario.setDireccion("");
		usuario.setTelefono("");
		usuario.setEstatusRegistro(EstatusRegistro.ACTIVO);
		usuario.setPedidos(setPedidos());
		return usuario;
	}
		
	public Empresa crearEmpresa()
	{
		Empresa empresa=new Empresa();		
		empresa.setRif("J000000000");
		empresa.setRazonSocial("");
		empresa.setDireccionFiscal("");
		empresa.setEstatusRegistro(EstatusRegistro.ACTIVO);
		empresa.setSedes(setSedes());		
		return empresa;
	}
	
	public Sede crearSede()
	{
		Sede sede=new Sede();
		sede.setNombre("");
		sede.setDireccionFisica("");
		sede.setRif("");
		sede.setEmail("");
		sede.setTelefono("");
		sede.setEmpresa(crearEmpresa());
		sede.setCatalogos(setCatalogos());
		sede.setEstatusRegistro(EstatusRegistro.ACTIVO);
		return sede;
	}
	
	public Sede crearSede(Empresa empresa)
	{
		Sede sede=new Sede();
		sede.setNombre("");
		sede.setDireccionFisica("");
		sede.setRif("");
		sede.setEmail("");
		sede.setTelefono("");
		sede.setEmpresa(empresa);
		sede.setCatalogos(setCatalogos());
		sede.setEstatusRegistro(EstatusRegistro.ACTIVO);
		return sede;
	}
	
	public Catalogo crearCatalogo()
	{
		Catalogo catalogo=new Catalogo();
		catalogo.setNombre("");
		catalogo.setEstatus(EstatusCatalogo.DISPONIBLE);
		catalogo.setSede(crearSede());
		catalogo.setEstatusRegistro(EstatusRegistro.ACTIVO);
		catalogo.setElementos(setElementosCatalogo());
		return catalogo;
	}
	
	public Catalogo crearCatalogo(Sede sede)
	{
		Catalogo catalogo=new Catalogo();
		catalogo.setNombre("");
		catalogo.setEstatus(EstatusCatalogo.DISPONIBLE);
		catalogo.setSede(sede);
		catalogo.setEstatusRegistro(EstatusRegistro.ACTIVO);
		catalogo.setElementos(setElementosCatalogo());
		return catalogo;
	}
	
	public ElementoCatalogo crearElementoCatalogo()
	{
		ElementoCatalogo elemento=new ElementoCatalogo();
		elemento.setNombre("");
		elemento.setDescripcion("");
		elemento.setPrecio(0.0f);
		elemento.setEstatus(EstatusCatalogo.DISPONIBLE);
		elemento.setEstatusRegistro(EstatusRegistro.ACTIVO);
		elemento.setCatalogo(crearCatalogo());		
		return elemento;
	}
	
	public ElementoCatalogo crearElementoCatalogo(Catalogo catalogo)
	{
		ElementoCatalogo elemento=new ElementoCatalogo();
		elemento.setNombre("");
		elemento.setDescripcion("");
		elemento.setPrecio(0.0f);
		elemento.setEstatus(EstatusCatalogo.DISPONIBLE);
		elemento.setEstatusRegistro(EstatusRegistro.ACTIVO);
		elemento.setCatalogo(catalogo);		
		return elemento;
	}
	
	public Pedido crearPedido()
	{
		java.util.Date date=new java.util.Date();		
		Usuario usuario=crearUsuarioFinal();
		
		Pedido pedido=new Pedido();		
		pedido.setCliente(usuario);		
		pedido.setDireccionEntrega(usuario.getDireccion());
		pedido.setTelefonoEntrega(usuario.getTelefono());
		pedido.setFecha(new java.sql.Date(date.getTime()));
		pedido.setHora(new java.sql.Time(date.getTime()));
		pedido.setSubTotal(0.0f);
		pedido.setIvaPorcentaje(ConstantesREST.REST_USUARIOS_PEDIDOS_FLOAT_PORCENTAJE_IVA_VALUE);
		pedido.setIvaMonto(0.0f);
		pedido.setTotal(0.0f);		
		pedido.setEstatus(EstatusPedido.CREADO);
		pedido.setEstatusRegistro(EstatusRegistro.ACTIVO);
		pedido.setElementos(setElementosCatalogo());
		return pedido;
	}
	
	public Pedido crearPedido(Usuario usuario)
	{
		java.util.Date date=new java.util.Date();
		
		Pedido pedido=new Pedido();		
		pedido.setCliente(usuario);		
		pedido.setDireccionEntrega(usuario.getDireccion());
		pedido.setTelefonoEntrega(usuario.getTelefono());
		pedido.setFecha(new java.sql.Date(date.getTime()));
		pedido.setHora(new java.sql.Time(date.getTime()));
		pedido.setSubTotal(0.0f);
		pedido.setIvaPorcentaje(ConstantesREST.REST_USUARIOS_PEDIDOS_FLOAT_PORCENTAJE_IVA_VALUE);
		pedido.setIvaMonto(0.0f);
		pedido.setTotal(0.0f);		
		pedido.setEstatus(EstatusPedido.CREADO);
		pedido.setEstatusRegistro(EstatusRegistro.ACTIVO);
		pedido.setElementos(setElementosCatalogo());
		return pedido;
	}
	
	public Pedido crearPedido(Usuario usuario, Set<ElementoCatalogo> elementos)
	{
		java.util.Date date=new java.util.Date();		
		Pedido pedido=new Pedido();		
		pedido.setCliente(usuario);		
		pedido.setDireccionEntrega(usuario.getDireccion());
		pedido.setTelefonoEntrega(usuario.getTelefono());
		pedido.setFecha(new java.sql.Date(date.getTime()));
		pedido.setHora(new java.sql.Time(date.getTime()));
		pedido.setIvaPorcentaje(ConstantesREST.REST_USUARIOS_PEDIDOS_FLOAT_PORCENTAJE_IVA_VALUE);
		pedido.setEstatus(EstatusPedido.CREADO);
		pedido.setEstatusRegistro(EstatusRegistro.ACTIVO);
		pedido.setElementos(elementos);
		pedido.calcularMontos();
		return pedido;
	}
	
	public Pedido crearPedido(Usuario usuario, List<ElementoCatalogo> elementos)
	{
		java.util.Date date=new java.util.Date();
		Set<ElementoCatalogo> setelementos=setElementosCatalogo();
		Iterator<ElementoCatalogo> iterator=elementos.iterator();
		
		while(iterator.hasNext())
		{
			ElementoCatalogo elemento=iterator.next();
			setelementos.add(elemento);
		}
		
		Pedido pedido=new Pedido();		
		pedido.setCliente(usuario);		
		pedido.setDireccionEntrega(usuario.getDireccion());
		pedido.setTelefonoEntrega(usuario.getTelefono());
		pedido.setFecha(new java.sql.Date(date.getTime()));
		pedido.setHora(new java.sql.Time(date.getTime()));
		pedido.setIvaPorcentaje(ConstantesREST.REST_USUARIOS_PEDIDOS_FLOAT_PORCENTAJE_IVA_VALUE);
		pedido.setEstatus(EstatusPedido.CREADO);
		pedido.setEstatusRegistro(EstatusRegistro.ACTIVO);
		pedido.setElementos(setelementos);
		pedido.calcularMontos();
		return pedido;
	}
	
	public Set<Usuario> setUsuarios()
	{
		Set<Usuario> usuarios=new HashSet<Usuario>(0);
		return usuarios;
	}
	
	public List<Usuario> listaUsuarios()
	{
		List<Usuario> usuarios=new ArrayList<Usuario>(0);
		return usuarios;
	}
	
	public Set<Empresa> setEmpresas()
	{
		Set<Empresa> empresas=new HashSet<Empresa>(0);
		return empresas;
	}
	
	public List<Empresa> listaEmpresas()
	{
		List<Empresa> empresas=new ArrayList<Empresa>(0);
		return empresas;
	}
	
	public Set<Sede> setSedes()
	{
		Set<Sede> sedes=new HashSet<Sede>(0);
		return sedes;
	}
	
	public List<Sede> listaSedes()
	{
		List<Sede> sedes=new ArrayList<Sede>(0);
		return sedes;
	}
	
	public Set<Catalogo> setCatalogos()
	{
		Set<Catalogo> catalogos=new HashSet<Catalogo>(0);
		return catalogos;
	}
	
	public List<Catalogo> listaCatalogos()
	{
		List<Catalogo> catalogos=new ArrayList<Catalogo>(0);
		return catalogos;
	}
	
	public Set<ElementoCatalogo> setElementosCatalogo()
	{
		Set<ElementoCatalogo> elementos=new HashSet<ElementoCatalogo>(0);
		return elementos;
	}
	
	public List<ElementoCatalogo> listaElementosCatalogo()
	{
		List<ElementoCatalogo> elementos=new ArrayList<ElementoCatalogo>(0);
		return elementos;
	}
	
	public Set<Pedido> setPedidos()
	{
		Set<Pedido> pedidos=new HashSet<Pedido>(0);
		return pedidos;
	}
	
	public List<Pedido> listaPedidos()
	{
		List<Pedido> pedidos=new ArrayList<Pedido>(0);
		return pedidos;
	}
	
	
}
