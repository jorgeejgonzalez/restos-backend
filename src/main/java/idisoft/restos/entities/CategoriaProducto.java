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

@SuppressWarnings("serial")
@Entity
@Table(name="categorias_productos")
public class CategoriaProducto implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotNull
	@Size(min=5,max=50)
	@Column
	private String nombre;
	
	@Column(name="estatus_registro")
	private EstatusRegistro estatusRegistro=EstatusRegistro.INACTIVO;
	
	@Valid
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria")
	@Cascade(CascadeType.ALL)
	private Set<Producto> productos = new HashSet<Producto>(0);
	
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

	public EstatusRegistro getEstatusRegistro() {
		return estatusRegistro;
	}

	public void setEstatusRegistro(EstatusRegistro estatusRegistro) {
		this.estatusRegistro = estatusRegistro;
	}

	public Set<Producto> getProductos() {
		return productos;
	}

	public void setProductos(Set<Producto> productos) {
		this.productos = productos;
	}
	
	public Set<ConstraintViolation<CategoriaProducto>> validarInstancia()
	{
		ValidatorFactory factory= Validation.buildDefaultValidatorFactory();
		Validator validator=factory.getValidator();
		return validator.validate(this);
	}

}
