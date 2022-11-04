//package com.ws.rest;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import javax.ejb.EJB;
//import javax.ejb.LocalBean;
//import javax.ejb.Stateless;
//import javax.json.JsonObject;
//import javax.ws.rs.core.Response;
//import com.dto.FenomenoDTO;
//import com.dto.LocalidadDTO;
//import com.dto.ObservacionDTO;
//import com.dto.UsuarioDTO;
//import com.enumerados.EnumEstadoUsuario;
//import com.exception.ServiciosException;
//import com.negocio.GestionFenomenoBean;
//import com.negocio.GestionObservacionBean;
//
//@Stateless
//@LocalBean
//public class ObservacionRest implements IObservacionRest{
//
//	@EJB
//	private GestionObservacionBean servicioObservacion;
//	
//	@EJB
//	private GestionFenomenoBean servicioFenomeno;
//	
//	@Override
//	public Response obtenerFenomenos() {
//		try {
//			List<FenomenoDTO> fenomenos = servicioFenomeno.obtenerFenomenos();
//			Map<String, List<FenomenoDTO>> fenMap = new HashMap<String, List<FenomenoDTO>>();
//			fenMap.put("fenomenos", fenomenos);
//			return  Response.ok().entity(fenMap).build();
//		} catch (ServiciosException e) {
//			return  Response.serverError().build();
//		}
//	}
//
//	@Override
//	public Response obtenerLocalidades(JsonObject imputJson) {
//		try {
//			List<LocalidadDTO> localidades = 
//			servicioObservacion.obtenerLocalidadesPorDepartamento(imputJson.getString("departamento").toUpperCase());
//			Map<String, List<LocalidadDTO>> locMap = new HashMap<String, List<LocalidadDTO>>();
//			locMap.put("localidades", localidades);
//			return  Response.ok().entity(locMap).build();
//		} catch (ServiciosException e) {
//			return  Response.serverError().build();
//		}
//	}
//
//	@Override
//	public Response crearObservacion(JsonObject imputJson) {
//		
//		Map<String, String> agregarStatus = new HashMap<String, String>();
//		ObservacionDTO ob = new ObservacionDTO();
//		double latitud = Double.parseDouble(imputJson.getString("latitud"));
//		double longitud = Double.parseDouble(imputJson.getString("longitud"));
//		Date fecha;
//		
//		try {
//			fecha = new SimpleDateFormat("dd/MM/yyyy").parse(imputJson.getString("fecha"));
//			ob.setCategoriaFenomeno(imputJson.getString("categoriaFenomeno"));
//			ob.setDescripcion(imputJson.getString("descripcion"));
//			ob.setFecha(fecha);
//			ob.setLocalidad(imputJson.getString("localidad"));
//			ob.setDepartamento(imputJson.getString("departamento"));
//			//ob.setEmailVoluntario(imputJson.getString("emailVoluntario"));
//			ob.setLatitud(latitud);
//			ob.setLongitud(longitud);
//			
////			byte[] byteArrayFoto;
////			try {
////				byteArrayFoto = Base64.decode(imputJson.getString("imagen"));
////				ob.setImagen(byteArrayFoto);
////			} catch (Base64DecodingException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
//			
//			
//			
//			servicioObservacion.agregarObservacion(ob);
//			
//			agregarStatus.put("Estado", "ok");
//			return  Response.ok().entity(agregarStatus).build();
//			
//		} catch (ParseException | ServiciosException e) {
//			agregarStatus.put("Estado: ", "error");
//			return  Response.serverError().entity(agregarStatus).build();
//		}
//	}
//
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
//	
//	
//}
