package idisoft.restos.entities.json;

import idisoft.restos.entities.Producto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ProductoJSON implements Serializable {
	
private int id;	
	
	private String nombre;
	
	private String descripcion;
	
	private float precio;
	
	private CategoriaProductoJSON categoria;
	
	private TipoProductoJSON tipo;

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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public CategoriaProductoJSON getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaProductoJSON categoria) {
		this.categoria = categoria;
	}

	public TipoProductoJSON getTipo() {
		return tipo;
	}

	public void setTipo(TipoProductoJSON tipo) {
		this.tipo = tipo;
	}
	
	public void parseProducto(Producto producto)
	{
		this.id=producto.getId();
		this.nombre=producto.getNombre();
		this.descripcion=producto.getDescripcion();
		
		CategoriaProductoJSON cpj=new CategoriaProductoJSON();
		cpj.parseCategoriaFromProducto(producto.getCategoria());
		this.categoria=cpj;
		
		TipoProductoJSON tpj=new TipoProductoJSON();
		tpj.parseTipoFromProducto(producto.getTipo());
		this.tipo=tpj;
		
	}
	
	public void parseProductoFromCategoria(Producto producto)
	{
		this.id=producto.getId();
		this.nombre=producto.getNombre();
		this.descripcion=producto.getDescripcion();
		
		this.categoria=null;
		
		TipoProductoJSON tpj=new TipoProductoJSON();
		tpj.parseTipoFromProducto(producto.getTipo());
		this.tipo=tpj;
		
	}
	
	public void parseProductoFromTipo(Producto producto)
	{
		this.id=producto.getId();
		this.nombre=producto.getNombre();
		this.descripcion=producto.getDescripcion();
		
		CategoriaProductoJSON cpj=new CategoriaProductoJSON();
		cpj.parseCategoriaFromProducto(producto.getCategoria());
		this.categoria=cpj;
		
		this.tipo=null;
		
	}
	

}
