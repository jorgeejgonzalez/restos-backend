package idisoft.restos.services;

import javax.ejb.Stateless;

import idisoft.restos.entities.Catalogo;
import idisoft.restos.entities.ElementoCatalogo;

@Stateless
public class CatalogoRegistry extends Registry {
	
	public void registrar(Catalogo catalogo)
	{
		persist(catalogo,"Registrando el catalogo "+catalogo.getNombre());
	}
	
	public void actualizar(Catalogo catalogo)
	{
		merge(catalogo,"Actualizando el catalogo "+catalogo.getId());
	}
	
	public void adjuntarElemento(ElementoCatalogo elemento, Catalogo catalogo)
	{
		merge(elemento,"Adjuntando el elemento " + elemento.getNombre() + " al Catalogo "+ catalogo.getId());
	}
	
	public void actualizarElemento(ElementoCatalogo elemento)
	{
		merge(elemento,"Actualizando el elemento "+elemento.getId());
	}

}
