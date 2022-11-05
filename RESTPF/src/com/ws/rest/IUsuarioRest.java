package com.ws.rest;

import java.security.NoSuchAlgorithmException;

import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("v1")
public interface IUsuarioRest {

	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public Response versionAPI();
	
	@GET
	@Path("endpointdescription")
	@Produces(MediaType.APPLICATION_JSON)
	public Response urlAPI();
	
	@GET
	@Path("usuario/obtenerusuarios")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtener();
	
	@POST
    @Path("usuario/validar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response validar(JsonObject imputJson) throws NoSuchAlgorithmException;
	
	@POST
    @Path("usuario/crear")
    @Produces(MediaType.APPLICATION_JSON)
	public Response crear(JsonObject imputJson) throws NoSuchAlgorithmException;
	
	
	
}
