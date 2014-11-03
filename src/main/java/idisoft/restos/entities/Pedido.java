package idisoft.restos.entities;

import idisoft.restos.util.MensajesEntidades;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

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
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@SuppressWarnings("serial")
@Entity
@Table(name="pedidos",catalog="restos")
public class Pedido implements Serializable{
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotNull(message=MensajesEntidades.ENTIDAD_PEDIDO_CLIENTE+MensajesEntidades.VALIDACION_VALOR_NULO)
	@Valid
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cliente")
	private Usuario cliente;
	
	@NotNull(message=MensajesEntidades.ENTIDAD_PEDIDO_DIRECCION_ENTREGA+MensajesEntidades.VALIDACION_VALOR_NULO)
	@Column(name="direccion_entrega")
	private String direccionEntrega;
	
	@NotNull(message=MensajesEntidades.ENTIDAD_PEDIDO_TELEFONO_ENTREGA+MensajesEntidades.VALIDACION_VALOR_NULO)
	@Column(name="telefono_entrega")
	private String telefonoEntrega;	
	
	@NotNull(message=MensajesEntidades.ENTIDAD_PEDIDO_FECHA+MensajesEntidades.VALIDACION_VALOR_NULO)
	@Column
	private Date fecha;
	
	@NotNull(message=MensajesEntidades.ENTIDAD_PEDIDO_HORA+MensajesEntidades.VALIDACION_VALOR_NULO)
	@Column
	private Time hora;
	
	@NotNull(message=MensajesEntidades.ENTIDAD_PEDIDO_SUBTOTAL+MensajesEntidades.VALIDACION_VALOR_NULO)
	@Column(name="sub_total")
	private float subTotal;
	
	@NotNull(message=MensajesEntidades.ENTIDAD_PEDIDO_IVA_PORCENTAJE+MensajesEntidades.VALIDACION_VALOR_NULO)
	@Column(name="iva_porcentaje")
	private float ivaPorcentaje;
	
	@NotNull(message=MensajesEntidades.ENTIDAD_PEDIDO_IVA_MONTO+MensajesEntidades.VALIDACION_VALOR_NULO)
	@Column(name="iva_monto")
	private float ivaMonto;
	
	@NotNull(message=MensajesEntidades.ENTIDAD_PEDIDO_TOTAL+MensajesEntidades.VALIDACION_VALOR_NULO)
	@Column
	private float total;
	
	@NotNull(message=MensajesEntidades.ENTIDAD_PEDIDO_ESTATUS+MensajesEntidades.VALIDACION_VALOR_NULO)
	@Column
	private EstatusPedido estatus;
	
	@Column(name="estatus_registro")
	private EstatusRegistro estatusRegistro=EstatusRegistro.INACTIVO;
	
	@Valid
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "pedidos_elementos", catalog = "restos", joinColumns = { 
			@JoinColumn(name = "pedido", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "elemento", 
					nullable = false, updatable = false) })
	@Cascade(CascadeType.ALL)	
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
	
	public EstatusRegistro getEstatusRegistro() {
		return estatusRegistro;
	}
	public void setEstatusRegistro(EstatusRegistro estatusRegistro) {
		this.estatusRegistro = estatusRegistro;
	}
	
	public Set<ElementoCatalogo> getElementos() {
		return elementos;
	}
	public void setElementos(Set<ElementoCatalogo> elementos) {
		this.elementos = elementos;
	}
	
	public Set<ConstraintViolation<Pedido>> validarInstancia()
	{
		ValidatorFactory factory= Validation.buildDefaultValidatorFactory();
		Validator validator=factory.getValidator();
		return validator.validate(this);
	}
	
	public boolean isOfUsuario(Usuario usuario)
	{
		return this.cliente.getCedula().equals(usuario.getCedula());
	}

}
