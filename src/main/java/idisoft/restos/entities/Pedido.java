package idisoft.restos.entities;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name="pedidos",catalog="restos")
public class Pedido extends Registro implements Serializable{
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cliente")
	private Usuario cliente;
	
	@NotNull
	@Column(name="direccion_entrega")
	private String direccionEntrega;
	
	@NotNull
	@Column(name="telefono_entrega")
	private String telefonoEntrega;	
	
	@NotNull
	@Column
	private Date fecha;
	
	@NotNull
	@Column
	private Time hora;
	
	@Column(name="sub_total")
	private float subTotal;
	
	@Column(name="iva_porcentaje")
	private float ivaPorcentaje;
	
	@Column(name="iva_monto")
	private float ivaMonto;
	
	@Column
	private float total;
	
	@Column
	private EstatusPedido estatus;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "pedidos_elementos", catalog = "restos", joinColumns = { 
			@JoinColumn(name = "pedido", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "elemento", 
					nullable = false, updatable = false) })
	private Set<ElementoCatalogo> elementos=new HashSet<ElementoCatalogo>(0);
	
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
	public void setIvaPorcentaje(float ivaporcentaje) {
		this.ivaPorcentaje = ivaporcentaje;
	}
	
	public float getIvaMonto() {
		return ivaMonto;
	}
	public void setIvaMonto(float ivamonto) {
		this.ivaMonto = ivamonto;
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
	
	public Set<ElementoCatalogo> getElementos() {
		return elementos;
	}
	public void setElementos(Set<ElementoCatalogo> elementos) {
		this.elementos = elementos;
	}

}
