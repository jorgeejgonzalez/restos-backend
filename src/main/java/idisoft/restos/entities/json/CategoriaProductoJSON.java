package idisoft.restos.entities.json;


import idisoft.restos.entities.CategoriaProducto;
import idisoft.restos.entities.Producto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@SuppressWarnings("serial")
public class CategoriaProductoJSON implements Serializable{
	
	private int id;	
	
	private String nombre;
	
	private Set<ProductoJSON> productos = new HashSet<ProductoJSON>(0);
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<ProductoJSON> getProductos() {
		return productos;
	}

	public void setProductos(Set<ProductoJSON> productos) {
		this.productos = productos;
	}
	
	public void parseCategoria(CategoriaProducto categoria)
	{
		this.id=categoria.getId();
		this.nombre=categoria.getNombre();
		
		if(categoria.getProductos()!=null)
		{
			if(categoria.getProductos().size()>0)
			{
				Iterator<Producto> iterator=categoria.getProductos().iterator();
				while(iterator.hasNext())
				{
					Producto p=iterator.next();
					ProductoJSON pj=new ProductoJSON();
					pj.parseProductoFromCategoria(p);
					this.productos.add(pj);
				}
			}
			else
			{
				this.productos=null;
			}
		}
		else
		{
			this.productos=null;
		}
	}
	
	public void parseCategoriaFromProducto(CategoriaProducto categoria)
	{
		this.id=categoria.getId();
		this.nombre=categoria.getNombre();
		this.productos=null;
	}

}
