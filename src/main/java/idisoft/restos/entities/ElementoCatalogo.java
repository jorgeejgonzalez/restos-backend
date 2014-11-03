package idisoft.restos.entities;

import idisoft.restos.util.MensajesEntidades;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name="catalogo_elementos",catalog="restos")
public class ElementoCatalogo implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotNull(message=MensajesEntidades.ENTIDAD_ELEMENTOCATALOGO_NOMBRE+MensajesEntidades.VALIDACION_VALOR_NULO)
	@Column
	private String nombre;
	
	@NotNull(message=MensajesEntidades.ENTIDAD_ELEMENTOCATALOGO_DESCRIPCION+MensajesEntidades.VALIDACION_VALOR_NULO)
	@Size(min=10,max=200,message=MensajesEntidades.ENTIDAD_ELEMENTOCATALOGO_DESCRIPCION+MensajesEntidades.VALIDACION_STRING_VALOR_LONGITUD+"10 y 200")
	@Column
	private String descripcion;
	
	@NotNull(message=MensajesEntidades.ENTIDAD_ELEMENTOCATALOGO_PRECIO+MensajesEntidades.VALIDACION_VALOR_NULO)
	@Column
	private float precio;
	
	@NotNull(message=MensajesEntidades.ENTIDAD_ELEMENTOCATALOGO_ESTATUS+MensajesEntidades.VALIDACION_VALOR_NULO)
	@Column
	private EstatusCatalogo estatus;
	
	@Column(name="estatus_registro")
	private EstatusRegistro estatusRegistro=EstatusRegistro.INACTIVO;
	
	@NotNull(message=MensajesEntidades.ENTIDAD_ELEMENTOCATALOGO_CATALOGO+MensajesEntidades.VALIDACION_VALOR_NULO)
	@Valid
	@ManyToOne(fetch = FetchType.LAZY)	
	@JoinColumn(name="catalogo")
	private Catalogo catalogo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public EstatusCatalogo getEstatus() {
		return estatus;
	}
	public void setEstatus(EstatusCatalogo estatus) {
		this.estatus = estatus;
	}
	
	public Catalogo getCatalogo() {
		return catalogo;
	}
	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}
	
	public EstatusRegistro getEstatusRegistro() {
		return estatusRegistro;
	}
	public void setEstatusRegistro(EstatusRegistro estatusRegistro) {
		this.estatusRegistro = estatusRegistro;
	}
	
	public Set<ConstraintViolation<ElementoCatalogo>> validarInstancia()
	{
		ValidatorFactory factory= Validation.buildDefaultValidatorFactory();
		Validator validator=factory.getValidator();
		return validator.validate(this);
	}
	
	public boolean isOfCatalogo(Catalogo catalogo)
	{
		return this.catalogo.getId()==catalogo.getId();
	}
	
}
