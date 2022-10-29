package com.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import com.dto.CasillaDTO;
import com.exception.ServiciosException;
import com.negocio.GestionCasillaBean;

@Named("listarcasillas")
@ConversationScoped	
public class ListarCasillas implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EJB
	private GestionCasillaBean servicioCasilla;
	
	private List<CasillaDTO> listacasillas = new ArrayList<CasillaDTO>();
	private List<CasillaDTO> filteredCasilla;
	
	@PostConstruct
	public void init()  {
		try {
			listacasillas = servicioCasilla.obtenerCasillas();
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		for(CasillaDTO f : listacasillas) {
			System.out.println("Este son las casillas: " + f.getNombre());
		}
	}
	
	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        
        CasillaDTO cas = (CasillaDTO) value;
        return cas.getNombre().toLowerCase().contains(filterText)
                || cas.getNombre().toLowerCase().contains(filterText)
                || cas.getDescripcion().toLowerCase().contains(filterText)
                || cas.getLugarubicacion().toLowerCase().contains(filterText);
    }
	
	public List<CasillaDTO> getListaCasilla() {
		return listacasillas;
	}

	public void setListaCasilla(List<CasillaDTO> listaCasillas) {
		this.listacasillas = listaCasillas;
	}

	public List<CasillaDTO> getFilteredCasilla() {
		return filteredCasilla;
	}

	public void setFilteredCasilla(List<CasillaDTO> filteredCasilla) {
		this.filteredCasilla = filteredCasilla;
	}
	
	
}
