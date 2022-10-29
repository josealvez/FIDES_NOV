package com.controlador;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import com.dto.UsuarioDTO;
import com.exception.ServiciosException;
import com.negocio.GestionUsuarioBean;



@Named("listarusuarios")
@ConversationScoped	
public class ListarUsuarios implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EJB
	private GestionUsuarioBean servicioUsuario;

	
	private List<UsuarioDTO> listaUsuarios = new ArrayList<UsuarioDTO>();
	private List<UsuarioDTO> filteredUser;
	
	public GestionUsuarioBean getServicioUsuario() {
		return servicioUsuario;
	}

	public void setServicioUsuario(GestionUsuarioBean servicioUsuario) {
		this.servicioUsuario = servicioUsuario;
	}

	public List<UsuarioDTO> getFilteredUser() {
		return filteredUser;
	}

	public void setFilteredUser(List<UsuarioDTO> filteredUser) {
		this.filteredUser = filteredUser;
	}

	@PostConstruct
	public void init()  {
		try {
			listaUsuarios = servicioUsuario.obtenerUsuarios();
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		for(UsuarioDTO u : listaUsuarios) {
			System.out.println("Este es mi NOMBRE: " + u.getNombre());
		}
	}

	public List<UsuarioDTO> getListaUsuarios() {
		return listaUsuarios;
	}
	public void setListaUsuarios(List<UsuarioDTO> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	
	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        
        UsuarioDTO user = (UsuarioDTO) value;
        return user.getNombre().toLowerCase().contains(filterText)
                || user.getApellido().toLowerCase().contains(filterText)
                || user.getEmail().toLowerCase().contains(filterText)
                || user.getRol().toLowerCase().contains(filterText)
                || user.getEstadoUsuario().toLowerCase().contains(filterText)	;
    }
	
	
}
