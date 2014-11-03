package idisoft.restos.entities;

import idisoft.restos.util.MensajesEntidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Email;

//import java.util.List;


@SuppressWarnings("serial")
@Entity
@Table(
		name = "usuarios",catalog="restos",
		uniqueConstraints=
		{
				@UniqueConstraint(columnNames="email"),
				@UniqueConstraint(columnNames="login")
		}
)
public class Usuario implements Serializable {
	
	@NotNull(message=MensajesEntidades.ENTIDAD_USUARIO_LOGIN+MensajesEntidades.VALIDACION_VALOR_NULO)
	@Size(min=6,max=20,message=MensajesEntidades.ENTIDAD_USUARIO_LOGIN+MensajesEntidades.VALIDACION_STRING_VALOR_LONGITUD+ "6 y 20")
	@Column
	private String login;		
	
	@NotNull(message=MensajesEntidades.ENTIDAD_USUARIO_PASSWORD+MensajesEntidades.VALIDACION_VALOR_NULO)
	@Size(min=8,max=20,message=MensajesEntidades.ENTIDAD_USUARIO_PASSWORD+MensajesEntidades.VALIDACION_STRING_VALOR_LONGITUD+ "8 y 20")
	@Column(name="clave")
	private String password;
	
	@NotNull(message=MensajesEntidades.ENTIDAD_USUARIO_TIPO+MensajesEntidades.VALIDACION_VALOR_NULO)
	@Column
	private TipoUsuario tipo;
	
	@NotNull(message=MensajesEntidades.ENTIDAD_USUARIO_EMAIL+MensajesEntidades.VALIDACION_VALOR_NULO)
	@Email(message=MensajesEntidades.ENTIDAD_USUARIO_EMAIL+MensajesEntidades.VALIDACION_STRING_FORMATO_EMAIL)
	@Column
	private String email;
	
	@NotNull(message=MensajesEntidades.ENTIDAD_USUARIO_NOMBRE+MensajesEntidades.VALIDACION_VALOR_NULO)
	@Column
	private String nombre;
	
	@NotNull(message=MensajesEntidades.ENTIDAD_USUARIO_APELLIDO+MensajesEntidades.VALIDACION_VALOR_NULO)
	@Column
	private String apellido;
	
	@Id
	@NotNull(message=MensajesEntidades.ENTIDAD_USUARIO_CEDULA+MensajesEntidades.VALIDACION_VALOR_NULO)
	@Size(min=8,max=9,message=MensajesEntidades.ENTIDAD_USUARIO_CEDULA+MensajesEntidades.VALIDACION_STRING_VALOR_LONGITUD+ "8 y 9")
	@Pattern(regexp="[V,E][0-9]*",message=MensajesEntidades.ENTIDAD_USUARIO_CEDULA+MensajesEntidades.VALIDACION_STRING_FORMATO_VENEZOLANO)
	@Column
	private String cedula;
	
	@Column
	private String direccion;
	
	@NotNull(message=MensajesEntidades.ENTIDAD_USUARIO_TELEFONO+MensajesEntidades.VALIDACION_VALOR_NULO)
	@Size(min=11,max=11,message=MensajesEntidades.ENTIDAD_USUARIO_TELEFONO+MensajesEntidades.VALIDACION_STRING_VALOR_LONGITUD+ "11 y 11")
	@Pattern(regexp="[0-9]*",message=MensajesEntidades.ENTIDAD_USUARIO_TELEFONO+MensajesEntidades.VALIDACION_STRING_VALOR_NUMERICO)
	@Column
	private String telefono;
	
	@Column(name="estatus_registro")
	private EstatusRegistro estatusRegistro=EstatusRegistro.INACTIVO;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
	@Cascade(CascadeType.ALL)
	private Set<Pedido> pedidos = new HashSet<Pedido>(0);
	
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
	
	public EstatusRegistro getEstatusRegistro() {
		return estatusRegistro;
	}

	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public void setEstatusRegistro(EstatusRegistro estatusRegistro) {
		this.estatusRegistro = estatusRegistro;
	}

	public Set<ConstraintViolation<Usuario>> validarInstancia()
	{
		ValidatorFactory factory= Validation.buildDefaultValidatorFactory();
		Validator validator=factory.getValidator();
		return validator.validate(this);
	}
	
}
