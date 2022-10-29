package com.ws.rest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import com.dto.FormularioDTO;
import com.exception.ServiciosException;
import com.negocio.GestionCasillaBean;
import com.negocio.GestionFormularioBean;

@Stateless
@LocalBean
public class FormularioRest implements IFormularioRest{

	@EJB
	private GestionFormularioBean servicioFormulario;
	
	@EJB
	private GestionCasillaBean servicioCasilla;
	
	@Override
	public Response obtenerFormularios() {
		try {
			List<FormularioDTO> formularios = servicioFormulario.obtenerFormularios();
			Map<String, List<FormularioDTO>> fenMap = new HashMap<String, List<FormularioDTO>>();
			fenMap.put("formularios", formularios);
			return  Response.ok().entity(fenMap).build();
		} catch (ServiciosException e) {
			return  Response.serverError().build();
		}
	}

	@Override
	public Response crearFormulario(JsonObject imputJson) {
		
		Map<String, String> agregarStatus = new HashMap<String, String>();
		FormularioDTO ob = new FormularioDTO();
		
		Date fecha;
		
		try {
			fecha = new SimpleDateFormat("dd/MM/yyyy").parse(imputJson.getString("fecha"));
			ob.setNombre(imputJson.getString("nombreFormulario"));
			ob.setDescripcion(imputJson.getString("descripcion"));
			ob.setFechahora(fecha);
			ob.setValidarInvestigador(imputJson.getBoolean("true"));

			servicioFormulario.agregarFormulario(ob);
			
			agregarStatus.put("Estado", "ok");
			return  Response.ok().entity(agregarStatus).build();
			
		} catch (ParseException | ServiciosException e) {
			agregarStatus.put("Estado: ", "error");
			return  Response.serverError().entity(agregarStatus).build();
		}
	}

//	@Override
//	public Response obtenerObservacionPorUsuario(JsonObject imputJson) {
//		try {
//			List<ObservacionDTO> obl = 
//					servicioObservacion.obtenerObservacionesPorUsuario(imputJson.getString("email"));
//			
//			Map<String, List<ObservacionDTO>> obsMap = new HashMap<String, List<ObservacionDTO>>();
//			obsMap.put("observaciones", obl);
//			return  Response.ok().entity(obsMap).build();
//			
//		} catch (ServiciosException e) {
//			return  Response.serverError().build();
//		} 
//	}
//
//	@Override
//	public Response obtenerObservacionId(JsonObject imputJson) {
//		
//		long id = Long.parseLong(imputJson.getString("id_observacion"));
//		try {
//			ObservacionDTO ob = servicioObservacion.obtenerObservacionPorId(id);
//			return  Response.ok().entity(ob).build();
//		} catch (ServiciosException e) {
//			return  Response.serverError().build();
//		}
//
//	}
	
	
}
