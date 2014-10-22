package idisoft.restos.data;

import java.util.List;

import idisoft.restos.entities.CategoriaProducto;
import idisoft.restos.entities.Producto;
import idisoft.restos.entities.TipoProducto;

import javax.enterprise.context.ApplicationScoped;

@SuppressWarnings("unchecked")
@ApplicationScoped
public class ProductoRepository extends Repository {
	
	public Producto findProductoById(int id)
	{
		return (Producto)findByIntKey(Producto.class, id);		
	}
	
	public List<Producto> findAllProductos()
	{
		return (List<Producto>)findAll(Producto.class, "id");		
	}
	
	public CategoriaProducto findCategoriaProductoById(int id)
	{
		return (CategoriaProducto)findByIntKey(CategoriaProducto.class, id);
	}
	
	public List<CategoriaProducto> findAllCategoriasProductos()
	{
		return (List<CategoriaProducto>)findAll(CategoriaProducto.class, "id");		
	}
	
	public TipoProducto findTipoProductoById(int id)
	{
		return (TipoProducto)findByIntKey(TipoProducto.class, id);
	}
	
	public List<TipoProducto> findAllTiposProductos()
	{
		return (List<TipoProducto>)findAll(TipoProducto.class, "id");		
	}

}
