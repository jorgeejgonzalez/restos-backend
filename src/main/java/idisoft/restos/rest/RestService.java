package idisoft.restos.rest;

import idisoft.restos.util.ConstantesREST;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public abstract class RestService {
	
	@Inject
	protected Logger logger;
	
	protected Response.ResponseBuilder builderProvider(Response.Status status,String type)
	{
		Response.ResponseBuilder builder=Response.status(status);
		
		builder.header(ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_ORIGIN, ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_ORIGIN_VALUE);		
		builder.header(ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_HEADERS,ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_HEADERS_VALUE);
        builder.header(ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_CREDENTIALS,ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_CREDENTIALS_VALUE);
        builder.header(ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_METHODS,ConstantesREST.REST_HEADER_ACCESS_CONTROL_ALLOW_METHODS_VALUE);
        builder.header(ConstantesREST.REST_HEADER_ACCESS_CONTROL_MAX_AGE,ConstantesREST.REST_HEADER_ACCESS_CONTROL_MAX_AGE_VALUE);
		
		builder.type(MediaType.APPLICATION_JSON);
		
		return builder;
	}

}
