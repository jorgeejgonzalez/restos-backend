package idisoft.restos.data;

import java.util.List;

import idisoft.restos.entities.Usuario;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class UsuarioRepository extends Repository {
	
	
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
	
	
}
