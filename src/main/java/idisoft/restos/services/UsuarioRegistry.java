package idisoft.restos.services;


import idisoft.restos.entities.EstatusRegistro;
import idisoft.restos.entities.Pedido;
import idisoft.restos.entities.Usuario;

import javax.ejb.Stateless;


@Stateless
public class UsuarioRegistry extends Registry {
	
	
	
	public Usuario registrar(Usuario usuario) throws Exception
	{
		return (Usuario)persist(usuario,"Registrando a " + usuario.getNombre()+ " "+usuario.getApellido());		
	}
	
	public Usuario actualizar(Usuario usuario) throws Exception
	{
		return (Usuario)merge(usuario,"Actualizando " + usuario.getCedula());		
	}
	
	public Usuario modificar(Usuario usuario, Usuario modificado)
	{
		usuario.setNombre(modificado.getNombre());
		usuario.setApellido(modificado.getApellido());
		usuario.setDireccion(modificado.getDireccion());
		usuario.setTelefono(modificado.getTelefono());
		usuario.setTipo(modificado.getTipo());
		usuario.setLogin(modificado.getLogin());
		usuario.setEmail(modificado.getEmail());
		
		return (Usuario)merge(usuario,"Actualizando el Usuario " + usuario.getCedula());		
	}
	
	public Usuario modificarPassword(Usuario usuario, Usuario modificado)
	{
		usuario.setPassword(modificado.getPassword());
		
		return (Usuario)merge(usuario,"Actualizando el Usuario " + usuario.getCedula());		
	}
	
	public Usuario eliminar(Usuario usuario) throws Exception
	{
		usuario.setEstatusRegistro(EstatusRegistro.ELIMINADO);
		return (Usuario)merge(usuario,"Eliminando el Usuario " + usuario.getCedula());
	}
	
	public Pedido generarPedido(Pedido pedido) throws Exception
	{
		Pedido retorno=(Pedido)persist(pedido,"Generando Pedido");
		return retorno;
	}
	
	public Pedido actualizarPedido(Pedido pedido) throws Exception
	{
		Pedido retorno=(Pedido)merge(pedido,"Generando Pedido "+pedido.getId());
		return retorno;
	}

}
