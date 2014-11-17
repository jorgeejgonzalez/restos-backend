package idisoft.restos.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import idisoft.restos.entities.Usuario;
import idisoft.restos.entities.json.UsuarioJSON;

@ApplicationScoped
public class EntitiesFactoryJSON {
	
	public UsuarioJSON parseUsuario(Usuario usuario)
	{
		UsuarioJSON retorno=new UsuarioJSON();
		retorno.parseUsuario(usuario);
		return retorno;
	}
	
	public UsuarioJSON parseUsuarioFromPedido(Usuario usuario)
	{
		UsuarioJSON retorno=new UsuarioJSON();
		retorno.parseUsuarioFromPedidos(usuario);
		return retorno;
	}
	
	public List<UsuarioJSON> usuariosParseJSON(List<Usuario> usuarios)
	{
		List<UsuarioJSON> usuariosjson=new ArrayList<UsuarioJSON>();
		Iterator<Usuario> iterator=usuarios.iterator();
		
		while(iterator.hasNext())
		{
			UsuarioJSON usj=parseUsuario(iterator.next());
			usuariosjson.add(usj);
		}
		
		return usuariosjson;
	}
	

}
