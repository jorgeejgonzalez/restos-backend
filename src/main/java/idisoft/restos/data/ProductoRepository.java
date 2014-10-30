package idisoft.restos.data;

import java.util.List;

import idisoft.restos.entities.CategoriaProducto;
import idisoft.restos.entities.EstatusRegistro;
import idisoft.restos.entities.Producto;
import idisoft.restos.entities.TipoProducto;

import javax.enterprise.context.ApplicationScoped;

@SuppressWarnings("unchecked")
@ApplicationScoped
public class ProductoRepository extends Repository implements ListRecords {
	
	public Producto findProductoById(int id)
	{
		return (Producto)findByIntKey(Producto.class, id);		
	}
	
	@Override
	public List<Producto> findAll()
	{
		return (List<Producto>)findAll(Producto.class, "id");		
	}
	
	@Override
	public List<Producto> findAllActive()
	{
		return (List<Producto>)findAllFiltered(Producto.class, "id", EstatusRegistro.ACTIVO);		
	}
	
	@Override
	public List<Producto> findAllInactive()
	{
		return (List<Producto>)findAllFiltered(Producto.class, "id", EstatusRegistro.INACTIVO);		
	}
	
	@Override
	public List<Producto> findAllDeleted()
	{
		return (List<Producto>)findAllFiltered(Producto.class, "id", EstatusRegistro.ELIMINADO);		
	}
	
	
	
	public CategoriaProducto findCategoriaProductoById(int id)
	{
		return (CategoriaProducto)findByIntKey(CategoriaProducto.class, id);
	}
	
	public List<CategoriaProducto> findAllCategoriasProductos()
	{
		return (List<CategoriaProducto>)findAll(CategoriaProducto.class, "id");		
	}
	
	public List<CategoriaProducto> findAllCategoriasProductosActive()
	{
		return (List<CategoriaProducto>)findAllFiltered(CategoriaProducto.class, "id",EstatusRegistro.ACTIVO);		
	}
	
	public List<CategoriaProducto> findAllCategoriasProductosInactive()
	{
		return (List<CategoriaProducto>)findAllFiltered(CategoriaProducto.class, "id",EstatusRegistro.INACTIVO);
	}
	
	public List<CategoriaProducto> findAllCategoriasProductosDeleted()
	{
		return (List<CategoriaProducto>)findAllFiltered(CategoriaProducto.class, "id",EstatusRegistro.ELIMINADO);
	}
	
	public TipoProducto findTipoProductoById(int id)
	{
		return (TipoProducto)findByIntKey(TipoProducto.class, id);
	}
	
	public List<TipoProducto> findAllTiposProductos()
	{
		return (List<TipoProducto>)findAll(TipoProducto.class, "id");		
	}
	
	public List<TipoProducto> findAllTiposProductosActive()
	{
		return (List<TipoProducto>)findAllFiltered(TipoProducto.class, "id",EstatusRegistro.ACTIVO);		
	}
	
	public List<TipoProducto> findAllTiposProductosInactive()
	{
		return (List<TipoProducto>)findAllFiltered(TipoProducto.class, "id",EstatusRegistro.INACTIVO);
	}
	
	public List<TipoProducto> findAllTiposProductosDeleted()
	{
		return (List<TipoProducto>)findAllFiltered(TipoProducto.class, "id",EstatusRegistro.ELIMINADO);
	}

}
