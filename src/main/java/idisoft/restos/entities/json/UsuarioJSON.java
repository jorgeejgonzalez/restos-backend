package idisoft.restos.entities.json;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import idisoft.restos.entities.EstatusRegistro;
import idisoft.restos.entities.Pedido;
import idisoft.restos.entities.TipoUsuario;
import idisoft.restos.entities.Usuario;
import idisoft.restos.util.MensajesEntidades;

@SuppressWarnings("serial")
public class UsuarioJSON implements Serializable{
	
	private String login;		
	
	private String password;
	
	private TipoUsuario tipo;
	
	private String email;
	
	private String nombre;
	
	private String apellido;
	
	private String cedula;
	
	private String direccion;
	
	private String telefono;
	
	private Set<PedidoJSON> pedidos = new HashSet<PedidoJSON>(0);

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public Set<PedidoJSON> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<PedidoJSON> pedidos) {
		this.pedidos = pedidos;
	}

	public void parseUsuario(Usuario usuario)
	{
		this.cedula=usuario.getCedula();
		this.login =usuario.getLogin();
		this.password=MensajesEntidades.USUARIO_CLAVE_OCULTA;
		this.email=usuario.getEmail();
		this.nombre=usuario.getNombre();
		this.apellido=usuario.getApellido();
		this.direccion=usuario.getDireccion();
		this.telefono=usuario.getTelefono();
		this.tipo=usuario.getTipo();
		
		if(usuario.getPedidos()==null)
		{
			this.pedidos=null;
		}
		else if(usuario.getPedidos().isEmpty())
		{
			this.pedidos=null;
		}
		else
		{
			Iterator<Pedido> iterator=usuario.getPedidos().iterator();
			while(iterator.hasNext())
			{
				Pedido p=iterator.next();
				
				if(p.getEstatusRegistro()==EstatusRegistro.ACTIVO)
				{
					PedidoJSON pej=new PedidoJSON();
					
					pej.parsePedidoFromUsuario(p);
					
					this.pedidos.add(pej);
				}
			}
		}
	}
	
	public void parseUsuarioFromPedidos(Usuario usuario)
	{
		this.cedula=usuario.getCedula();
		this.login =usuario.getLogin();
		this.password=MensajesEntidades.USUARIO_CLAVE_OCULTA;
		this.email=usuario.getEmail();
		this.nombre=usuario.getNombre();
		this.apellido=usuario.getApellido();
		this.direccion=usuario.getDireccion();
		this.telefono=usuario.getTelefono();
		this.tipo=usuario.getTipo();
		
		this.pedidos=null;
	}

}
