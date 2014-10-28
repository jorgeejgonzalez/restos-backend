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

@SuppressWarnings("serial")
@Entity
@Table(name="catalogo_elementos")
public class ElementoCatalogo extends Registro implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotNull
	@Column
	private String nombre;
	
	@NotNull
	@Column
	private String descripcion;
	
	@NotNull
	@Column
	private float precio;
	
	@NotNull
	@Column
	private EstatusCatalogo estatus;
	
	@NotNull
	@Column
	private int unidades;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	@JoinColumn(name="menu")
	private Catalogo menu;
	
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
	
	public int getUnidades() {
		return unidades;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	
	public Catalogo getMenu() {
		return menu;
	}
	public void setMenu(Catalogo menu) {
		this.menu = menu;
	}
	
}
