package idisoft.restos.data;

import idisoft.restos.entities.Catalogo;
import idisoft.restos.entities.ElementoCatalogo;
import idisoft.restos.entities.EstatusRegistro;
import idisoft.restos.entities.Sede;
import idisoft.restos.util.ConstantesEntidades;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.NoResultException;

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
	public List<Catalogo> findAll() 
	{		
		return (List<Catalogo>)findAll(Catalogo.class,"id");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Catalogo> findAllActive() throws NoResultException 
	{		
		return (List<Catalogo>)findAllFiltered(Catalogo.class,"id",EstatusRegistro.ACTIVO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Catalogo> findAllInactive() throws NoResultException 
	{		
		return (List<Catalogo>)findAllFiltered(Catalogo.class,"id",EstatusRegistro.INACTIVO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Catalogo> findAllDeleted() throws NoResultException 
	{		
		return (List<Catalogo>)findAllFiltered(Catalogo.class,"id",EstatusRegistro.ELIMINADO);
	}
	
	private List<Catalogo> findAllFilteredBySede(int sedeid, EstatusRegistro estatus) throws NoResultException 
	{		
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
				throw new NoResultException(ConstantesEntidades.MENSAJE_LISTA_NULA);
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
				if(retorno.isEmpty())
				{
					throw new NoResultException(ConstantesEntidades.MENSAJE_LISTA_VACIA);
				}
			}
			else
			{
				throw new NoResultException(ConstantesEntidades.MENSAJE_LISTA_VACIA);
			}
		}
		
		return retorno;
	}
	
	public List<Catalogo> findAllActiveBySede(int sedeid) throws NoResultException
	{
		return findAllFilteredBySede(sedeid, EstatusRegistro.ACTIVO);
	}
	
	public List<Catalogo> findAllInactiveBySede(int sedeid) throws NoResultException
	{
		return findAllFilteredBySede(sedeid, EstatusRegistro.INACTIVO);
	}
	
	public List<Catalogo> findAllDeletedBySede(int sedeid) throws NoResultException
	{
		return findAllFilteredBySede(sedeid, EstatusRegistro.ELIMINADO);
	}

}
