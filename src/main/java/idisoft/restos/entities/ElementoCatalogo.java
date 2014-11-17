package idisoft.restos.entities;

import idisoft.restos.util.ConstantesEntidades;

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

import org.jboss.util.HashCode;

@SuppressWarnings("serial")
@Entity
@Table(name="catalogo_elementos")
public class ElementoCatalogo implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotNull(message=ConstantesEntidades.ENTIDAD_ELEMENTOCATALOGO_NOMBRE+ConstantesEntidades.VALIDACION_VALOR_NULO)
	@Column
	private String nombre;
	
	@NotNull(message=ConstantesEntidades.ENTIDAD_ELEMENTOCATALOGO_DESCRIPCION+ConstantesEntidades.VALIDACION_VALOR_NULO)
	@Size(min=10,max=200,message=ConstantesEntidades.ENTIDAD_ELEMENTOCATALOGO_DESCRIPCION+ConstantesEntidades.VALIDACION_STRING_VALOR_LONGITUD+"10 y 200")
	@Column
	private String descripcion;
	
	@NotNull(message=ConstantesEntidades.ENTIDAD_ELEMENTOCATALOGO_PRECIO+ConstantesEntidades.VALIDACION_VALOR_NULO)
	@Column
	private float precio;
	
	@NotNull(message=ConstantesEntidades.ENTIDAD_ELEMENTOCATALOGO_ESTATUS+ConstantesEntidades.VALIDACION_VALOR_NULO)
	@Column
	private EstatusCatalogo estatus;
	
	@Column(name="estatus_registro")
	private EstatusRegistro estatusRegistro=EstatusRegistro.INACTIVO;
	
	@NotNull(message=ConstantesEntidades.ENTIDAD_ELEMENTOCATALOGO_CATALOGO+ConstantesEntidades.VALIDACION_VALOR_NULO)
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
	
	@Override
	public boolean equals(Object o)
	{
		boolean check=false;
		if(o instanceof ElementoCatalogo)
		{
			ElementoCatalogo e=(ElementoCatalogo) o;
			check= this.id==e.getId() &&
					this.nombre.equals(e.getNombre()) &&
					this.descripcion.equals(e.getDescripcion()) &&
					this.estatus==e.getEstatus() &&
					this.precio==e.getPrecio() &&
					this.catalogo.equals(e.getCatalogo());
		}
		return check;
	}
	/*
	@Override
	public int hashCode()
	{
		HashCode hc=new HashCode(ConstantesEntidades.ENTIDAD_ELEMENTOCATALOGO_HASHCODE_PRIME);
		hc.add(this.id);
		return hc.hashCode();
	}
	*/
}
