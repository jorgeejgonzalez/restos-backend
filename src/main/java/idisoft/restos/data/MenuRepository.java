package idisoft.restos.data;

import java.util.List;

import idisoft.restos.entities.ElementoCatalogo;
import idisoft.restos.entities.Catalogo;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MenuRepository extends Repository {
	
	public Catalogo findMenuById(int id)
	{
		return (Catalogo)findByIntKey(Catalogo.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Catalogo> findAllMenus()
	{
		return (List<Catalogo>)findAll(Catalogo.class, "id");
	}
	
	public ElementoCatalogo findElementoById(int id)
	{
		return (ElementoCatalogo)findByIntKey(ElementoCatalogo.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<ElementoCatalogo> findAllElementosMenus()
	{
		return (List<ElementoCatalogo>)findAll(ElementoCatalogo.class, "id");
	}

}
