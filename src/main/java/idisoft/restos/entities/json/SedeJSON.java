package idisoft.restos.entities.json;

import idisoft.restos.entities.Catalogo;
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
	
	private EmpresaJSON empresa;
	
	private Set<CatalogoJSON> catalogos = new HashSet<CatalogoJSON>(0);
	
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
	
	public EmpresaJSON getEmpresa() {
		return empresa;
	}
	public void setEmpresa(EmpresaJSON empresa) {
		this.empresa = empresa;
	}
	
	public Set<CatalogoJSON> getCatalogos() {
		return catalogos;
	}
	public void setCatalogos(Set<CatalogoJSON> catalogos) {
		this.catalogos = catalogos;
	}
	
	public void parseSedeFromEmpresa(Sede sede)
	{
		this.rif=sede.getRif();
		this.nombre=sede.getNombre();
		this.direccionFisica=sede.getDireccionFisica();
		this.email=sede.getEmail();
		this.telefono=sede.getTelefono();
					
		this.empresa=null;
		
		if(sede.getCatalogos()!=null)
		{
			if(sede.getCatalogos().size()>0)
			{
				Iterator<Catalogo> iterator=sede.getCatalogos().iterator();
				while(iterator.hasNext())
				{
					Catalogo m=iterator.next();
					CatalogoJSON mj=new CatalogoJSON();
					mj.parseCatalogoFromSede(m);
					this.catalogos.add(mj);
				}
			}
			else
			{
				this.catalogos=null;
			}
			
		}
		else 
		{
			this.catalogos=null;
		}
	}
	
	public void parseSede(Sede sede)
	{
		this.rif=sede.getRif();
		this.nombre=sede.getNombre();
		this.direccionFisica=sede.getDireccionFisica();
		this.email=sede.getEmail();
		this.telefono=sede.getTelefono();
		
		this.empresa=new EmpresaJSON();
		this.empresa.parseEmpresaFromSede(sede.getEmpresa());
		
		if(sede.getCatalogos()!=null)
		{
			if(sede.getCatalogos().size()>0)
			{
				for(int i=0; i<sede.getCatalogos().size();++i)
				{
					Catalogo m=sede.getCatalogos().iterator().next();
					CatalogoJSON mj=new CatalogoJSON();
					mj.parseCatalogoFromSede(m);
					this.catalogos.add(mj);
				}
			}
			else
			{
				this.catalogos=null;
			}
			
		}
		else 
		{
			this.catalogos=null;
		}
		
	}
}
