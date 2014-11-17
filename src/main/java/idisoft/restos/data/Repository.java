package idisoft.restos.data;

import idisoft.restos.entities.EstatusRegistro;
import idisoft.restos.util.ConstantesEntidades;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@ApplicationScoped
public abstract class Repository {
	
	@Inject
	protected EntityManager em;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Object findByStringKey(Class c, String s) throws NoResultException
	{
		
		Object r= em.find(c,s);
		if(r==null)
		{
			throw new NoResultException(ConstantesEntidades.MENSAJE_ENTIDAD_NULA);
		}
		return r;		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Object findByIntKey(Class c, int i) throws NoResultException
	{
		Object r= em.find(c,i);
		if(r==null)
		{
			throw new NoResultException(ConstantesEntidades.MENSAJE_ENTIDAD_NULA);
		}
		return r;
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Object findSingleByString(Class c, String attribute,String value) throws NoResultException
	{
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery criteria=cb.createQuery(c);		
		Root root=criteria.from(c);
		
		criteria.select(root).where(cb.equal(root.get(attribute), value));
		
		try
		{
			return em.createQuery(criteria).getSingleResult();
		}
		catch(NoResultException ex)
		{
			throw new NoResultException(ConstantesEntidades.MENSAJE_ENTIDAD_NULA);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Object findSingleByStringAndStatus(Class c, String attribute,String value, EstatusRegistro status) throws NoResultException
	{
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery criteria=cb.createQuery(c);		
		Root root=criteria.from(c);
		Predicate condition1=cb.equal(root.get(attribute), value);
		Predicate condition2=cb.equal(root.get("estatusRegistro"), status);
		Predicate conditionsQuery=cb.and(condition1,condition2);
		
		criteria.select(root).where(conditionsQuery);
		
		try
		{
			return em.createQuery(criteria).getSingleResult();
		}
		catch(NoResultException ex)
		{
			throw new NoResultException(ConstantesEntidades.MENSAJE_ENTIDAD_NULA);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List findListByString(Class c, String attribute,String value) throws NoResultException
	{
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery criteria=cb.createQuery(c);		
		Root root=criteria.from(c);
		
		criteria.select(root).where(cb.equal(root.get(attribute), value));
		
		try
		{
			List l=em.createQuery(criteria).getResultList();
			if(l.size()==0)
			{
				throw new NoResultException(ConstantesEntidades.MENSAJE_LISTA_NULA);
			}
				
			return l;
		}
		catch(NoResultException ex)
		{
			throw new NoResultException(ConstantesEntidades.MENSAJE_LISTA_NULA);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List findListByStringAndStatus(Class c, String attribute,String value, EstatusRegistro status) throws NoResultException
	{
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery criteria=cb.createQuery(c);		
		Root root=criteria.from(c);		
		Predicate condition1=cb.equal(root.get(attribute), value);
		Predicate condition2=cb.equal(root.get("estatusRegistro"), status);
		Predicate conditionsQuery=cb.and(condition1,condition2);
		
		criteria.select(root).where(conditionsQuery);
		
		try
		{
			List l=em.createQuery(criteria).getResultList();
			if(l.size()==0)
			{
				throw new NoResultException(ConstantesEntidades.MENSAJE_LISTA_NULA);
			}
				
			return l;
		}
		catch(NoResultException ex)
		{
			throw new NoResultException(ConstantesEntidades.MENSAJE_LISTA_NULA);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List findAll(Class c, String order) throws NoResultException
	{
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery criteria=cb.createQuery(c);		
		Root root=criteria.from(c);
		
		criteria.select(root).orderBy(cb.asc(root.get(order)));
		
		try
		{
			List l=em.createQuery(criteria).getResultList();
			if(l.size()==0)
			{
				throw new NoResultException(ConstantesEntidades.MENSAJE_LISTA_NULA);
			}
				
			return l;
		}
		catch(NoResultException ex)
		{
			throw new NoResultException(ConstantesEntidades.MENSAJE_LISTA_NULA);
		}
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List findAllFiltered(Class c, String order, EstatusRegistro status) throws NoResultException
	{
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery criteria=cb.createQuery(c);		
		Root root=criteria.from(c);
		
		criteria.select(root)		
		.where(cb.equal(root.get("estatusRegistro"), status))
		.orderBy(cb.asc(root.get(order)));
		try
		{
			List l=em.createQuery(criteria).getResultList();
			if(l.size()==0)
			{
				throw new NoResultException(ConstantesEntidades.MENSAJE_LISTA_NULA);
			}
				
			return l;
		}
		catch(NoResultException ex)
		{
			throw new NoResultException(ConstantesEntidades.MENSAJE_LISTA_NULA);
		}
	}
	
}
