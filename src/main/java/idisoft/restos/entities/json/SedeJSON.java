package idisoft.restos.entities.json;

import idisoft.restos.entities.Menu;
import idisoft.restos.entities.Sede;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@SuppressWarnings("serial")
public class SedeJSON implements Serializable{

	private String rif;
	
	private String nombre;
	
	private String email;
	
	private String direccionFisica;
	
	private String telefono;
	
	private RestauranteJSON restaurante;
	
	private Set<MenuJSON> menus = new HashSet<MenuJSON>(0);
	
	public String getRif() {
		return rif;
	}
	public void setRif(String rif) {
		this.rif = rif;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getDireccionFisica() {
		return direccionFisica;
	}
	public void setDireccionFisica(String direccionFisica) {
		this.direccionFisica = direccionFisica;
	}
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public RestauranteJSON getRestaurante() {
		return restaurante;
	}
	public void setRestaurante(RestauranteJSON restaurante) {
		this.restaurante = restaurante;
	}
	
	public Set<MenuJSON> getMenus() {
		return menus;
	}
	public void setMenus(Set<MenuJSON> menus) {
		this.menus = menus;
	}
	
	public void parseSedeFromRestaurante(Sede sede)
	{
		this.rif=sede.getRif();
		this.nombre=sede.getNombre();
		this.direccionFisica=sede.getDireccionFisica();
		this.email=sede.getEmail();
		this.telefono=sede.getTelefono();
					
		this.restaurante=null;
		
		if(sede.getMenus()!=null)
		{
			if(sede.getMenus().size()>0)
			{
				Iterator<Menu> iterator=sede.getMenus().iterator();
				while(iterator.hasNext())
				{
					Menu m=iterator.next();
					MenuJSON mj=new MenuJSON();
					mj.parseMenuFromSede(m);
					this.menus.add(mj);
				}
			}
			else
			{
				this.menus=null;
			}
			
		}
		else 
		{
			this.menus=null;
		}
	}
	
	public void parseSede(Sede sede)
	{
		this.rif=sede.getRif();
		this.nombre=sede.getNombre();
		this.direccionFisica=sede.getDireccionFisica();
		this.email=sede.getEmail();
		this.telefono=sede.getTelefono();
		
		this.restaurante=new RestauranteJSON();
		this.restaurante.parseRestauranteFromSede(sede.getRestaurante());
		
		if(sede.getMenus()!=null)
		{
			if(sede.getMenus().size()>0)
			{
				for(int i=0; i<sede.getMenus().size();++i)
				{
					Menu m=sede.getMenus().iterator().next();
					MenuJSON mj=new MenuJSON();
					mj.parseMenuFromSede(m);
					this.menus.add(mj);
				}
			}
			else
			{
				this.menus=null;
			}
			
		}
		else 
		{
			this.menus=null;
		}
		
	}
}
