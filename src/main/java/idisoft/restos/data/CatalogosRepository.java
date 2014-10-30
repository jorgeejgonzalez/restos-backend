package idisoft.restos.data;

import idisoft.restos.entities.Catalogo;
import idisoft.restos.entities.EstatusRegistro;
import idisoft.restos.entities.Sede;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CatalogosRepository extends Repository implements ListRecords {

	public Catalogo findById(int id)
	{
		return (Catalogo)findByIntKey(Catalogo.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Catalogo> findAll() {		
		return (List<Catalogo>)findAll(Catalogo.class,"id");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Catalogo> findAllActive() {		
		return (List<Catalogo>)findAllFiltered(Catalogo.class,"id",EstatusRegistro.ACTIVO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Catalogo> findAllInactive() {		
		return (List<Catalogo>)findAllFiltered(Catalogo.class,"id",EstatusRegistro.INACTIVO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Catalogo> findAllDeleted() {		
		return (List<Catalogo>)findAllFiltered(Catalogo.class,"id",EstatusRegistro.ELIMINADO);
	}
	
	public List<Catalogo> findAllActiveBySede(int sedeid) {		
		Sede sede=(Sede)findByIntKey(Sede.class, sedeid);
		List<Catalogo> retorno=new ArrayList<Catalogo>();
		
		if(sede==null)
		{
			retorno=null;
		}
		else
		{
			if(sede.getCatalogos()==null)
			{
				retorno=null;
			}
			else if(sede.getCatalogos().isEmpty())
			{
				retorno=null;				
			}
			else
			{
				Iterator<Catalogo> iterator=sede.getCatalogos().iterator();
				while(iterator.hasNext())
				{
					Catalogo catalogo=iterator.next();
					if(catalogo.getEstatusRegistro()==EstatusRegistro.ACTIVO)
					{
						retorno.add(catalogo);
					}
				}
			}
		}
		
		return retorno;
	}

}
