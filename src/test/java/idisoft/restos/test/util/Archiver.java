package idisoft.restos.test.util;

import idisoft.restos.data.CatalogosRepository;
import idisoft.restos.data.ListRecords;
import idisoft.restos.data.PedidoRepository;
import idisoft.restos.data.Repository;
import idisoft.restos.data.UsuarioRepository;
import idisoft.restos.entities.Catalogo;
import idisoft.restos.entities.CategoriaProducto;
import idisoft.restos.entities.ElementoCatalogo;
import idisoft.restos.entities.Empresa;
import idisoft.restos.entities.EstatusCatalogo;
import idisoft.restos.entities.EstatusPedido;
import idisoft.restos.entities.EstatusRegistro;
import idisoft.restos.entities.Pedido;
import idisoft.restos.entities.Producto;
import idisoft.restos.entities.Sede;
import idisoft.restos.entities.TipoProducto;
import idisoft.restos.entities.TipoUsuario;
import idisoft.restos.entities.Usuario;
import idisoft.restos.entities.json.CatalogoJSON;
import idisoft.restos.entities.json.CategoriaProductoJSON;
import idisoft.restos.entities.json.ElementoCatalogoJSON;
import idisoft.restos.entities.json.EmpresaJSON;
import idisoft.restos.entities.json.PedidoJSON;
import idisoft.restos.entities.json.ProductoJSON;
import idisoft.restos.entities.json.SedeJSON;
import idisoft.restos.entities.json.TipoProductoJSON;
import idisoft.restos.entities.json.UsuarioJSON;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.tools.example.richfaces.util.Resources;

public class Archiver {
	
	public static WebArchive archivoWeb()
	{
		WebArchive war=ShrinkWrap.create(WebArchive.class,"test.war");				
		
		war.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		war.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml");
		war.addAsWebInfResource("restostest-ds.xml");
		
		war.addClasses(
				Catalogo.class,
				CategoriaProducto.class,
				ElementoCatalogo.class,
				Empresa.class,
				EstatusCatalogo.class,
				EstatusPedido.class,
				EstatusRegistro.class,
				Pedido.class,
				Producto.class,
				Sede.class,
				TipoProducto.class,
				TipoUsuario.class,
				Usuario.class);		
		
		war.addClasses(
				CatalogoJSON.class,
				CategoriaProductoJSON.class,
				ElementoCatalogoJSON.class,
				EmpresaJSON.class,
				PedidoJSON.class,
				SedeJSON.class,
				TipoProductoJSON.class,
				ProductoJSON.class,
				UsuarioJSON.class);
		
		war.addClasses(
				ListRecords.class,
				Repository.class,
				UsuarioRepository.class,
				CatalogosRepository.class,
				PedidoRepository.class);
		
		war.addClass(Resources.class);
		
		System.out.println(war.toString(true));
		return war;
	}
	
	public static JavaArchive archivoJar()
	{
		JavaArchive jar=ShrinkWrap.create(JavaArchive.class,"test.jar")
				.addAsManifestResource(EmptyAsset.INSTANCE,"beans.xml");
		System.out.println(jar.toString(true));
		return jar;
	}

}
