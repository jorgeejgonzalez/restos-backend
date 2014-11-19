package idisoft.restos.entities;

import idisoft.restos.util.ConstantesEntidades;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Date;
import java.util.HashSet;
import java.util.Iterator;
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
import org.jboss.util.HashCode;

@SuppressWarnings("serial")
@Entity
@Table(name="pedidos")
public class Pedido implements Serializable{
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotNull(message=ConstantesEntidades.ENTIDAD_PEDIDO_CLIENTE+ConstantesEntidades.VALIDACION_VALOR_NULO)
	@Valid
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cliente")
	private Usuario cliente;
	
	@NotNull(message=ConstantesEntidades.ENTIDAD_PEDIDO_DIRECCION_ENTREGA+ConstantesEntidades.VALIDACION_VALOR_NULO)
	@Column(name="direccion_entrega")
	private String direccionEntrega;
	
	@NotNull(message=ConstantesEntidades.ENTIDAD_PEDIDO_TELEFONO_ENTREGA+ConstantesEntidades.VALIDACION_VALOR_NULO)
	@Column(name="telefono_entrega")
	private String telefonoEntrega;	
	
	@NotNull(message=ConstantesEntidades.ENTIDAD_PEDIDO_FECHA+ConstantesEntidades.VALIDACION_VALOR_NULO)
	@Column
	private Date fecha;
	
	@NotNull(message=ConstantesEntidades.ENTIDAD_PEDIDO_HORA+ConstantesEntidades.VALIDACION_VALOR_NULO)
	@Column
	private Time hora;
	
	@NotNull(message=ConstantesEntidades.ENTIDAD_PEDIDO_SUBTOTAL+ConstantesEntidades.VALIDACION_VALOR_NULO)
	@Column(name="sub_total")
	private float subTotal;
	
	@NotNull(message=ConstantesEntidades.ENTIDAD_PEDIDO_IVA_PORCENTAJE+ConstantesEntidades.VALIDACION_VALOR_NULO)
	@Column(name="iva_porcentaje")
	private float ivaPorcentaje;
	
	@NotNull(message=ConstantesEntidades.ENTIDAD_PEDIDO_IVA_MONTO+ConstantesEntidades.VALIDACION_VALOR_NULO)
	@Column(name="iva_monto")
	private float ivaMonto;
	
	@NotNull(message=ConstantesEntidades.ENTIDAD_PEDIDO_TOTAL+ConstantesEntidades.VALIDACION_VALOR_NULO)
	@Column
	private float total;
	
	@NotNull(message=ConstantesEntidades.ENTIDAD_PEDIDO_ESTATUS+ConstantesEntidades.VALIDACION_VALOR_NULO)
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
	
	public void calcularMontos()
	{
		if(elementos.isEmpty())
		{
			this.subTotal=0.0f;
			this.ivaMonto=0.0f;
			this.total=0.0f;
		}
		else
		{
			Iterator<ElementoCatalogo> iterator=this.elementos.iterator();
			float subtotal=0.0f;		
			
			while(iterator.hasNext())
			{
				ElementoCatalogo elemento=iterator.next();
				if(elemento.getEstatusRegistro()==EstatusRegistro.ACTIVO)
				{
					subtotal+=elemento.getPrecio();
				}			
			}
			
			this.subTotal=subtotal;
			this.ivaMonto=subtotal*(this.ivaPorcentaje/100.0f);
			this.total=subtotal+this.ivaMonto;
		}		
	}
	
	public Set<ConstraintViolation<Pedido>> validarInstancia()
	{
		ValidatorFactory factory= Validation.buildDefaultValidatorFactory();
		Validator validator=factory.getValidator();
		return validator.validate(this);
	}
	
	public boolean isOfUsuario(Usuario usuario)
	{
		return this.cliente.equals(usuario);
	}
	
	@Override
	public boolean equals(Object o)
	{
		boolean check=false;
		if(o instanceof Pedido)
		{
			Pedido p=(Pedido) o;
			check= this.id==p.getId() &&
					this.cliente.equals(p.getCliente()) &&
					this.fecha.equals(p.getFecha()) &&
					this.hora.equals(p.getHora()) &&
					this.direccionEntrega.equals(p.getDireccionEntrega()) &&
					this.telefonoEntrega.equals(p.getTelefonoEntrega()) &&
					this.estatus==p.getEstatus();
		}
		return check;
	}
	
		
	@Override
	public int hashCode()
	{
		HashCode hc=new HashCode(ConstantesEntidades.ENTIDAD_PEDIDO_HASHCODE_PRIME);
		hc.add(this.id);
		if(this.cliente!=null)
		{
			hc.add(this.cliente.hashCode());
		}
		hc.add(this.fecha);
		hc.add(this.hora);
		hc.add(this.direccionEntrega);
		hc.add(this.telefonoEntrega);
		return hc.hashCode();
	}
	

}
