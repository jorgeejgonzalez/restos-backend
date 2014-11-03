package idisoft.restos.entities;

import idisoft.restos.util.MensajesEntidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@SuppressWarnings("serial")
@Entity
@Table(name="sedes", catalog="restos")
public class Sede implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotNull(message=MensajesEntidades.ENTIDAD_SEDE_RIF+MensajesEntidades.VALIDACION_VALOR_NULO)
	@Size(min=10,max=10,message=MensajesEntidades.ENTIDAD_SEDE_RIF+MensajesEntidades.VALIDACION_STRING_FORMATO_VENEZOLANO+"10 y 10")
	@Pattern(regexp="[J,V,E,G][0-9]*",message=MensajesEntidades.ENTIDAD_SEDE_RIF+MensajesEntidades.VALIDACION_STRING_FORMATO_VENEZOLANO)	
	@Column
	private String rif;
	
	@NotNull(message=MensajesEntidades.ENTIDAD_SEDE_NOMBRE+MensajesEntidades.VALIDACION_VALOR_NULO)
	@Size(min=6,max=50,message=MensajesEntidades.ENTIDAD_SEDE_NOMBRE+MensajesEntidades.VALIDACION_STRING_VALOR_LONGITUD+"6 y 50")
	@Column
	private String nombre;
	
	@NotNull(message=MensajesEntidades.ENTIDAD_SEDE_EMAIL+MensajesEntidades.VALIDACION_VALOR_NULO)
	@Email(message=MensajesEntidades.ENTIDAD_SEDE_EMAIL+MensajesEntidades.VALIDACION_STRING_FORMATO_EMAIL)
	@Column
	private String email;
	
	@NotNull(message=MensajesEntidades.ENTIDAD_SEDE_DIRECCION_FISICA+MensajesEntidades.VALIDACION_VALOR_NULO)
	@Column(name="direccion_fisica")
	private String direccionFisica;
	
	@NotNull(message=MensajesEntidades.ENTIDAD_SEDE_TELEFONO+MensajesEntidades.VALIDACION_VALOR_NULO)
	@Size(min=11,max=11,message=MensajesEntidades.ENTIDAD_SEDE_TELEFONO+MensajesEntidades.VALIDACION_STRING_VALOR_LONGITUD+"11 y 11")
	@Pattern(regexp="[0-9]*",message=MensajesEntidades.ENTIDAD_SEDE_TELEFONO+MensajesEntidades.VALIDACION_STRING_VALOR_NUMERICO)
	@Column
	private String telefono;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull(message=MensajesEntidades.ENTIDAD_SEDE_EMPRESA+MensajesEntidades.VALIDACION_VALOR_NULO)
	@Valid
	@JoinColumn(name="empresa")
	private Empresa empresa;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sede")
	private Set<Catalogo> catalogos = new HashSet<Catalogo>(0);
	
	@Column(name="estatus_registro")
	private EstatusRegistro estatusRegistro=EstatusRegistro.INACTIVO;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getRif() {
		return rif;
	}
	public void setRif(String rif) {
		this.rif = rif;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getDireccionFisica() {
		return direccionFisica;
	}
	public void setDireccionFisica(String direccionFisica) {
		this.direccionFisica = direccionFisica;
	}
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public Set<Catalogo> getCatalogos() {
		return catalogos;
	}
	public void setCatalogos(Set<Catalogo> catalogos) {
		this.catalogos = catalogos;
	}
	public EstatusRegistro getEstatusRegistro() {
		return estatusRegistro;
	}
	public void setEstatusRegistro(EstatusRegistro estatusRegistro) {
		this.estatusRegistro = estatusRegistro;
	}
	
	public Set<ConstraintViolation<Sede>> validarInstancia()
	{
		ValidatorFactory factory= Validation.buildDefaultValidatorFactory();
		Validator validator=factory.getValidator();
		return validator.validate(this);
	}
	
	public boolean isOfEmpresa(Empresa empresa)
	{
		return this.empresa.getRif().equals(empresa.getRif());
	}

}
