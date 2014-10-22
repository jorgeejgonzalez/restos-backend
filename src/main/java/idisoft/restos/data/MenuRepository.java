package idisoft.restos.data;

import java.util.List;

import idisoft.restos.entities.ElementoMenu;
import idisoft.restos.entities.Menu;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MenuRepository extends Repository {
	
	public Menu findMenuById(int id)
	{
		return (Menu)findByIntKey(Menu.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Menu> findAllMenus()
	{
		return (List<Menu>)findAll(Menu.class, "id");
	}
	
	public ElementoMenu findElementoById(int id)
	{
		return (ElementoMenu)findByIntKey(ElementoMenu.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<ElementoMenu> findAllElementosMenus()
	{
		return (List<ElementoMenu>)findAll(ElementoMenu.class, "id");
	}

}
