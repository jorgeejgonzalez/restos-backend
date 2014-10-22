package idisoft.restos.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name="menus")
public class Menu implements Serializable{
	
	@Id
	@NotNull
	@Column(name="idmenus")
	private int id;
	
	@NotNull
	@Column
	private String nombre;
	
	@NotNull
	@Column
	private EstatusMenu estatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	@JoinColumn(name="sede")
	private Sede sede;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
	private Set<ElementoMenu> elementosMenu = new HashSet<ElementoMenu>(0);
	
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
	
	public Sede getSede() {
		return sede;
	}
	public void setSede(Sede sede) {
		this.sede = sede;
	}
	
	public Set<ElementoMenu> getElementosMenu() {
		return elementosMenu;
	}
	public void setElementosMenu(Set<ElementoMenu> elementosMenu) {
		this.elementosMenu = elementosMenu;
	}
	
}

