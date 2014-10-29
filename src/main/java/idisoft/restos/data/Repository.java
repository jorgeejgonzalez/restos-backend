package idisoft.restos.data;

import idisoft.restos.entities.EstatusRegistro;

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
	protected Object findByStringKey(Class c, String s)
	{
		try
		{			
			return em.find(c,s);
		}
		catch(NoResultException ex)
		{
			//TO-DO
			return null;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Object findByIntKey(Class c, int i)
	{
		try
		{			
			return em.find(c,i);
		}
		catch(NoResultException ex)
		{
			//TO-DO
			return null;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Object findSingleByString(Class c, String attribute,String value)
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
			//TO-DO
			return null;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Object findSingleByStringAndStatus(Class c, String attribute,String value, EstatusRegistro status)
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
			//TO-DO
			return null;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List findListByString(Class c, String attribute,String value)
	{
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery criteria=cb.createQuery(c);		
		Root root=criteria.from(c);
		
		criteria.select(root).where(cb.equal(root.get(attribute), value));
		
		try
		{
			return em.createQuery(criteria).getResultList();
		}
		catch(NoResultException ex)
		{
			//TO-DO
			return null;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List findListByStringAndStatus(Class c, String attribute,String value, EstatusRegistro status)
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
			return em.createQuery(criteria).getResultList();
		}
		catch(NoResultException ex)
		{
			//TO-DO
			return null;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List findAll(Class c, String order)
	{
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery criteria=cb.createQuery(c);		
		Root root=criteria.from(c);
		
		criteria.select(root).orderBy(cb.asc(root.get(order)));
		
		try
		{
			return em.createQuery(criteria).getResultList();
		}
		catch(NoResultException ex)
		{
			//TO-DO
			return null;
		}
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List findAllFiltered(Class c, String order, EstatusRegistro status)
	{
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery criteria=cb.createQuery(c);		
		Root root=criteria.from(c);
		
		criteria.select(root)		
		.where(cb.equal(root.get("estatusRegistro"), status))
		.orderBy(cb.asc(root.get(order)));
		
		
		try
		{
			return em.createQuery(criteria).getResultList();
		}
		catch(NoResultException ex)
		{
			//TO-DO
			return null;
		}
		
	}
	
}
