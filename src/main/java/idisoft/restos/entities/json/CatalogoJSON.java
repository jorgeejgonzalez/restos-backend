package idisoft.restos.entities.json;

import idisoft.restos.entities.ElementoCatalogo;
import idisoft.restos.entities.EstatusCatalogo;
import idisoft.restos.entities.Catalogo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@SuppressWarnings("serial")

public class CatalogoJSON implements Serializable{
	
	private int id;
	
	private String nombre;
	
	private EstatusCatalogo estatus;
	
	private SedeJSON sede;
	
	private Set<ElementoCatalogoJSON> elementosCatalogo = new HashSet<ElementoCatalogoJSON>(0);
	
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
	
	public EstatusCatalogo getEstatus() {
		return estatus;
	}
	public void setEstatus(EstatusCatalogo estatus) {
		this.estatus = estatus;
	}
	
	public SedeJSON getSede() {
		return sede;
	}
	public void setSede(SedeJSON sede) {
		this.sede = sede;
	}
	
	public Set<ElementoCatalogoJSON> getElementosCatalogo() {
		return elementosCatalogo;
	}
	public void setElementosCatalogo(Set<ElementoCatalogoJSON> elementosMenu) {
		this.elementosCatalogo = elementosMenu;
	}
	
	public void parseCatalogoFromSede(Catalogo menu)
	{
		this.id=menu.getId();
		this.nombre=menu.getNombre();
		this.estatus=menu.getEstatus();
		this.sede=null;
		
		if(menu.getElementosCatalogo()!=null)
		{
			if(menu.getElementosCatalogo().size()>0)
			{
				Iterator<ElementoCatalogo> iterator=menu.getElementosCatalogo().iterator();
				while(iterator.hasNext())
				{
					ElementoCatalogo em=iterator.next();
					ElementoCatalogoJSON emj=new ElementoCatalogoJSON();
					emj.parseElementoFromCatalogo(em);
					this.elementosCatalogo.add(emj);
				}
			}
			else
			{
				this.elementosCatalogo=null;
			}
			
		}
		else 
		{
			this.elementosCatalogo=null;
		}
		
	}
	
}

