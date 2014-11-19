package idisoft.restos.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

import idisoft.restos.entities.Catalogo;
import idisoft.restos.entities.ElementoCatalogo;
import idisoft.restos.entities.Empresa;
import idisoft.restos.entities.EstatusRegistro;
import idisoft.restos.entities.Pedido;
import idisoft.restos.entities.Sede;
import idisoft.restos.entities.Usuario;
import idisoft.restos.entities.json.CatalogoJSON;
import idisoft.restos.entities.json.ElementoCatalogoJSON;
import idisoft.restos.entities.json.EmpresaJSON;
import idisoft.restos.entities.json.PedidoJSON;
import idisoft.restos.entities.json.SedeJSON;
import idisoft.restos.entities.json.UsuarioJSON;
import idisoft.restos.util.ConstantesEntidades;

@ApplicationScoped
public class EntitiesFactoryJSON {
	
	public UsuarioJSON crearUsuario()
	{
		return new UsuarioJSON();
	}
	
	public EmpresaJSON crearEmpresa()
	{
		return new EmpresaJSON();
	}
	
	public SedeJSON crearSede()
	{
		return new SedeJSON();
	}
	
	public CatalogoJSON crearCatalogo()
	{
		return new CatalogoJSON();
	}
	
	public ElementoCatalogoJSON crearElementoCatalogo()
	{
		return new ElementoCatalogoJSON();
	}
	
	public PedidoJSON crearPedido()
	{
		return new PedidoJSON();
	}
	
	public List<UsuarioJSON> listaUsuarios()
	{
		return new ArrayList<UsuarioJSON>(0);
	}
	
	public Set<UsuarioJSON> setUsuarios()
	{
		return new HashSet<UsuarioJSON>(0);
	}
	
	public List<EmpresaJSON> listaEmpresas()
	{
		return new ArrayList<EmpresaJSON>(0);
	}
	
	public Set<EmpresaJSON> setEmpresas()
	{
		return new HashSet<EmpresaJSON>(0);
	}
	
	public List<SedeJSON> listaSedes()
	{
		return new ArrayList<SedeJSON>(0);
	}
	
	public Set<SedeJSON> setSedes()
	{
		return new HashSet<SedeJSON>(0);
	}
	
	public List<CatalogoJSON> listaCatalogos()
	{
		return new ArrayList<CatalogoJSON>(0);
	}
	
	public Set<CatalogoJSON> setCatalogos()
	{
		return new HashSet<CatalogoJSON>(0);
	}
	
	public List<ElementoCatalogoJSON> listaElementosCatalogo()
	{
		return new ArrayList<ElementoCatalogoJSON>(0);
	}
	
	public Set<ElementoCatalogoJSON> setElementosCatalogo()
	{
		return new HashSet<ElementoCatalogoJSON>(0);
	}
	
	public List<PedidoJSON> listaPedidos()
	{
		return new ArrayList<PedidoJSON>(0);
	}
	
	public Set<PedidoJSON> setPedido()
	{
		return new HashSet<PedidoJSON>(0);
	}
	
	public UsuarioJSON parseUsuario(Usuario usuario)
	{
		UsuarioJSON retorno=crearUsuario();
		retorno.setCedula(usuario.getCedula());
		retorno.setLogin(usuario.getLogin());
		retorno.setPassword(ConstantesEntidades.USUARIO_CLAVE_OCULTA);
		retorno.setEmail(usuario.getEmail());
		retorno.setNombre(usuario.getNombre());
		retorno.setApellido(usuario.getApellido());
		retorno.setDireccion(usuario.getDireccion());
		retorno.setTelefono(usuario.getTelefono());
		retorno.setTipo(usuario.getTipo());
		
		if(usuario.getPedidos()==null)
		{
			retorno.setPedidos(null);
		}
		else if(usuario.getPedidos().isEmpty())
		{
			retorno.setPedidos(null);
		}
		else
		{
			retorno.setPedidos(setPedido());
			Iterator<Pedido> iterator=usuario.getPedidos().iterator();
			while(iterator.hasNext())
			{
				Pedido p=iterator.next();
				retorno.getPedidos().add(parsePedidoFromUsuario(p));
			}
		}
		return retorno;
	}
	
	public UsuarioJSON parseUsuarioFromPedido(Usuario usuario)
	{
		UsuarioJSON retorno=crearUsuario();
		retorno.setCedula(usuario.getCedula());
		retorno.setLogin(usuario.getLogin());
		retorno.setPassword(ConstantesEntidades.USUARIO_CLAVE_OCULTA);
		retorno.setEmail(usuario.getEmail());
		retorno.setNombre(usuario.getNombre());
		retorno.setApellido(usuario.getApellido());
		retorno.setDireccion(usuario.getDireccion());
		retorno.setTelefono(usuario.getTelefono());
		retorno.setTipo(usuario.getTipo());
		retorno.setPedidos(null);
		return retorno;
	}
	
	public List<UsuarioJSON> parseListaUsuarios(List<Usuario> usuarios)
	{
		Iterator<Usuario> iterator=usuarios.iterator();
		List<UsuarioJSON> usuariosjson=listaUsuarios();
		while(iterator.hasNext())
		{
			usuariosjson.add(parseUsuario(iterator.next()));
		}
		
		return usuariosjson;
	}
	
	public List<UsuarioJSON> parseListaUsuarios(Set<Usuario> usuarios)
	{		
		Iterator<Usuario> iterator=usuarios.iterator();
		List<UsuarioJSON> usuariosjson=listaUsuarios();
		while(iterator.hasNext())
		{
			usuariosjson.add(parseUsuario(iterator.next()));
		}
		
		return usuariosjson;
	}
	
	public EmpresaJSON parseEmpresa(Empresa empresa)
	{
		EmpresaJSON retorno=crearEmpresa();
		retorno.setRif(empresa.getRif());
		retorno.setRazonSocial(empresa.getRazonSocial());
		retorno.setDireccionFiscal(empresa.getDireccionFiscal());
		
		if(empresa.getSedes()==null)
		{
			retorno.setSedes(null);			
		}
		else if(empresa.getSedes().isEmpty())
		{
			retorno.setSedes(null);
		}
		else
		{
			retorno.setSedes(setSedes());
			Iterator<Sede> iterator=empresa.getSedes().iterator();
			while(iterator.hasNext())
			{
				Sede s=iterator.next();				
				retorno.getSedes().add(parseSedeFromEmpresa(s));
			}
		}
		
		return retorno;
	}
	
	public EmpresaJSON parseEmpresaFromSede(Empresa empresa)
	{
		EmpresaJSON retorno=crearEmpresa();
		retorno.setRif(empresa.getRif());
		retorno.setRazonSocial(empresa.getRazonSocial());
		retorno.setDireccionFiscal(empresa.getDireccionFiscal());
		retorno.setSedes(null);
		return retorno;
	}
	
	public List<EmpresaJSON> parseListaEmpresas(List<Empresa> empresas)
	{		
		Iterator<Empresa> iterator=empresas.iterator();		
		List<EmpresaJSON> empresasjson=listaEmpresas();
		while(iterator.hasNext())
		{
			empresasjson.add(parseEmpresa(iterator.next()));
		}		
		return empresasjson;
	}
	
	public List<EmpresaJSON> parseListaEmpresas(Set<Empresa> empresas)
	{		
		Iterator<Empresa> iterator=empresas.iterator();		
		List<EmpresaJSON> empresasjson=listaEmpresas();
		while(iterator.hasNext())
		{
			empresasjson.add(parseEmpresa(iterator.next()));
		}		
		return empresasjson;
	}
	
	public SedeJSON parseSede(Sede sede)
	{
		SedeJSON retorno=crearSede();
		retorno.setRif(sede.getRif());
		retorno.setNombre(sede.getNombre());
		retorno.setDireccionFisica(sede.getDireccionFisica());
		retorno.setEmail(sede.getEmail());
		retorno.setTelefono(sede.getTelefono());
		
		if(sede.getEmpresa()==null)
		{
			retorno.setEmpresa(null);
		}
		else
		{
			retorno.setEmpresa(parseEmpresaFromSede(sede.getEmpresa()));
		}		
		
		if(sede.getCatalogos()==null)
		{
			retorno.setCatalogos(null);
		}
		else if(sede.getCatalogos().isEmpty())
		{
			retorno.setCatalogos(null);
		}
		else
		{
			retorno.setCatalogos(setCatalogos());
			Iterator<Catalogo> iterator=sede.getCatalogos().iterator();
			while(iterator.hasNext())
			{
				Catalogo c=iterator.next();
				retorno.getCatalogos().add(parseCatalogoFromSede(c));
			}			
		}		
		return retorno;
	}
	
	public SedeJSON parseSedeFromEmpresa(Sede sede)
	{
		SedeJSON retorno=crearSede();
		retorno.setRif(sede.getRif());
		retorno.setNombre(sede.getNombre());
		retorno.setDireccionFisica(sede.getDireccionFisica());
		retorno.setEmail(sede.getEmail());
		retorno.setTelefono(sede.getTelefono());
		
		retorno.setEmpresa(null);
		
		if(sede.getCatalogos()==null)
		{
			retorno.setCatalogos(null);
		}
		else if(sede.getCatalogos().isEmpty())
		{
			retorno.setCatalogos(null);
		}
		else
		{
			retorno.setCatalogos(setCatalogos());
			Iterator<Catalogo> iterator=sede.getCatalogos().iterator();
			while(iterator.hasNext())
			{
				Catalogo c=iterator.next();
				retorno.getCatalogos().add(parseCatalogoFromSede(c));
			}			
		}		
		return retorno;
	}
	
	public SedeJSON parseSedeFromCatalogo(Sede sede)
	{
		SedeJSON retorno=crearSede();
		retorno.setRif(sede.getRif());
		retorno.setNombre(sede.getNombre());
		retorno.setDireccionFisica(sede.getDireccionFisica());
		retorno.setEmail(sede.getEmail());
		retorno.setTelefono(sede.getTelefono());
		
		if(sede.getEmpresa()==null)
		{
			retorno.setEmpresa(null);
		}
		else
		{
			retorno.setEmpresa(parseEmpresaFromSede(sede.getEmpresa()));
		}
		
		retorno.setCatalogos(null);
				
		return retorno;
	}
	
	public List<SedeJSON> parseListaSedes(List<Sede> sedes)
	{		
		Iterator<Sede> iterator=sedes.iterator();		
		List<SedeJSON> sedesjson=listaSedes();
		while(iterator.hasNext())
		{
			sedesjson.add(parseSede(iterator.next()));
		}		
		return sedesjson;
	}
	
	public List<SedeJSON> parseListaSedes(Set<Sede> sedes)
	{		
		Iterator<Sede> iterator=sedes.iterator();		
		List<SedeJSON> sedesjson=listaSedes();
		while(iterator.hasNext())
		{
			sedesjson.add(parseSede(iterator.next()));
		}		
		return sedesjson;
	}
	
	public CatalogoJSON parseCatalogo(Catalogo catalogo)
	{
		CatalogoJSON retorno=crearCatalogo();
		retorno.setId(catalogo.getId());
		retorno.setNombre(catalogo.getNombre());
		retorno.setEstatus(catalogo.getEstatus());
		
		if(catalogo.getSede()==null)
		{
			retorno.setSede(null);
		}
		else
		{
			retorno.setSede(parseSedeFromCatalogo(catalogo.getSede()));
		}
		
		if(catalogo.getElementos()==null)
		{
			retorno.setElementos(null);
		}
		else if(catalogo.getElementos().isEmpty())
		{
			retorno.setElementos(null);
		}
		else
		{
			retorno.setElementos(setElementosCatalogo());
			Iterator<ElementoCatalogo> iterator=catalogo.getElementos().iterator();
			while(iterator.hasNext())
			{
				ElementoCatalogo e=iterator.next();
				retorno.getElementos().add(parseElementoFromCatalogo(e));
			}		
		}
		return retorno;
	}
	
	public CatalogoJSON parseCatalogoFromSede(Catalogo catalogo)
	{
		CatalogoJSON retorno=crearCatalogo();
		
		retorno.setId(catalogo.getId());
		retorno.setNombre(catalogo.getNombre());
		retorno.setEstatus(catalogo.getEstatus());		
		
		retorno.setSede(null);
		
		if(catalogo.getElementos()==null)
		{
			retorno.setElementos(null);
		}
		else if(catalogo.getElementos().isEmpty())
		{
			retorno.setElementos(null);
		}
		else
		{
			retorno.setElementos(setElementosCatalogo());
			Iterator<ElementoCatalogo> iterator=catalogo.getElementos().iterator();
			while(iterator.hasNext())
			{
				ElementoCatalogo e=iterator.next();				
				retorno.getElementos().add(parseElementoFromCatalogo(e));
			}		
		}
		
		return retorno;	
	}
	
	public CatalogoJSON parseCatalogoFromElemento(Catalogo catalogo)
	{
		CatalogoJSON retorno=crearCatalogo();
		retorno.setId(catalogo.getId());
		retorno.setNombre(catalogo.getNombre());
		retorno.setEstatus(catalogo.getEstatus());
		
		if(catalogo.getSede()==null)
		{
			retorno.setSede(null);
		}
		else
		{
			retorno.setSede(parseSedeFromCatalogo(catalogo.getSede()));
		}
		
		retorno.setElementos(null);
		
		return retorno;
	}
	
	public List<CatalogoJSON> parseListaCatalogos(List<Catalogo> catalogos)
	{		
		Iterator<Catalogo> iterator=catalogos.iterator();		
		List<CatalogoJSON> catalogosjson=listaCatalogos();
		while(iterator.hasNext())
		{
			catalogosjson.add(parseCatalogo(iterator.next()));
		}		
		return catalogosjson;
	}
	
	public List<CatalogoJSON> parseListaCatalogos(Set<Catalogo> catalogos)
	{		
		Iterator<Catalogo> iterator=catalogos.iterator();		
		List<CatalogoJSON> catalogosjson=listaCatalogos();
		while(iterator.hasNext())
		{
			catalogosjson.add(parseCatalogo(iterator.next()));
		}		
		return catalogosjson;
	}
	
	public ElementoCatalogoJSON parseElemento(ElementoCatalogo elemento)
	{
		ElementoCatalogoJSON retorno=crearElementoCatalogo();
		retorno.setId(elemento.getId());
		retorno.setNombre(elemento.getNombre());
		retorno.setDescripcion(elemento.getDescripcion());
		retorno.setPrecio(elemento.getPrecio());
		retorno.setEstatus(elemento.getEstatus());
		
		if(elemento.getCatalogo()==null)
		{
			retorno.setCatalogo(null);
		}
		else
		{
			retorno.setCatalogo(parseCatalogoFromElemento(elemento.getCatalogo()));
		}		
		
		return retorno;
	}
	
	public ElementoCatalogoJSON parseElementoFromCatalogo(ElementoCatalogo elemento)
	{
		ElementoCatalogoJSON retorno=crearElementoCatalogo();
		retorno.setId(elemento.getId());
		retorno.setNombre(elemento.getNombre());
		retorno.setDescripcion(elemento.getDescripcion());
		retorno.setPrecio(elemento.getPrecio());
		retorno.setEstatus(elemento.getEstatus());
		
		retorno.setCatalogo(null);
		
		return retorno;
	}
	
	public List<ElementoCatalogoJSON> parseListaElementos(List<ElementoCatalogo> elementos)
	{		
		Iterator<ElementoCatalogo> iterator=elementos.iterator();		
		List<ElementoCatalogoJSON> elementosjson=listaElementosCatalogo();
		while(iterator.hasNext())
		{
			elementosjson.add(parseElemento(iterator.next()));
		}		
		return elementosjson;
	}
	
	public List<ElementoCatalogoJSON> parseListaElementos(Set<ElementoCatalogo> elementos)
	{		
		Iterator<ElementoCatalogo> iterator=elementos.iterator();		
		List<ElementoCatalogoJSON> elementosjson=listaElementosCatalogo();
		while(iterator.hasNext())
		{
			elementosjson.add(parseElemento(iterator.next()));
		}		
		return elementosjson;
	}
	
	public PedidoJSON parsePedido(Pedido pedido)
	{
		PedidoJSON retorno=crearPedido();
		
		retorno.setId(pedido.getId());
		
		if(pedido.getCliente()==null)
		{
			retorno.setCliente(null);
		}
		else
		{
			retorno.setCliente(parseUsuarioFromPedido(pedido.getCliente()));
		}		
		
		retorno.setDireccionEntrega(pedido.getDireccionEntrega());
		retorno.setTelefonoEntrega(pedido.getTelefonoEntrega());
		retorno.setFecha(pedido.getFecha());
		retorno.setHora(pedido.getHora());
		retorno.setSubTotal(pedido.getSubTotal());
		retorno.setIvaPorcentaje(pedido.getIvaPorcentaje());
		retorno.setIvaMonto(pedido.getIvaMonto());
		retorno.setTotal(pedido.getTotal());
		retorno.setEstatus(pedido.getEstatus());	
		
		if(pedido.getElementos()==null)
		{
			retorno.setElementos(null);
		}
		else if(pedido.getElementos().isEmpty())
		{
			retorno.setElementos(null);
		}
		else
		{
			retorno.setElementos(setElementosCatalogo());
			Iterator<ElementoCatalogo> iterator=pedido.getElementos().iterator();
			while(iterator.hasNext())
			{
				ElementoCatalogo e=iterator.next();				
				retorno.getElementos().add(parseElemento(e));
			}
		}
		
		return retorno;
	}
	
	public PedidoJSON parsePedidoFromUsuario(Pedido pedido)
	{
		PedidoJSON retorno=crearPedido();
		
		retorno.setId(pedido.getId());		
		retorno.setCliente(null);
		retorno.setDireccionEntrega(pedido.getDireccionEntrega());
		retorno.setTelefonoEntrega(pedido.getTelefonoEntrega());
		retorno.setFecha(pedido.getFecha());
		retorno.setHora(pedido.getHora());
		retorno.setSubTotal(pedido.getSubTotal());
		retorno.setIvaPorcentaje(pedido.getIvaPorcentaje());
		retorno.setIvaMonto(pedido.getIvaMonto());
		retorno.setTotal(pedido.getTotal());
		retorno.setEstatus(pedido.getEstatus());	
		
		if(pedido.getElementos()==null)
		{
			retorno.setElementos(null);
		}
		else if(pedido.getElementos().isEmpty())
		{
			retorno.setElementos(null);
		}
		else
		{
			retorno.setElementos(setElementosCatalogo());
			Iterator<ElementoCatalogo> iterator=pedido.getElementos().iterator();
			while(iterator.hasNext())
			{
				ElementoCatalogo e=iterator.next();				
				retorno.getElementos().add(parseElemento(e));
			}
		}
		
		return retorno;
	}
	
	public List<PedidoJSON> parseListaPedidos(List<Pedido> pedidos)
	{		
		Iterator<Pedido> iterator=pedidos.iterator();		
		List<PedidoJSON> pedidosjson=listaPedidos();
		while(iterator.hasNext())
		{
			pedidosjson.add(parsePedido(iterator.next()));
		}		
		return pedidosjson;
	}
	
	public List<PedidoJSON> parseListaPedidos(Set<Pedido> pedidos)
	{		
		Iterator<Pedido> iterator=pedidos.iterator();		
		List<PedidoJSON> pedidosjson=listaPedidos();
		while(iterator.hasNext())
		{
			pedidosjson.add(parsePedido(iterator.next()));
		}		
		return pedidosjson;
	}

}
