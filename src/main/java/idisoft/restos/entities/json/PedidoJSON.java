package idisoft.restos.entities.json;

import idisoft.restos.entities.ElementoCatalogo;
import idisoft.restos.entities.EstatusPedido;
import idisoft.restos.entities.Pedido;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class PedidoJSON {
	
	private int id;
	
	private UsuarioJSON cliente;
	
	private String direccionEntrega;
	
	private String telefonoEntrega;	
	
	private Date fecha;
	
	private Time hora;
	
	private float subTotal;
	
	private float ivaPorcentaje;
	
	private float ivaMonto;
	
	private float total;
	
	private EstatusPedido estatus;
	
	private Set<ElementoCatalogoJSON> elementos=new HashSet<ElementoCatalogoJSON>(0);

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UsuarioJSON getCliente() {
		return cliente;
	}

	public void setCliente(UsuarioJSON cliente) {
		this.cliente = cliente;
	}

	public String getDireccionEntrega() {
		return direccionEntrega;
	}

	public void setDireccionEntrega(String direccionEntrega) {
		this.direccionEntrega = direccionEntrega;
	}

	public String getTelefonoEntrega() {
		return telefonoEntrega;
	}

	public void setTelefonoEntrega(String telefonoEntrega) {
		this.telefonoEntrega = telefonoEntrega;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public float getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}

	public float getIvaPorcentaje() {
		return ivaPorcentaje;
	}

	public void setIvaPorcentaje(float ivaPorcentaje) {
		this.ivaPorcentaje = ivaPorcentaje;
	}

	public float getIvaMonto() {
		return ivaMonto;
	}

	public void setIvaMonto(float ivaMonto) {
		this.ivaMonto = ivaMonto;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public EstatusPedido getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusPedido estatus) {
		this.estatus = estatus;
	}
	
	public Set<ElementoCatalogoJSON> getElementos() {
		return elementos;
	}

	public void setElementos(Set<ElementoCatalogoJSON> elementos) {
		this.elementos = elementos;
	}

	public void parsePedido(Pedido pedido)
	{
		this.id=pedido.getId();
		
		this.cliente=new UsuarioJSON();
		this.cliente.parseUsuarioFromPedidos(pedido.getCliente());
		
		this.direccionEntrega=pedido.getDireccionEntrega();
		this.telefonoEntrega=pedido.getTelefonoEntrega();
		this.fecha=pedido.getFecha();
		this.hora=pedido.getHora();
		this.subTotal=pedido.getSubTotal();
		this.ivaPorcentaje=pedido.getIvaPorcentaje();
		this.ivaMonto=pedido.getIvaMonto();
		this.total=pedido.getTotal();
		this.estatus=pedido.getEstatus();	
		
		if(pedido.getElementos()!=null)
		{
			if(!pedido.getElementos().isEmpty())
			{
				Iterator<ElementoCatalogo> iterator=pedido.getElementos().iterator();
				while(iterator.hasNext())
				{
					ElementoCatalogo em=iterator.next();
					ElementoCatalogoJSON emj=new ElementoCatalogoJSON();
					emj.parseElemento(em);
					this.elementos.add(emj);
				}
			}
			else
			{
				this.elementos=null;
			}
			
		}
		else 
		{
			this.elementos=null;
		}
	}
	
	public void parsePedidoFromUsuario(Pedido pedido)
	{
		this.id=pedido.getId();		
		this.cliente=null;		
		this.direccionEntrega=pedido.getDireccionEntrega();
		this.telefonoEntrega=pedido.getTelefonoEntrega();
		this.fecha=pedido.getFecha();
		this.hora=pedido.getHora();
		this.subTotal=pedido.getSubTotal();
		this.ivaPorcentaje=pedido.getIvaPorcentaje();
		this.ivaMonto=pedido.getIvaMonto();
		this.total=pedido.getTotal();
		this.estatus=pedido.getEstatus();
		
		if(pedido.getElementos()!=null)
		{
			if(!pedido.getElementos().isEmpty())
			{
				Iterator<ElementoCatalogo> iterator=pedido.getElementos().iterator();
				while(iterator.hasNext())
				{
					ElementoCatalogo em=iterator.next();
					ElementoCatalogoJSON emj=new ElementoCatalogoJSON();
					emj.parseElemento(em);
					this.elementos.add(emj);
				}
			}
			else
			{
				this.elementos=null;
			}
			
		}
		else 
		{
			this.elementos=null;
		}
		
	}

}
