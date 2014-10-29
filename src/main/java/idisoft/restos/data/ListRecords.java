package idisoft.restos.data;

import java.util.List;

public interface ListRecords {
	
	
	public List findAll();	
	public List findAllActive();
	public List findAllInactive();
	public List findAllDeleted();
	

}
