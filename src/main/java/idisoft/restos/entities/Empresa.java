package idisoft.restos.entities;

import idisoft.restos.util.ConstantesEntidades;

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
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.jboss.util.HashCode;

@SuppressWarnings("serial")
@Entity
@Table(name="empresas")
public class Empresa implements Serializable {
	
	@Id
	@NotNull(message=ConstantesEntidades.ENTIDAD_EMPRESA_RIF+ConstantesEntidades.VALIDACION_VALOR_NULO)
	@Size(min=10,max=10,message=ConstantesEntidades.ENTIDAD_EMPRESA_RIF+ConstantesEntidades.VALIDACION_STRING_VALOR_LONGITUD+"10 y 10")
	@Pattern(regexp="[J,V,E,G][0-9]*",message=ConstantesEntidades.ENTIDAD_EMPRESA_RIF+ConstantesEntidades.VALIDACION_STRING_FORMATO_VENEZOLANO)
	@Column
	private String rif;
	
	@NotNull(message=ConstantesEntidades.ENTIDAD_EMPRESA_RAZON_SOCIAL+ConstantesEntidades.VALIDACION_VALOR_NULO)
	@Size(min=10,max=100,message=ConstantesEntidades.ENTIDAD_EMPRESA_RAZON_SOCIAL+ConstantesEntidades.VALIDACION_STRING_VALOR_LONGITUD+"10 y 100")
	@Column(name="razon_social")
	private String razonSocial;
	
	@NotNull(message=ConstantesEntidades.ENTIDAD_EMPRESA_DIRECCION_FISCAL+ConstantesEntidades.VALIDACION_VALOR_NULO)
	@Size(min=10,max=100,message=ConstantesEntidades.ENTIDAD_EMPRESA_DIRECCION_FISCAL+ConstantesEntidades.VALIDACION_STRING_VALOR_LONGITUD+"10 y 100")
	@Column(name="direccion_fiscal")
	private String direccionFiscal;
	
	@Column(name="estatus_registro")
	private EstatusRegistro estatusRegistro=EstatusRegistro.INACTIVO;
	
	@Valid
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
	
	@Override
	public boolean equals(Object o)
	{
		boolean check=false;
		if(o instanceof Empresa)
		{
			Empresa e=(Empresa) o;
			check= this.rif.equals(e.getRif()) &&
					this.razonSocial.equals(e.getRazonSocial()) &&
					this.direccionFiscal.equals(e.getDireccionFiscal());
		}
		return check;
	}
	
	@Override
	public int hashCode()
	{
		HashCode hc=new HashCode(ConstantesEntidades.ENTIDAD_EMPRESA_HASHCODE_PRIME);
		hc.add(this.rif);
		hc.add(this.razonSocial);
		hc.add(this.direccionFiscal);
		return hc.hashCode();
	}
	
}
