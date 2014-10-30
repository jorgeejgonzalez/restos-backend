package idisoft.restos.services;

import idisoft.restos.entities.CategoriaProducto;
import idisoft.restos.entities.Producto;
import idisoft.restos.entities.TipoProducto;

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
	
	public void adjuntarCategoria(CategoriaProducto categoria, Producto producto) throws Exception
	{
		
		merge(categoria,"Agregando el producto "+producto.getId() + " a la categoria "+ categoria.getId());
	}
	
	public void adjuntarTipo(TipoProducto tipo, Producto producto) throws Exception
	{
		merge(tipo,"Agregando el producto "+producto.getId() + " al tipo "+ tipo.getId());
	}

}
