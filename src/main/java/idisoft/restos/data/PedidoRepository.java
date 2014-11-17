package idisoft.restos.data;

import idisoft.restos.entities.Catalogo;
import idisoft.restos.entities.EstatusPedido;
import idisoft.restos.entities.EstatusRegistro;
import idisoft.restos.entities.Pedido;
import idisoft.restos.entities.Usuario;
import idisoft.restos.util.ConstantesEntidades;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PedidoRepository extends Repository implements ListRecords {

	public Pedido findById(int id) throws NoResultException
	{
		return (Pedido)findByIntKey(Pedido.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> findAll() throws NoResultException 
	{
		return (List<Pedido>)findAll(Pedido.class,"id");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> findAllActive() throws NoResultException 
	{
		return (List<Pedido>)findAllFiltered(Pedido.class, "id", EstatusRegistro.ACTIVO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> findAllInactive() throws NoResultException 
	{
		return (List<Pedido>)findAllFiltered(Pedido.class, "id", EstatusRegistro.INACTIVO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> findAllDeleted() throws NoResultException 
	{
		return (List<Pedido>)findAllFiltered(Pedido.class, "id", EstatusRegistro.ELIMINADO);
	}
	
	@SuppressWarnings("unchecked")
	public List<Pedido> findAllByUsuario(String cedula, EstatusRegistro eregistro) throws NoResultException
	{
		Usuario usuario=(Usuario)findByStringKey(Usuario.class, cedula);
		
		if(usuario.getEstatusRegistro()!=EstatusRegistro.ACTIVO)
		{
			throw new NoResultException(ConstantesEntidades.MENSAJE_ENTIDAD_NULA);
		}
		else
		{
			CriteriaBuilder cb=em.getCriteriaBuilder();
			CriteriaQuery<Pedido> criteria=cb.createQuery(Pedido.class);		
			Root<Pedido> root=criteria.from(Pedido.class);
			
			Predicate condition1=cb.equal(root.get("cliente"), usuario);			
			Predicate condition2=cb.equal(root.get("estatusRegistro"), eregistro);
			Predicate conditionsQuery=cb.and(condition1,condition2);	
			
			criteria.select(root).where(conditionsQuery);
			
			return (List<Pedido>)findAllFiltered(criteria);
		}		
	}
	
	@SuppressWarnings("unchecked")
	public List<Pedido> findAllByUsuario(String cedula, EstatusPedido epedido, EstatusRegistro eregistro) throws NoResultException
	{
		Usuario usuario=(Usuario)findByStringKey(Usuario.class, cedula);
		
		if(usuario.getEstatusRegistro()!=EstatusRegistro.ACTIVO)
		{
			throw new NoResultException(ConstantesEntidades.MENSAJE_ENTIDAD_NULA);
		}
		else
		{
			CriteriaBuilder cb=em.getCriteriaBuilder();
			CriteriaQuery<Pedido> criteria=cb.createQuery(Pedido.class);		
			Root<Pedido> root=criteria.from(Pedido.class);
			
			Predicate condition1=cb.equal(root.get("cliente"), usuario);			
			Predicate condition2=cb.equal(root.get("estatus"), epedido);
			Predicate condition3=cb.equal(root.get("estatusRegistro"), eregistro);
			Predicate conditionsQuery=cb.and(condition1,condition2,condition3);	
			
			criteria.select(root).where(conditionsQuery);
			
			return (List<Pedido>)findAllFiltered(criteria);
		}
	}
		
	public List<Pedido> findAllActiveByUsuario(String cedula) throws NoResultException
	{
		return findAllByUsuario(cedula, EstatusRegistro.ACTIVO);
	}
	
	public List<Pedido> findAllActiveByUsuario(String cedula,EstatusPedido estatus) throws NoResultException
	{
		return findAllByUsuario(cedula, estatus, EstatusRegistro.ACTIVO);
	}
	
	public List<Pedido> findAllInactiveByUsuario(String cedula) throws NoResultException
	{
		return findAllByUsuario(cedula, EstatusRegistro.INACTIVO);
	}
	
	public List<Pedido> findAllInactiveByUsuario(String cedula,EstatusPedido estatus) throws NoResultException
	{
		return findAllByUsuario(cedula, estatus, EstatusRegistro.INACTIVO);
	}
	
	
	public List<Pedido> findAllDeletedByUsuario(String cedula) throws NoResultException
	{
		return findAllByUsuario(cedula, EstatusRegistro.ELIMINADO);
	}
	
	public List<Pedido> findAllDeletedByUsuario(String cedula,EstatusPedido estatus) throws NoResultException
	{
		return findAllByUsuario(cedula, estatus, EstatusRegistro.ELIMINADO);
	}

}
