package idisoft.restos.services;


import idisoft.restos.entities.Usuario;

import javax.ejb.Stateless;


@Stateless
public class UsuarioRegistry extends Registry {
	
	
	
	public void registrar(Usuario usuario) throws Exception
	{
		persist(usuario,"Registrando a " + usuario.getNombre()+ " "+usuario.getApellido());		
	}
	
	public void actualizar(Usuario usuario) throws Exception
	{
		merge(usuario,"Actualizando el Usuario " + usuario.getCedula());
		
	}

}
