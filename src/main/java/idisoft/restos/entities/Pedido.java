package idisoft.restos.entities;

import java.sql.Time;
import java.sql.Date;

public class Pedido {
	
	private int id;
	private Usuario cliente;
	private Sede local;
	private String direccionEntrega;
	private String telefonoEntrega;	
	private Date fecha;
	private Time hora;
	private float subTotal;
	private float porcentajeIVA;
	private float montoIVA;
	private float Total;
	private EstatusPedido estatus;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Usuario getCliente() {
		return cliente;
	}
	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}
	
	public Sede getLocal() {
		return local;
	}
	public void setLocal(Sede local) {
		this.local = local;
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
	
	public float getPorcentajeIVA() {
		return porcentajeIVA;
	}
	public void setPorcentajeIVA(float porcentajeIVA) {
		this.porcentajeIVA = porcentajeIVA;
	}
	
	public float getMontoIVA() {
		return montoIVA;
	}
	public void setMontoIVA(float montoIVA) {
		this.montoIVA = montoIVA;
	}
	
	public float getTotal() {
		return Total;
	}
	public void setTotal(float total) {
		Total = total;
	}
	
	public EstatusPedido getEstatus() {
		return estatus;
	}
	public void setEstatus(EstatusPedido estatus) {
		this.estatus = estatus;
	}

}
