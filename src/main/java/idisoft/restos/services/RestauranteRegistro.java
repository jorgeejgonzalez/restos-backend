package idisoft.restos.services;

import idisoft.restos.entities.Restaurante;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class RestauranteRegistro {
	
	@Inject
	Logger log;
	
	@Inject
	EntityManager em;
	
	public void registrarRestaurante(Restaurante restaurante) throws Exception
	{
		log.info("Registrando a " + restaurante.getRif()+ " "+restaurante.getRazonSocial());
		em.persist(restaurante);
	}

}
