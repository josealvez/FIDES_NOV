package com.ws.rest;

import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("v1/observacion")
public interface IObservacionRest {

	@GET
	@Path("obtenerfenomenos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerFenomenos();
	
	@POST
    @Path("obtenerlocalidades")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerLocalidades(JsonObject imputJson);
	
	@POST
    @Path("crearobservacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearObservacion(JsonObject imputJson);
	
	@POST
    @Path("obtenerObservacionUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerObservacionPorUsuario(JsonObject imputJson);
	
	@POST
    @Path("obtenerObservacionId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerObservacionId(JsonObject imputJson);
}
