package idisoft.restos.entities;

import idisoft.restos.util.ConstantesEntidades;

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
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.jboss.util.HashCode;

@SuppressWarnings("serial")
@Entity
@Table(name="catalogos",catalog="restos")
public class Catalogo implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotNull(message=ConstantesEntidades.ENTIDAD_CATALOGO_NOMBRE+ConstantesEntidades.VALIDACION_VALOR_NULO)
	@Size(min=10,max=100,message=ConstantesEntidades.ENTIDAD_CATALOGO_NOMBRE+ConstantesEntidades.VALIDACION_STRING_VALOR_LONGITUD+"10 y 100")
	@Column
	private String nombre;
	
	@NotNull(message=ConstantesEntidades.ENTIDAD_CATALOGO_ESTATUS+ConstantesEntidades.VALIDACION_VALOR_NULO)
	@Column
	private EstatusCatalogo estatus;
	
	@NotNull(message=ConstantesEntidades.ENTIDAD_CATALOGO_SEDE+ConstantesEntidades.VALIDACION_VALOR_NULO)
	@Valid
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="sede")	
	private Sede sede;
	
	@Column(name="estatus_registro")
	private EstatusRegistro estatusRegistro=EstatusRegistro.INACTIVO;
	
	@Valid
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "catalogo")
	@Cascade(CascadeType.ALL)
	private Set<ElementoCatalogo> elementos = new HashSet<ElementoCatalogo>(0);
	
	public Catalogo()
	{
		this.nombre="";
		this.estatus=EstatusCatalogo.NODISPONIBLE;
		this.sede=new Sede();
		this.estatusRegistro=EstatusRegistro.INACTIVO;
		this.elementos = new HashSet<ElementoCatalogo>(0);
	}
	
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
	
	public EstatusCatalogo getEstatus() {
		return estatus;
	}
	public void setEstatus(EstatusCatalogo estatus) {
		this.estatus = estatus;
	}
	
	public Sede getSede() {
		return sede;
	}
	public void setSede(Sede sede) {
		this.sede = sede;
	}
	
	public EstatusRegistro getEstatusRegistro() {
		return estatusRegistro;
	}
	public void setEstatusRegistro(EstatusRegistro estatusRegistro) {
		this.estatusRegistro = estatusRegistro;
	}
	public Set<ElementoCatalogo> getElementos() {
		return elementos;
	}
	public void setElementos(Set<ElementoCatalogo> elementos) {
		this.elementos = elementos;
	}
	
	public Set<ConstraintViolation<Catalogo>> validarInstancia()
	{
		ValidatorFactory factory= Validation.buildDefaultValidatorFactory();
		Validator validator=factory.getValidator();
		return validator.validate(this);
	}
	
	public boolean isOfSede(Sede sede)
	{
		return this.sede.equals(sede);
	}
	
	@Override
	public boolean equals(Object o)
	{
		boolean check=false;
		if(o instanceof Catalogo)
		{
			Catalogo c=(Catalogo) o;
			check= this.id==c.getId() &&
					this.nombre.equals(c.getNombre()) &&
					this.estatus==c.getEstatus() &&
					this.sede.equals(c.getSede());
		}
		return check;
	}
	
	/*
	@Override
	public int hashCode()
	{
		HashCode hc=new HashCode(ConstantesEntidades.ENTIDAD_CATALOGO_HASHCODE_PRIME);
		hc.add(this.nombre);
		hc.add(this.sede.getNombre());
		return hc.hashCode();
	}
	*/
	
}

