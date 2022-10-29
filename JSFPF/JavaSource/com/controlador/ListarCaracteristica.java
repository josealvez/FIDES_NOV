package com.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import com.dto.CaracteristicaDTO;
import com.exception.ServiciosException;
import com.negocio.GestionCaracteristicaBean;

@Named("listarcaracteristicas")
@ConversationScoped	
public class ListarCaracteristica implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EJB
	private GestionCaracteristicaBean servicioCaracteristica;
	
	private List<CaracteristicaDTO> listaCaracteristica = new ArrayList<CaracteristicaDTO>();
	private List<CaracteristicaDTO> filteredCaracteristica;

	
	@PostConstruct
	public void init() {
		try {
			listaCaracteristica = servicioCaracteristica.obtenerCaracteristicas();
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
	}
	
	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		  String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
	        if (filterText == null || filterText.equals("")) {
	            return true;
	        }
	        
	        CaracteristicaDTO crc = (CaracteristicaDTO) value;
	        return crc.getEtiqueta().toLowerCase().contains(filterText)
	        	   || crc.getFenomeno().toLowerCase().contains(filterText)
	        	   || crc.getNombre().toLowerCase().contains(filterText)
	        	   || crc.getTipoDato().toLowerCase().contains(filterText);
	}


	public GestionCaracteristicaBean getServicioCaracteristica() {
		return servicioCaracteristica;
	}


	public void setServicioCaracteristica(GestionCaracteristicaBean servicioCaracteristica) {
		this.servicioCaracteristica = servicioCaracteristica;
	}


	public List<CaracteristicaDTO> getListaCaracteristica() {
		return listaCaracteristica;
	}


	public void setListaCaracteristica(List<CaracteristicaDTO> listaCaracteristica) {
		this.listaCaracteristica = listaCaracteristica;
	}


	public List<CaracteristicaDTO> getFilteredCaracteristica() {
		return filteredCaracteristica;
	}


	public void setFilteredCaracteristica(List<CaracteristicaDTO> filteredCaracteristica) {
		this.filteredCaracteristica = filteredCaracteristica;
	}
	
	
	
}
