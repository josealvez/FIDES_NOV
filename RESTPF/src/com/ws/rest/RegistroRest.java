package com.ws.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import com.dto.FormularioDTO;
import com.dto.RegistroDTO;
import com.exception.ServiciosException;
import com.negocio.GestionFormularioBean;
import com.negocio.GestionRegistroBean;

@Stateless
@LocalBean
public class RegistroRest implements IRegistroRest {

	@EJB
	private GestionRegistroBean servicioRegistro;
	
    public RegistroRest() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public Response obtenerRegistros() {
		try {
			List<RegistroDTO> registros = servicioRegistro.ListarRegistrosRest();
			Map<String, List<RegistroDTO>> forMap = new HashMap<String, List<RegistroDTO>>();
			forMap.put("registros", registros);
			return  Response.ok().entity(forMap).build();
		} catch (ServiciosException e) {
			return  Response.serverError().build();
		}
	}

}
