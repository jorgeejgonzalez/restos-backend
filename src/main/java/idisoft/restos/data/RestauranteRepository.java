package idisoft.restos.data;

import java.util.List;

import idisoft.restos.entities.Empresa;
import idisoft.restos.entities.Sede;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class RestauranteRepository extends Repository {
	
	public Empresa findRestauranteByRif(String rif)
	{
		return (Empresa)findByStringKey(Empresa.class, rif);
	}
	
	@SuppressWarnings("unchecked")
	public List<Empresa> findAllRestaurantes()
	{
		return (List<Empresa>)findAll(Empresa.class,"rif");
	}	
	
	public Sede findSedeByRif(String rif)
	{
		return (Sede)findByStringKey(Sede.class, rif);
	}
	
	@SuppressWarnings("unchecked")
	public List<Sede> findAllSedes()
	{
		return (List<Sede>)findAll(Sede.class,"rif");
	}

}
