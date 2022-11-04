package com.ws.rest;

import javax.ejb.Local;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("v1/formularios")
public interface IFormularioRest {

	@GET
	@Path("obtenerformularios")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerFormularios();

	@POST
    @Path("crear")
    @Produces(MediaType.APPLICATION_JSON)
    Response crearFormulario(JsonObject imputJson);

}
