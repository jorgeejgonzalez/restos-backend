package idisoft.restos.controller;


import java.util.List;

import idisoft.restos.data.UsuarioRepository;
import idisoft.restos.entities.Usuario;
import idisoft.restos.services.UsuarioRegistry;
import idisoft.restos.util.ConstantesREST;

import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

@Model
public class UsuarioController {
	
	@Inject
	private UsuarioRepository repositorio;
	
	@Inject
	private UsuarioRegistry registro;
	
	@Produces
	@Named
	public List<Usuario> getUsuariosActivos() throws NoResultException
	{
		List<Usuario> usuarios=repositorio.findAllActive();
		if(usuarios.isEmpty())
		{
			throw new NoResultException(ConstantesREST.REST_MENSAJE_LISTA_VACIA);
		}
		return usuarios;
	}
	
	@Produces
	@Named
	public List<Usuario> getUsuariosInactivos() throws NoResultException
	{
		List<Usuario> usuarios=repositorio.findAllInactive();
		if(usuarios.isEmpty())
		{
			throw new NoResultException(ConstantesREST.REST_MENSAJE_LISTA_VACIA);
		}
		return usuarios;
	}
	
	@Produces
	@Named
	public List<Usuario> getUsuariosEliminados() throws NoResultException
	{
		List<Usuario> usuarios=repositorio.findAllDeleted();
		if(usuarios.isEmpty())
		{
			throw new NoResultException(ConstantesREST.REST_MENSAJE_LISTA_VACIA);
		}
		return usuarios;
	}
	

}
