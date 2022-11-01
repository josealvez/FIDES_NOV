package com.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import com.entities.Contiene;
import com.exception.ServiciosException;
import com.negocio.GestionRegistroBean;

@Named("listarregistros")
@ConversationScoped	
public class ListarRegistros implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private GestionRegistroBean servicioRegistro;
	

	private List<Contiene> listacontienes = new ArrayList<Contiene>();
	private List<Contiene> filteredContiene;

	@PostConstruct
	public void init() {
		try {
				List<Contiene> listaSinFiltro = servicioRegistro.ListarRegistros();
				for (Contiene con : listaSinFiltro) {
					if (con.getIsRegistro()) {
						listacontienes.add(con);
					}
				}
			} catch (ServiciosException e) {
				e.printStackTrace();
		}
	}
	
	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }

        Contiene con = (Contiene) value;
        return con.getCasilla().getNombre().toLowerCase().contains(filterText)
        		||con.getFormulario().getNombre().toLowerCase().contains(filterText);
	}

	public List<Contiene> getListaContiene() {
		init();
		return listacontienes;
	}

	public void setListaContiene(List<Contiene> listacontienes) {
		this.listacontienes = listacontienes;
	}

	public List<Contiene> getFilteredContiene() {
		return filteredContiene;
	}

	public void setFilteredContiene(List<Contiene> filteredContiene) {
		this.filteredContiene = filteredContiene;
	}
	
	
}
