package idisoft.restos.data;

import java.util.List;

import javax.persistence.NoResultException;

public interface ListRecords {
	
	
	public List findAll();	
	public List findAllActive() throws NoResultException;
	public List findAllInactive() throws NoResultException;
	public List findAllDeleted() throws NoResultException;
	

}
