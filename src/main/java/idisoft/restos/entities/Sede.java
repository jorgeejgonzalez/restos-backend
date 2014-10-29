package idisoft.restos.entities;

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
public class Sede extends Registro implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotNull
	@Size(min=10,max=10)
	@Pattern(regexp="[J,V,E,G][0-9]*",message="debe cumplir el formato de rif sin guiones")	
	@Column
	private String rif;
	
	@NotNull
	@Size(min=6,max=50)
	@Column
	private String nombre;
	
	@NotNull
	@Email
	@Column
	private String email;
	
	@NotNull
	@Column(name="direccion_fisica")
	private String direccionFisica;
	
	@NotNull
	@Size(min=11,max=11)
	@Pattern(regexp="[0-9]*",message="solo acepta numeros")
	@Column
	private String telefono;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	@JoinColumn(name="empresa")
	private Empresa empresa;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sede")
	private Set<Catalogo> menus = new HashSet<Catalogo>(0);
		
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
	
	public Set<Catalogo> getMenus() {
		return menus;
	}
	public void setMenus(Set<Catalogo> menus) {
		this.menus = menus;
	}
	public Set<ConstraintViolation<Sede>> validarInstancia()
	{
		ValidatorFactory factory= Validation.buildDefaultValidatorFactory();
		Validator validator=factory.getValidator();
		return validator.validate(this);
	}
	

}
