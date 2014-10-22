package idisoft.restos.entities.json;

import idisoft.restos.entities.Producto;
import idisoft.restos.entities.TipoProducto;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TipoProductoJSON {
	
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
	
	public void parseTipo(TipoProducto tipo)
	{
		this.id=tipo.getId();
		this.nombre=tipo.getNombre();
		
		if(tipo.getProductos()!=null)
		{
			if(tipo.getProductos().size()>0)
			{
				Iterator<Producto> iterator=tipo.getProductos().iterator();
				while(iterator.hasNext())
				{
					Producto p=iterator.next();
					ProductoJSON pj=new ProductoJSON();
					pj.parseProductoFromTipo(p);
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
	
	public void parseTipoFromProducto(TipoProducto tipo)
	{
		this.id=tipo.getId();
		this.nombre=tipo.getNombre();
		this.productos=null;
	}

}
