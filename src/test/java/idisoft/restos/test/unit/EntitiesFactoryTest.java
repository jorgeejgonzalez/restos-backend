package idisoft.restos.test.unit;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import idisoft.restos.data.EntitiesFactory;
import idisoft.restos.entities.Catalogo;
import idisoft.restos.entities.ElementoCatalogo;
import idisoft.restos.entities.Empresa;
import idisoft.restos.entities.EstatusCatalogo;
import idisoft.restos.entities.EstatusRegistro;
import idisoft.restos.entities.Pedido;
import idisoft.restos.entities.Sede;
import idisoft.restos.entities.TipoUsuario;
import idisoft.restos.entities.Usuario;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EntitiesFactoryTest {

	private EntitiesFactory factory;

	@Before
	public void setUp() throws Exception {
		factory=new EntitiesFactory();
	}

	@Test
	public void factoryDebeCrearLosDiferentesUsuarios() {
		Usuario usuariofinal=factory.crearUsuarioFinal();
		Usuario usuariodespacho=factory.crearUsuarioDespacho();
		Usuario usuarioadministrador=factory.crearUsuarioAdministrador();
		Usuario usuariomaster=factory.crearUsuarioMaster();
		
		assertEquals(TipoUsuario.USUARIO_FINAL, usuariofinal.getTipo());
		assertEquals(TipoUsuario.DESPACHO, usuariodespacho.getTipo());
		assertEquals(TipoUsuario.ADMINISTRADOR, usuarioadministrador.getTipo());
		assertEquals(TipoUsuario.MASTER, usuariomaster.getTipo());		
	}
	
	@Test
	public void debeCrearListaDeUsuarios() {
		Usuario usuariofinal=factory.crearUsuarioFinal();
		Usuario usuariodespacho=factory.crearUsuarioDespacho();
		Usuario usuarioadministrador=factory.crearUsuarioAdministrador();
		Usuario usuariomaster=factory.crearUsuarioMaster();
		
		List<Usuario> usuarios=factory.listaUsuarios();
		
		assertEquals(0,usuarios.size());
		
		usuarios.add(usuariofinal);
		usuarios.add(usuariodespacho);
		usuarios.add(usuarioadministrador);
		usuarios.add(usuariomaster);
		
		assertEquals(4,usuarios.size());
				
		usuarios.add(usuariomaster);
		
		assertEquals(5,usuarios.size());		
	}
	
	@Test
	public void debeCrearSetDeUsuarios() {
		Usuario usuariofinal=factory.crearUsuarioFinal();
		Usuario usuariodespacho=factory.crearUsuarioDespacho();
		Usuario usuarioadministrador=factory.crearUsuarioAdministrador();
		Usuario usuariomaster=factory.crearUsuarioMaster();
		
		Set<Usuario> usuarios=factory.setUsuarios();
		
		assertEquals(0,usuarios.size());
		
		usuarios.add(usuariofinal);
		usuarios.add(usuariodespacho);
		usuarios.add(usuarioadministrador);
		usuarios.add(usuariomaster);
		
		assertEquals(4,usuarios.size());
				
		usuarios.add(usuariomaster);
		
		assertEquals(4,usuarios.size());		
	}
	
	@Test
	public void debeCrearEmpresas()
	{
		Empresa empresa=factory.crearEmpresa();
		Empresa empresa2=factory.crearEmpresa();
		
		empresa.setRazonSocial("algo c.a.");
		empresa2.setRazonSocial("falsa c.a.");
		
		assertEquals(false,empresa.equals(empresa2));
	}
	
	@Test
	public void debeCrearSedes()
	{
		Sede sede=factory.crearSede();
		sede.setRif("J123456789");
		
		Sede sede2=factory.crearSede();
		sede2.setRif("J987654321");
		
		assertEquals(false, sede.equals(sede2));
	}
	
	@Test
	public void debeCrearSedesConEmpresas()
	{
		Empresa empresa=factory.crearEmpresa();
		empresa.setRif("J123456789");
		empresa.setRazonSocial("empresa falsa c.a.");		
		
		Sede sede=factory.crearSede(empresa);
		sede.setRif("J123456789");
		
		Sede sede2=factory.crearSede(empresa);
		sede2.setRif("J987654321");
		
		assertEquals(false, sede.equals(sede2));		
	}
	
	@Test
	public void debeCrearCatalogos()
	{
		Catalogo catalogo=factory.crearCatalogo();
		Catalogo catalogo2=factory.crearCatalogo();
		
				
		catalogo.setId(1);
		catalogo2.setId(2);
		
		catalogo.setNombre("algo");
		catalogo2.setNombre("algo2");
		
		assertEquals(false, catalogo.equals(catalogo2));		
		
	}
	
	@Test
	public void debeCrearCatalogosConSede()
	{
		Sede sede=factory.crearSede();
		sede.setNombre("sede falsa");
		
		Catalogo catalogo=factory.crearCatalogo(sede);
		
		assertEquals(EstatusCatalogo.DISPONIBLE,catalogo.getEstatus());
		assertEquals(EstatusRegistro.ACTIVO, catalogo.getEstatusRegistro());
		
		Catalogo catalogo2=factory.crearCatalogo(sede);
		catalogo2.setNombre("chicheria");
				
		assertEquals(true,catalogo.getSede().equals(sede));
		assertEquals(true,catalogo2.getSede().equals(sede));
		assertEquals(false,catalogo.equals(catalogo2));
		
	}
	
	@Test
	public void debeCrearElementosDeCatalogos()
	{
		ElementoCatalogo elemento=factory.crearElementoCatalogo();
		ElementoCatalogo elemento2=factory.crearElementoCatalogo();
		
		elemento.setId(1);
		elemento2.setId(2);
		
		assertEquals(false, elemento.equals(elemento2));
	}
	
	@Test
	public void debeCrearElementosDeCatalogosConCatalogo()
	{
		Catalogo catalogo=factory.crearCatalogo();
		catalogo.setNombre("chicheria");
		
		ElementoCatalogo elemento=factory.crearElementoCatalogo(catalogo);
		ElementoCatalogo elemento2=factory.crearElementoCatalogo(catalogo);
		
		elemento.setId(1);
		elemento2.setId(2);
		
		assertEquals(true,elemento.getCatalogo().equals(catalogo));
		assertEquals(true,elemento2.getCatalogo().equals(catalogo));
		assertEquals(false, elemento.equals(elemento2));
	}
	
	@Test
	public void debeCrearPedidos()
	{
		Pedido pedido=factory.crearPedido();
		Pedido pedido2=factory.crearPedido();
		
		pedido.setId(1);
		pedido2.setId(2);
		
		assertEquals(false, pedido.equals(pedido2));
	}
	
	@Test
	public void debeCrearPedidosParaUsuario()
	{
		Usuario usuario=factory.crearUsuarioFinal();
		Usuario usuario2=factory.crearUsuarioFinal();
		
		usuario.setNombre("jorge");
		usuario2.setNombre("paul");
		
		
		Pedido pedido=factory.crearPedido(usuario);
		Pedido pedido2=factory.crearPedido(usuario2);
		
		assertEquals(false, usuario.equals(usuario2));
		assertEquals(false, pedido.equals(pedido2));
		
	}

}
