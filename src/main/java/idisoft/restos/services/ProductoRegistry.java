package idisoft.restos.services;

import idisoft.restos.entities.Producto;

import javax.ejb.Stateless;

@Stateless
public class ProductoRegistry extends Registry {
	
	public void registrar(Producto producto) throws Exception
	{
		persist(producto,"Registrando a " + producto.getNombre());		
	}
	
	public void actualizar(Producto producto) throws Exception
	{
		merge(producto,"Actualizando el producto " + producto.getId());		
	}

}
