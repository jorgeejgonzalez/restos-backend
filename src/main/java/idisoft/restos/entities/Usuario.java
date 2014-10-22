package idisoft.restos.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

//import java.util.List;


@SuppressWarnings("serial")
@Entity
@Table(name = "usuarios",catalog="restos")
public class Usuario implements Serializable {
	
	@NotNull
	@Size(min=6,max=20)
	@Column
	private String login;		
	
	@NotNull
	@Size(min=8,max=20)
	@Column(name="clave")
	private String password;
	
	@NotNull
	@Column
	private TipoUsuario tipo;
	
	@NotNull
	@Email
	@Column
	private String email;
	
	@NotNull
	@Column
	private String nombre;
	
	@NotNull
	@Column
	private String apellido;
	
	@Id
	@NotNull
	@Size(min=8,max=9)
	@Pattern(regexp="[V,E][0-9]*",message="debe seguir formato de cedula venezolana")
	@Column
	private String cedula;
	
	@Column
	private String direccion;
	
	@NotNull
	@Size(min=11,max=11)
	@Pattern(regexp="[0-9]*",message="solo acepta numeros")
	@Column
	private String telefono;
	
	public Usuario()
	{	
	}
	
	public Usuario(String login,
			String password,
			TipoUsuario tipo,
			String email,
			String nombre,
			String apellido,
			String cedula,
			String direccion,
			String telefono)
	{
		this.login=login;
		this.password=password;
		this.tipo=tipo;
		this.email=email;
		this.nombre=nombre;
		this.apellido=apellido;
		this.cedula=cedula;
		this.direccion=direccion;
		this.telefono=telefono;
	}
		
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNombre() {
		return nombre;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
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
	
	public Set<ConstraintViolation<Usuario>> validarInstancia()
	{
		ValidatorFactory factory= Validation.buildDefaultValidatorFactory();
		Validator validator=factory.getValidator();
		return validator.validate(this);
	}
	
}
