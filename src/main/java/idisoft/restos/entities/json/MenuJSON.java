package idisoft.restos.entities.json;

import idisoft.restos.entities.ElementoMenu;
import idisoft.restos.entities.EstatusMenu;
import idisoft.restos.entities.Menu;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@SuppressWarnings("serial")

public class MenuJSON implements Serializable{
	
	private int id;
	
	private String nombre;
	
	private EstatusMenu estatus;
	
	private SedeJSON sede;
	
	private Set<ElementoMenuJSON> elementosMenu = new HashSet<ElementoMenuJSON>(0);
	
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
	
	public EstatusMenu getEstatus() {
		return estatus;
	}
	public void setEstatus(EstatusMenu estatus) {
		this.estatus = estatus;
	}
	
	public SedeJSON getSede() {
		return sede;
	}
	public void setSede(SedeJSON sede) {
		this.sede = sede;
	}
	
	public Set<ElementoMenuJSON> getElementosMenu() {
		return elementosMenu;
	}
	public void setElementosMenu(Set<ElementoMenuJSON> elementosMenu) {
		this.elementosMenu = elementosMenu;
	}
	
	public void parseMenuFromSede(Menu menu)
	{
		this.id=menu.getId();
		this.nombre=menu.getNombre();
		this.estatus=menu.getEstatus();
		this.sede=null;
		
		if(menu.getElementosMenu()!=null)
		{
			if(menu.getElementosMenu().size()>0)
			{
				Iterator<ElementoMenu> iterator=menu.getElementosMenu().iterator();
				while(iterator.hasNext())
				{
					ElementoMenu em=iterator.next();
					ElementoMenuJSON emj=new ElementoMenuJSON();
					emj.parseElementoFromMenu(em);
					this.elementosMenu.add(emj);
				}
			}
			else
			{
				this.elementosMenu=null;
			}
			
		}
		else 
		{
			this.elementosMenu=null;
		}
		
	}
	
}

