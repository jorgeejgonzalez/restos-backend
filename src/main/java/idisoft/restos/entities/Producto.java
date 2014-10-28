package idisoft.restos.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name="productos")
public class Producto extends Registro implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;	
	
	@NotNull
	@Size(min=10,max=50)
	@Column
	private String nombre;
	
	@Size(max=100)
	@Column
	private String descripcion;
	
	@NotNull
	@Column
	private float precio;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	@JoinColumn(name="categoria")
	private CategoriaProducto categoria;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	@JoinColumn(name="tipo")
	private TipoProducto tipo;
	
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
	public CategoriaProducto getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaProducto categoria) {
		this.categoria = categoria;
	}
	public TipoProducto getTipo() {
		return tipo;
	}
	public void setTipo(TipoProducto tipo) {
		this.tipo = tipo;
	}

}
