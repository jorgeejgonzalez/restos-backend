package idisoft.restos.entities.json;

import idisoft.restos.entities.EstatusMenu;

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

public class MenuJSON implements Serializable{
	
	private int id;
	
	private String nombre;
	
	private EstatusMenu estatus;
	
	private SedeJSON sede;
	
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
	
}

