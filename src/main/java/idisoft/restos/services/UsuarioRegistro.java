package idisoft.restos.services;

import idisoft.restos.entities.Usuario;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class UsuarioRegistro {
	
	@Inject
	Logger log;
	
	@Inject
	EntityManager em;
	
	public void registrarUsuario(Usuario usuario) throws Exception
	{
		log.info("Registrando a " + usuario.getNombre()+ " "+usuario.getApellido());
		em.persist(usuario);
	}
	
	public void actualizarUsuario(Usuario usuario) throws Exception
	{
		log.info("Actualizando el Usuario " + usuario.getCedula());
		em.merge(usuario);
	}

}
