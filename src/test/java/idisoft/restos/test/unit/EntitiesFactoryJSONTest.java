package idisoft.restos.test.unit;

import static org.junit.Assert.*;

import java.util.List;

import idisoft.restos.data.EntitiesFactory;
import idisoft.restos.data.EntitiesFactoryJSON;
import idisoft.restos.entities.Catalogo;
import idisoft.restos.entities.ElementoCatalogo;
import idisoft.restos.entities.Empresa;
import idisoft.restos.entities.EstatusRegistro;
import idisoft.restos.entities.Pedido;
import idisoft.restos.entities.Sede;
import idisoft.restos.entities.TipoUsuario;
import idisoft.restos.entities.Usuario;
import idisoft.restos.entities.json.CatalogoJSON;
import idisoft.restos.entities.json.ElementoCatalogoJSON;
import idisoft.restos.entities.json.EmpresaJSON;
import idisoft.restos.entities.json.PedidoJSON;
import idisoft.restos.entities.json.SedeJSON;
import idisoft.restos.entities.json.UsuarioJSON;
import idisoft.restos.util.ConstantesEntidades;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EntitiesFactoryJSONTest {
	
	private EntitiesFactoryJSON factoryJSON;
	private EntitiesFactory factory;

	@Before
	public void setUp() throws Exception {
		factory=new EntitiesFactory();
		factoryJSON=new EntitiesFactoryJSON();
	}

	@Test
	public void debeCrearUsuarios() {
		Usuario usuario=factory.crearUsuarioMaster();
		UsuarioJSON usuariojson=factoryJSON.parseUsuario(usuario);
		
		assertEquals(true, usuario.getCedula().equals(usuariojson.getCedula()));
		assertEquals(true,usuariojson.getPassword().equals(ConstantesEntidades.USUARIO_CLAVE_OCULTA));
	}
	
	@Test
	public void debeCrearEmpresas()
	{
		Empresa empresa=factory.crearEmpresa();
		EmpresaJSON empresajson=factoryJSON.parseEmpresa(empresa);
		
		assertEquals(true, empresa.getRif().equals(empresajson.getRif()));
		assertEquals(true, empresajson.getSedes()==null);
		
		Sede sede=factory.crearSede(empresa);
		empresa.getSedes().add(sede);
		
		empresajson=factoryJSON.parseEmpresa(empresa);
		
		assertEquals(1,empresajson.getSedes().size());
	}
	
	@Test
	public void debeCrearSedes()
	{
		Sede sede=factory.crearSede();
		SedeJSON sedejson=factoryJSON.parseSede(sede);
		
		assertEquals(true, sede.getRif().equals(sedejson.getRif()));
		assertEquals(false, sedejson.getEmpresa()==null);
		assertEquals(true, sedejson.getCatalogos()==null);
		
		Catalogo catalogo=factory.crearCatalogo(sede);
		sede.getCatalogos().add(catalogo);
		sedejson=factoryJSON.parseSede(sede);
		
		assertEquals(1, sedejson.getCatalogos().size());		
	}
	
	@Test
	public void debeCrearCatalogos()
	{
		Catalogo catalogo=factory.crearCatalogo();
		CatalogoJSON catalogojson=factoryJSON.parseCatalogo(catalogo);
		
		assertEquals(true, catalogo.getId()==catalogojson.getId());
		assertEquals(null, catalogojson.getElementos());
		
		ElementoCatalogo elemento=factory.crearElementoCatalogo(catalogo);
		catalogo.getElementos().add(elemento);
		catalogojson=factoryJSON.parseCatalogo(catalogo);
		
		assertEquals(1,catalogojson.getElementos().size());
	}
	
	@Test
	public void debeCrearElementosdeCatalogo()
	{
		ElementoCatalogo elemento=factory.crearElementoCatalogo();
		ElementoCatalogoJSON elementojson=factoryJSON.parseElemento(elemento);
		assertEquals(elemento.getDescripcion(), elementojson.getDescripcion());		
	}
	
	@Test
	public void debeCrearPedidos()
	{
		Pedido pedido=factory.crearPedido();
		PedidoJSON pedidojson=factoryJSON.parsePedido(pedido);
		
		assertEquals(true, pedido.getHora()==pedidojson.getHora());
		assertEquals(true, pedido.getFecha()==pedidojson.getFecha());
		
		Usuario usuario=factory.crearUsuarioFinal();
		usuario.setCedula("V17230971");
		usuario.setNombre("jorge");
		usuario.setDireccion("sanfra");
		usuario.setTelefono("02617623790");
		
		Catalogo catalogo=factory.crearCatalogo();
		catalogo.setNombre("test");
				
		ElementoCatalogo elemento1=factory.crearElementoCatalogo(catalogo);
		ElementoCatalogo elemento2=factory.crearElementoCatalogo(catalogo);
		ElementoCatalogo elemento3=factory.crearElementoCatalogo(catalogo);
		
		elemento1.setPrecio(10.0f);
		elemento2.setPrecio(20.0f);
		elemento3.setPrecio(50.0f);	
		
		List<ElementoCatalogo> elementos=factory.listaElementosCatalogo();
		elementos.add(elemento1);
		elementos.add(elemento2);
		elementos.add(elemento3);
		
		pedido=factory.crearPedido(usuario, elementos);
		
		pedidojson=factoryJSON.parsePedido(pedido);
		
		assertEquals(usuario.getNombre(), pedidojson.getCliente().getNombre());
		assertEquals(usuario.getDireccion(), pedidojson.getDireccionEntrega());
		assertEquals(usuario.getTelefono(), pedidojson.getTelefonoEntrega());
		assertEquals(true,pedidojson.getSubTotal()==80.0f);		
	}

}
