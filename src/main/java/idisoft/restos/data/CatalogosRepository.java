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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;

public class CatalogosRepository extends Repository implements ListRecords {

	public Catalogo findById(int id) throws NoResultException 
	{
		return (Catalogo)findByIntKey(Catalogo.class, id);
	}
	
	public Sede findSedeById(int id) throws NoResultException 
	{
		return (Sede)findByIntKey(Sede.class, id);
	}
	
	public ElementoCatalogo findElementoById(int id) throws NoResultException 
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
	
	@SuppressWarnings("unchecked")
	private List<Catalogo> findAllFilteredBySede(int sedeid, EstatusRegistro estatus) throws NoResultException 
	{
		
		Sede sede=(Sede)findByIntKey(Sede.class, sedeid);
		
		if(sede.getEstatusRegistro()!=EstatusRegistro.ACTIVO)
		{
			throw new NoResultException(ConstantesEntidades.MENSAJE_ENTIDAD_NULA);
		}
		else
		{
			CriteriaBuilder cb=em.getCriteriaBuilder();
			CriteriaQuery<Catalogo> criteria=cb.createQuery(Catalogo.class);		
			Root<Catalogo> root=criteria.from(Catalogo.class);
			
			Predicate condition1=cb.equal(root.get("sede"), sede);
			Predicate condition2=cb.equal(root.get("estatusRegistro"), estatus);
			Predicate conditionsQuery=cb.and(condition1,condition2);
			
			criteria.select(root).where(conditionsQuery);
			
			List<Catalogo> retorno=(List<Catalogo>)findAllFiltered(criteria);
			
			return retorno;
		}
		
		
		
		
		/*	
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
		*/
		
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
