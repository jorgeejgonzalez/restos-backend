package idisoft.restos.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name="menus_elementos")
public class ElementoMenu implements Serializable {
	
	@Id
	@NotNull
	@Column(name="idmenus_elementos")
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
	private EstatusMenu estatus;
	
	@NotNull
	@Column
	private int unidades;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	@JoinColumn(name="menu")
	private Menu menu;
	
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
	
	public EstatusMenu getEstatus() {
		return estatus;
	}
	public void setEstatus(EstatusMenu estatus) {
		this.estatus = estatus;
	}
	
	public int getUnidades() {
		return unidades;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
}
