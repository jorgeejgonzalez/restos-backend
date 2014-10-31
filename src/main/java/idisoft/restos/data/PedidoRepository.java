package idisoft.restos.data;

import idisoft.restos.entities.Pedido;

import java.util.List;

public class PedidoRepository extends Repository implements ListRecords {

	public Pedido findById(int id)
	{
		return (Pedido)findByIntKey(Pedido.class, id);
	}
	
	@Override
	public List<Pedido> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> findAllActive() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> findAllInactive() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> findAllDeleted() {
		// TODO Auto-generated method stub
		return null;
	}

}
