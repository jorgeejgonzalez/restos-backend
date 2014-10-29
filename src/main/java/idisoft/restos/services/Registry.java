package idisoft.restos.services;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public abstract class Registry {
	
	@Inject
	protected Logger logger;
	
	@Inject
	protected EntityManager em;
	
	protected void persist(Object obj, String log)
	{
		logger.info(log);
		em.persist(obj);
	}
	
	protected void merge(Object obj, String log)
	{
		logger.info(log);
		em.merge(obj);
	}

}
