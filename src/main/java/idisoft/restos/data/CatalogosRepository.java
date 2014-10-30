package idisoft.restos.data;

import idisoft.restos.entities.Catalogo;
import idisoft.restos.entities.ElementoCatalogo;
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
	
	public Sede findSedeById(int id)
	{
		return (Sede)findByIntKey(Sede.class, id);
	}
	
	public ElementoCatalogo findElementoById(int id)
	{
		return (ElementoCatalogo)findByIntKey(ElementoCatalogo.class, id);
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
	
	private List<Catalogo> findAllFilteredBySede(int sedeid, EstatusRegistro estatus) {		
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
			else if(!sede.getCatalogos().isEmpty())
			{
				Iterator<Catalogo> iterator=sede.getCatalogos().iterator();
				while(iterator.hasNext())
				{
					Catalogo catalogo=iterator.next();
					if(catalogo.getEstatusRegistro()==estatus)
					{
						retorno.add(catalogo);
					}
				}
			}
		}
		
		return retorno;
	}
	
	public List<Catalogo> findAllActiveBySede(int sedeid)
	{
		return findAllFilteredBySede(sedeid, EstatusRegistro.ACTIVO);
	}
	
	public List<Catalogo> findAllInactiveBySede(int sedeid)
	{
		return findAllFilteredBySede(sedeid, EstatusRegistro.INACTIVO);
	}
	
	public List<Catalogo> findAllDeletedBySede(int sedeid)
	{
		return findAllFilteredBySede(sedeid, EstatusRegistro.ELIMINADO);
	}

}
