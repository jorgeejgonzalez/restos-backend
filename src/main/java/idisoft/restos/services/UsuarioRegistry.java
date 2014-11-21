package idisoft.restos.services;


import idisoft.restos.entities.Pedido;
import idisoft.restos.entities.Usuario;

import javax.ejb.Stateless;


@Stateless
public class UsuarioRegistry extends Registry {
	
	
	
	public Usuario registrar(Usuario usuario) throws Exception
	{
		return (Usuario)persist(usuario,"Registrando a " + usuario.getNombre()+ " "+usuario.getApellido());		
	}
	
	public void actualizar(Usuario usuario) throws Exception
	{
		merge(usuario,"Actualizando el Usuario " + usuario.getCedula());
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
