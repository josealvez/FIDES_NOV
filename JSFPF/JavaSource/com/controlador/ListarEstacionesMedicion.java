package com.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.dto.EstacionMedicionDTO;
import com.exception.ServiciosException;
import com.negocio.GestionEstacionMedicion;

@Named("listarEM")
@SessionScoped
public class ListarEstacionesMedicion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private GestionEstacionMedicion gEstacionMedicion;
	
	private List<EstacionMedicionDTO> listaEstaciones = new ArrayList<EstacionMedicionDTO>();
	private List<EstacionMedicionDTO> filteredEstaciones;
	
	@PostConstruct
	public void init()  {
		try {
			listaEstaciones = gEstacionMedicion.obtenerTodasEM();
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		for(EstacionMedicionDTO em : listaEstaciones) {
			System.out.println("Este son las estaciones: " + em.getNombre());
		}
	}
	
	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        
        EstacionMedicionDTO em = (EstacionMedicionDTO) value;
        return em.getNombre().toLowerCase().contains(filterText)
                || em.getDescripcion().toLowerCase().contains(filterText)
                || em.getCiudad().toLowerCase().contains(filterText)
                || em.getDepartamento().toLowerCase().contains(filterText)
                || em.getParametro().toLowerCase().contains(filterText)
                || em.getTipoDato().toLowerCase().contains(filterText)
                || em.getUnidadMedida().toLowerCase().contains(filterText)
                || em.getUsuario().toLowerCase().contains(filterText);
    }

	public List<EstacionMedicionDTO> getListaEstaciones() {
		return listaEstaciones;
	}

	public void setListaEstaciones(List<EstacionMedicionDTO> listaEstaciones) {
		this.listaEstaciones = listaEstaciones;
	}

	public List<EstacionMedicionDTO> getFilteredEstaciones() {
		return filteredEstaciones;
	}

	public void setFilteredEstaciones(List<EstacionMedicionDTO> filteredEstaciones) {
		this.filteredEstaciones = filteredEstaciones;
	}
	
	
	
	
}
