package idisoft.restos.data;

import java.util.List;

import idisoft.restos.entities.Restaurante;
import idisoft.restos.entities.Sede;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class RestauranteRepository extends Repository {
	
	public Restaurante findRestauranteByRif(String rif)
	{
		return (Restaurante)findByStringKey(Restaurante.class, rif);
	}
	
	@SuppressWarnings("unchecked")
	public List<Restaurante> findAllRestaurantes()
	{
		return (List<Restaurante>)findAll(Restaurante.class,"rif");
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
