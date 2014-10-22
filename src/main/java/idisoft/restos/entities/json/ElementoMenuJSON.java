package idisoft.restos.entities.json;

import idisoft.restos.entities.ElementoMenu;
import idisoft.restos.entities.EstatusMenu;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ElementoMenuJSON implements Serializable {
	
	private int id;
	
	private String nombre;
	
	private String descripcion;
	
	private float precio;
	
	private EstatusMenu estatus;
	
	private int unidades;
		
	private MenuJSON menu;

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

	public MenuJSON getMenu() {
		return menu;
	}

	public void setMenu(MenuJSON menu) {
		this.menu = menu;
	}
	
	public void parseElementoFromMenu(ElementoMenu elementomenu)
	{
		this.id=elementomenu.getId();
		this.nombre=elementomenu.getNombre();
		this.descripcion=elementomenu.getDescripcion();
		this.precio=elementomenu.getPrecio();
		this.unidades=elementomenu.getUnidades();
		this.estatus=elementomenu.getEstatus();
		this.menu=null;
	}

}
