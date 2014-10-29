package idisoft.restos.entities.json;

import idisoft.restos.entities.Empresa;
import idisoft.restos.entities.Sede;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@SuppressWarnings("serial")
public class EmpresaJSON implements Serializable {
	
	private String rif;
	
	private String razonSocial;
	
	private String direccionFiscal;
	
	private Set<SedeJSON> sedes = new HashSet<SedeJSON>(0);
		
	public EmpresaJSON()
	{
		
	}
	
	public EmpresaJSON(String rif,
			String razonsocial,
			String direccionfiscal)
	{
		this.rif=rif;
		this.razonSocial=razonsocial;
		this.direccionFiscal=direccionfiscal;
	}
	
	public String getRif() {
		return rif;
	}
	public void setRif(String rif) {
		this.rif = rif;
	}
	
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
	public String getDireccionFiscal() {
		return direccionFiscal;
	}
	
	public void setDireccionFiscal(String direccionFiscal) {
		this.direccionFiscal = direccionFiscal;
	}
	
	public Set<SedeJSON> getSedes() {
		return sedes;
	}

	public void setSedes(Set<SedeJSON> sedes) {
		this.sedes = sedes;
	}

	public void parseEmpresa(Empresa empresa)
	{
		this.rif=empresa.getRif();
		this.razonSocial=empresa.getRazonSocial();
		this.direccionFiscal=empresa.getDireccionFiscal();
		if(empresa.getSedes()!=null)
		{
			if(empresa.getSedes().size()>0)
			{
				Iterator<Sede> iterator=empresa.getSedes().iterator();
				while(iterator.hasNext())
				{
					Sede sede=iterator.next();
					SedeJSON s=new SedeJSON();
					s.parseSedeFromEmpresa(sede);
					this.sedes.add(s);
				}
			}
			else
			{
				this.sedes=null;
			}
		}
		else
		{
			this.sedes=null;
		}
	}
	
	public void parseEmpresaFromSede(Empresa empresa)
	{
		this.rif=empresa.getRif();
		this.razonSocial=empresa.getRazonSocial();
		this.direccionFiscal=empresa.getDireccionFiscal();
		this.sedes=null;
	}
	
}
