package idisoft.restos.entities.json;

import idisoft.restos.entities.ElementoCatalogo;
import idisoft.restos.entities.EstatusCatalogo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ElementoCatalogoJSON implements Serializable {
	
	private int id;
	
	private String nombre;
	
	private String descripcion;
	
	private float precio;
	
	private EstatusCatalogo estatus;
	
	private CatalogoJSON menu;

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

	public CatalogoJSON getMenu() {
		return menu;
	}

	public void setMenu(CatalogoJSON menu) {
		this.menu = menu;
	}
	
	public void parseElementoFromMenu(ElementoCatalogo elementomenu)
	{
		this.id=elementomenu.getId();
		this.nombre=elementomenu.getNombre();
		this.descripcion=elementomenu.getDescripcion();
		this.precio=elementomenu.getPrecio();
		this.estatus=elementomenu.getEstatus();
		this.menu=null;
	}

}
