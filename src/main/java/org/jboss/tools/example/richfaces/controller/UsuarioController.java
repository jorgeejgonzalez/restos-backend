package org.jboss.tools.example.richfaces.controller;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;

import idisoft.restos.data.UsuarioRepository;
import idisoft.restos.entities.Usuario;
import idisoft.restos.entities.json.UsuarioJSON;
import idisoft.restos.util.ConstantesREST;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.jboss.tools.example.richfaces.model.Member;
import org.richfaces.cdi.push.Push;

@Model
public class UsuarioController {
	
	public static final String PUSH_CDI_TOPIC = "pushCdi";

    @Inject
    private FacesContext facesContext;    
    
    
    @Inject
    @Push(topic = PUSH_CDI_TOPIC)
    Event<String> pushEvent;
    
    @Inject
    private UsuarioRepository repositorio;
    
    @Produces
    @Named
    private Usuario usuario;
    
    @Produces
    @Named
    private UsuarioJSON usuarioSesion;


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@PostConstruct
    public void initUsuario() {
        usuario = new Usuario();
        usuario.setCedula("V17000000");
    }
	
	public void hacerLogin() throws Exception
	{
		try
		{
			Usuario	retorno=repositorio.findByLogin(usuario.getLogin());
			
			if(retorno!=null)
			{
				if(usuario.getPassword().equals(retorno.getPassword()))
				{
					usuarioSesion=new UsuarioJSON();
					usuarioSesion.parseUsuario(retorno);
					FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Logged In!", "Entrada satisfactoria");
		            facesContext.addMessage(null, m);
		            pushEvent.fire(String.format("Logon Successful: %s (id: %d)", usuarioSesion.getCedula(), usuarioSesion.getLogin()));
				}
				else
				{
					throw new Exception("Password invalido");
				}
			}
			else
			{
				throw new Exception("Login no existe");
			}
			            
		}
		catch(Exception e)
		{
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Logon unsuccessful");
            facesContext.addMessage(null, m);
		}
		
	}	
	
	private String getRootErrorMessage(Exception e) {
        // Default to general error message that registration failed.
        String errorMessage = "Registration failed. See server log for more information";
        if (e == null) {
            // This shouldn't happen, but return the default messages
            return errorMessage;
        }

        // Start with the exception and recurse to find the root cause
        Throwable t = e;
        while (t != null) {
            // Get the message from the Throwable class instance
            errorMessage = t.getLocalizedMessage();
            t = t.getCause();
        }
        // This is the root cause message
        return errorMessage;
    }

	

}
