package com.ws.rest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;

import com.dto.UsuarioDTO;
import com.enumerados.EnumEstadoUsuario;
import com.exception.ServiciosException;
import com.negocio.GestionUsuarioBean;


@Stateless
@LocalBean
public class UsuarioRest implements IUsuarioRest {

	@EJB
	private GestionUsuarioBean serviceUsuario;
	
	@Override
	public Response versionAPI() {
			Map<String, String>  apiDescripcion = new HashMap<String, String>();
			apiDescripcion.put("Version", "V1.0");
			apiDescripcion.put("Team", "FIDES");
			apiDescripcion.put("Proyecto", "FIDESAIR");
			
			return Response.ok().entity(apiDescripcion).build();
	}

	@Override
	public Response obtener() {
		try {
			List<UsuarioDTO> todosLosUsuarios = serviceUsuario.obtenerUsuarios();
			return  Response.ok().entity(todosLosUsuarios).build();
		} catch (ServiciosException e) {
			e.printStackTrace();
			return  Response.serverError().build();
		} 
	}

	@Override
	public Response validar(JsonObject imputJson) {
		try {
			Map<String, String> loginStatus = new HashMap<String, String>();
			String email = imputJson.getString("email");
			String password = imputJson.getString("password");
			UsuarioDTO u = serviceUsuario.validarUsuario(email, password);
			boolean status = false;
			status = (u != null && u.getEstadoUsuario().equals(EnumEstadoUsuario.HABILITADO.toString()));
			loginStatus.put("Estado", Boolean.toString(status));
			if(status) {
				loginStatus.put("Nombre", u.getNombre());
				loginStatus.put("Apellido", u.getApellido());
			}
			return  Response.ok().entity(loginStatus).build();
		} catch (NoSuchAlgorithmException | ServiciosException e) {
			return  Response.serverError().build();
		} 
	}

	@Override
	public Response crear(JsonObject j) {
		
		Map<String, Boolean> agregarStatus = new HashMap<String, Boolean>();
		
		try {
			
			UsuarioDTO u = new UsuarioDTO();
			u.setRol(j.getString("rol"));
			u.setEstadoUsuario(j.getString("estadoUsuario"));
			u.setUsername(j.getString("username"));
			u.setNombre(j.getString("nombre"));
			u.setApellido(j.getString("apellido"));
			u.setDireccion(j.getString("direccion"));
			u.setDocumentoCategoria(j.getString("documentoCategoria"));
			u.setDocumento(j.getString("documento"));
			u.setEmail(j.getString("email"));
			u.setPasswd(j.getString("password"));			
			serviceUsuario.agregarUsuario(u);
			agregarStatus.put("Estado", true);
			return  Response.ok().entity(agregarStatus).build();
		}catch (NoSuchAlgorithmException | ServiciosException e ){
			agregarStatus.put("Estado: ", false);
			return  Response.serverError().entity(agregarStatus).build();
		}
	}
	
	@Override
	public Response urlAPI() {
		Map<String, String>  apiDescripcion = new HashMap<String, String>();
		apiDescripcion.put("GET Obtener Usuarios", "/RESTPF/api/v1/usuario/obtenerusuarios");
		apiDescripcion.put("POST Validar Usuario", "/RESTPF/api/v1/usuario/validar");
		apiDescripcion.put("GET Obtener Fenomenos", "/RESTPF/api/v1/observacion/obtenerfenomenos");
		apiDescripcion.put("GET Obtener Localidades", "/RESTPF/api/v1/observacion/obtenerlocalidades");
		apiDescripcion.put("POST Crear Observacion", "/RESTPF/api/v1/observacion/crearobservacion");
		apiDescripcion.put("GET Obtener Observacion por usuario", "/RESTPF/api/v1/observacion/obtenerObservacionUsuario");
		apiDescripcion.put("GET Obtener Observacion por id", "/RESTPF/api/v1/observacion/obtenerObservacionId");
		apiDescripcion.put("GET Obtener Formularios", "/RESTPF/api/v1/formularios/listar");
		return Response.ok().entity(apiDescripcion).build();
	}
	


}
