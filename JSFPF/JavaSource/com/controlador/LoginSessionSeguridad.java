package com.controlador;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import com.dto.UsuarioDTO;

@Named("loginsessionseguridad")
@ConversationScoped	
public class LoginSessionSeguridad  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void validarSession() {
		try {
			UsuarioDTO u = (UsuarioDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
			if(u == null) {
				FacesContext.getCurrentInstance().getExternalContext().redirect("./../index.xhtml"); 
				//FacesContext.getCurrentInstance().getExternalContext().redirect( "/JSFPF/dennis/dennis.xhtml?faces-redirect=true?i=1");
			}
		}catch(Exception e) {
			 
		}
	}
	
	public void logout(ActionEvent event) throws IOException {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    ec.invalidateSession();
	    FacesContext.getCurrentInstance().getExternalContext().redirect("./../index.xhtml?faces-redirect=true");
		
	}
	
	public void redireccionarUsuario (ActionEvent event) throws IOException{
		UsuarioDTO u = (UsuarioDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		if(u.getRol().contains("ADMINISTRADOR")) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/JSFPF/pages/settingsAdmin.xhtml?faces-redirect=true?i=1");
		}else if(u.getRol().contains("INVESTIGADOR")) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/JSFPF/pages/settingsInvestigador.xhtml?faces-redirect=true?i=1");
		}else {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/JSFPF/pages/settingsAficionado.xhtml?faces-redirect=true?i=1");
		}
	}
	
	public void vistaAdmin (ActionEvent event) throws IOException{		
		  FacesContext.getCurrentInstance().getExternalContext().redirect("/JSFPF/pages/listarUsuario.xhtml?faces-redirect=true?i=1");
	}
	
	public void vistaInvestigador (ActionEvent event) throws IOException{		
		  FacesContext.getCurrentInstance().getExternalContext().redirect("/JSFPF/pages/listarFormulario.xhtml?faces-redirect=true?i=1");
	}
	
	public void vistaAficionado (ActionEvent event) throws IOException{		
		  FacesContext.getCurrentInstance().getExternalContext().redirect("/JSFPF/pages/formularioDisponible.xhtml?faces-redirect=true?i=1");
	}
}
