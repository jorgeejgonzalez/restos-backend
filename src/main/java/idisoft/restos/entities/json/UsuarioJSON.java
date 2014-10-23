package idisoft.restos.entities.json;

import java.io.Serializable;

import idisoft.restos.entities.TipoUsuario;
import idisoft.restos.entities.Usuario;
import idisoft.restos.util.ConstantesEntidades;

@SuppressWarnings("serial")
public class UsuarioJSON implements Serializable{
	
	//private static final String HIDE_PASSWORD= "************";
	
	private String login;		
	
	private String password;
	
	private TipoUsuario tipo;
	
	private String email;
	
	private String nombre;
	
	private String apellido;
	
	private String cedula;
	
	private String direccion;
	
	private String telefono;

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
	
	public void parseUsuario(Usuario usuario)
	{
		this.cedula=usuario.getCedula();
		this.login =usuario.getLogin();
		this.password=ConstantesEntidades.HIDE_PASSSWORD;
		this.email=usuario.getEmail();
		this.nombre=usuario.getNombre();
		this.apellido=usuario.getApellido();
		this.direccion=usuario.getDireccion();
		this.telefono=usuario.getTelefono();
		this.tipo=usuario.getTipo();
		
	}

}
