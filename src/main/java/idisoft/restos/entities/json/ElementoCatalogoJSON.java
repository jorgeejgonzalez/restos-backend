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
	
	private CatalogoJSON catalogo;

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

	public CatalogoJSON getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(CatalogoJSON catalogo) {
		this.catalogo = catalogo;
	}
	
	public void parseElementoFromCatalogo(ElementoCatalogo elementocatalogo)
	{
		this.id=elementocatalogo.getId();
		this.nombre=elementocatalogo.getNombre();
		this.descripcion=elementocatalogo.getDescripcion();
		this.precio=elementocatalogo.getPrecio();
		this.estatus=elementocatalogo.getEstatus();
		this.catalogo=null;
	}

}
