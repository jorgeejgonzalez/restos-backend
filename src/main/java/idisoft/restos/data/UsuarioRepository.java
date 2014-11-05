package idisoft.restos.data;

import java.util.List;

import idisoft.restos.entities.EstatusRegistro;
import idisoft.restos.entities.Usuario;
import idisoft.restos.util.ConstantesEntidades;
import idisoft.restos.util.ConstantesREST;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;


@ApplicationScoped
public class UsuarioRepository extends Repository implements ListRecords{
	
	
	public Usuario findByCedula(String cedula)
	{
		return (Usuario)findByStringKey(Usuario.class, cedula);
	}
	
	public Usuario findByEmail(String email)
	{
		return (Usuario)findSingleByString(Usuario.class, "email", email);
	}
	
	public Usuario findByLogin(String login)
	{
		return (Usuario)findSingleByString(Usuario.class, "login", login);
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> findAll()
	{
		return findAll(Usuario.class, "cedula");
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> findAllActive() throws NoResultException
	{
		return findAllFiltered(Usuario.class, "cedula", EstatusRegistro.ACTIVO);
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> findAllInactive()
	{
		return findAllFiltered(Usuario.class, "cedula", EstatusRegistro.INACTIVO);
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> findAllDeleted()
	{
		return findAllFiltered(Usuario.class, "cedula", EstatusRegistro.ELIMINADO);
	}
	
	
}
