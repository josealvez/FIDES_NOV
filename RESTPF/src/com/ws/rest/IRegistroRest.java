package com.ws.rest;

import javax.ejb.Local;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("v1/registros")
public interface IRegistroRest {

	@GET
	@Path("obtenerregistros")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerRegistros();

}
