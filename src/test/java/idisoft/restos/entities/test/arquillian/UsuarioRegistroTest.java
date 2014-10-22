package idisoft.restos.entities.test.arquillian;

import static org.junit.Assert.assertEquals;
import idisoft.restos.data.UsuarioRepository;
import idisoft.restos.services.UsuarioRegistro;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.tools.example.richfaces.model.Member;
import org.jboss.tools.example.richfaces.service.MemberRegistration;
import org.jboss.tools.example.richfaces.util.Resources;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class UsuarioRegistroTest {
	
	@Deployment
	public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(Member.class, MemberRegistration.class, Resources.class)
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                // Deploy our test datasource
                .addAsWebInfResource("restostest-ds.xml");
    }
	
	@Inject
	Logger log;
	
	@Inject
	UsuarioRegistro usuarioRegistro;
	
	@Inject
	UsuarioRepository repositorio;
	
	@Test
	public void testRegistro() throws Exception {		
		assertEquals(1,repositorio.findAll().size());
	}

}
