package com.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.dto.ObservacionDTO;
import com.dto.UsuarioDTO;
import com.exception.ServiciosException;
import com.negocio.GestionObservacionBean;

@Named("listarobservaciones")
@ConversationScoped	
public class ListarObservaciones implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EJB
	private GestionObservacionBean servicioObservacion;
	
	private List<ObservacionDTO> listaObservacion = new ArrayList<ObservacionDTO>();
	private List<ObservacionDTO> filteredObservacion;
	
	@PostConstruct
	public void init() {
		try {
			UsuarioDTO u = (UsuarioDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
			listaObservacion= servicioObservacion.obtenerObservacionesPorUsuario(u.getEmail());
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
	}
	
	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		  String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
	        if (filterText == null || filterText.equals("")) {
	            return true;
	        }
	        
	        ObservacionDTO obs = (ObservacionDTO) value;
	        return obs.getCategoriaFenomeno().toLowerCase().contains(filterText)
	        		|| obs.getDepartamento().toLowerCase().contains(filterText)
	        		|| obs.getDescripcion().toLowerCase().contains(filterText)
	        		|| obs.getLocalidad().toLowerCase().contains(filterText)
	        		|| obs.getEstado().toLowerCase().contains(filterText)
	        		|| obs.getFecha().toString().toLowerCase().contains(filterText);
	        	   
	}

	public List<ObservacionDTO> getListaObservacion() {
		return listaObservacion;
	}

	public void setListaObservacion(List<ObservacionDTO> listaObservacion) {
		this.listaObservacion = listaObservacion;
	}

	public List<ObservacionDTO> getFilteredObservacion() {
		return filteredObservacion;
	}

	public void setFilteredObservacion(List<ObservacionDTO> filteredObservacion) {
		this.filteredObservacion = filteredObservacion;
	}

	
}
