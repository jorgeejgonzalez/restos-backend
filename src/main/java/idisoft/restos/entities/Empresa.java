package idisoft.restos.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name="empresas",catalog="restos")
public class Empresa implements Serializable {
	
	@Id
	@NotNull
	@Size(min=10,max=10)
	@Pattern(regexp="[J,V,E,G][0-9]*",message="debe cumplir el formato de rif sin guiones")
	@Column
	private String rif;
	
	@NotNull
	@Size(min=10,max=100)
	@Column(name="razon_social")
	private String razonSocial;
	
	@NotNull
	@Size(min=10,max=100)
	@Column(name="direccion_fiscal")
	private String direccionFiscal;
	
	@Column(name="estatus_registro")
	private EstatusRegistro estatusRegistro=EstatusRegistro.INACTIVO;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
	private Set<Sede> sedes = new HashSet<Sede>(0);
		
	public Empresa()
	{
		
	}
	
	public Empresa(String rif,
			String razonsocial,
			String direccionfiscal)
	{
		this.rif=rif;
		this.razonSocial=razonsocial;
		this.direccionFiscal=direccionfiscal;
	}
	
	public String getRif() {
		return rif;
	}
	public void setRif(String rif) {
		this.rif = rif;
	}
	
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
	public String getDireccionFiscal() {
		return direccionFiscal;
	}
	
	public void setDireccionFiscal(String direccionFiscal) {
		this.direccionFiscal = direccionFiscal;
	}
	
	public EstatusRegistro getEstatusRegistro() {
		return estatusRegistro;
	}

	public void setEstatusRegistro(EstatusRegistro estatusRegistro) {
		this.estatusRegistro = estatusRegistro;
	}

	public Set<Sede> getSedes() {
		return sedes;
	}

	public void setSedes(Set<Sede> sedes) {
		this.sedes = sedes;
	}

	public Set<ConstraintViolation<Empresa>> validarInstancia()
	{
		ValidatorFactory factory= Validation.buildDefaultValidatorFactory();
		Validator validator=factory.getValidator();
		return validator.validate(this);
	}
	
}
