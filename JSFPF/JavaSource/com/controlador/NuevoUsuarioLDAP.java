package com.controlador;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.dto.UsuarioDTO;
import com.enumerados.EnumCategoriaUsuario;
import com.enumerados.EnumEstadoUsuario;
import com.exception.ServiciosException;

@Named("crearusuarioldap")
@SessionScoped
public class NuevoUsuarioLDAP extends CrearUsuario {

	private static final long serialVersionUID = 1L;
	
	
	
	public void initConversation() {
		    if (!FacesContext.getCurrentInstance().isPostback() ) {
		    	FacesContext fc = FacesContext.getCurrentInstance();	
				if(!fc.getExternalContext().getRequestParameterMap().isEmpty()) {
					UsuarioDTO u = (UsuarioDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
					this.email = u.getEmail();
					this.userName = u.getUsername();
					this.password = u.getUsername();
 					this.estadoUsuario =  EnumEstadoUsuario.HABILITADO.toString();
					this.rolUsuairo =  EnumCategoriaUsuario.INVESTIGADOR.toString();
					
				}
		    }
	}
	
	
	public void crearUsuarioLDAP() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		
		if(this.password.trim().equals("")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación",  "El password debe contener entre 2 y 50 caracteres." );
			context.addMessage( "El password debe contener entre 2 y 50 caracteres." , message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().getExternalContext().redirect(" ");
			
		}else if( (this.password.trim().length() < 2) || (this.password.trim().length() > 50)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación",  "El password debe contener entre 2 y 50 caracteres.");
			context.addMessage( "El password debe contener entre 2 y 50 caracteres.", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().getExternalContext().redirect(" ");
		}
		
		
		if(this.nombre.trim().equals("")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "El nombre debe contener entre 2 y 50 caracteres.");
			context.addMessage( "El nombre debe contener entre 2 y 50 caracteres." , message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().getExternalContext().redirect(" ");
		}else if( (this.nombre.trim().length() < 2) || (this.nombre.trim().length() > 50)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación",  "El nombre debe contener entre 2 y 50 caracteres.");
			context.addMessage( "El nombre debe contener entre 2 y 50 caracteres.", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().getExternalContext().redirect(" ");
		}
		
		if(this.apellido.trim().equals("")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "El apellido debe contener entre 2 y 50 caracteres.");
			context.addMessage( "El apellido debe contener entre 2 y 50 caracteres." , message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().getExternalContext().redirect(" ");
		}else if( (this.apellido.trim().length() < 2) || (this.apellido.trim().length() > 50)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación",  "El apellido debe contener entre 2 y 50 caracteres.");
			context.addMessage( "El apellido debe contener entre 2 y 50 caracteres.", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().getExternalContext().redirect(" ");
		}
		
		if(this.direccion.trim().equals("")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "La dirección debe contener entre 2 y 50 caracteres.");
			context.addMessage( "La dirección debe contener entre 2 y 50 caracteres." , message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().getExternalContext().redirect(" ");
		}else if( (this.direccion.trim().length() < 2) || (this.direccion.trim().length() > 50)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación",  "La dirección debe contener entre 2 y 50 caracteres.");
			context.addMessage( "La dirección debe contener entre 2 y 50 caracteres.", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().getExternalContext().redirect(" ");
		}
		
		if(this.userName.trim().equals("")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "El userName debe contener entre 2 y 50 caracteres.");
			context.addMessage( "El userName debe contener entre 2 y 50 caracteres." , message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().getExternalContext().redirect(" ");
		}else if( (this.userName.trim().length() < 2) || (this.userName.trim().length() > 50)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación",  "El userName debe contener entre 2 y 50 caracteres.");
			context.addMessage( "El userName debe contener entre 2 y 50 caracteres.", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().getExternalContext().redirect(" ");
		}
		
		if(this.documento.trim().equals("")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "El documento debe contener entre 2 y 50 caracteres.");
			context.addMessage( "El documento debe contener entre 2 y 50 caracteres." , message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			 FacesContext.getCurrentInstance().getExternalContext().redirect(" ");
		}else if( (this.documento.trim().length() < 2) || (this.documento.trim().length() > 50)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación",  "El documento debe contener entre 2 y 50 caracteres.");
			context.addMessage( "El documento debe contener entre 2 y 50 caracteres.", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().getExternalContext().redirect(" ");
		}
		
		try {
			
			UsuarioDTO usuario = new UsuarioDTO();
			
			usuario.setEmail(this.getEmail());
			usuario.setUsername(this.getUserName());
			usuario.setNombre(this.getNombre());
			usuario.setApellido(this.getApellido());
			usuario.setDireccion(this.getDireccion());
			usuario.setDocumento(this.getDocumento());
			usuario.setDocumentoCategoria(this.getDocumentoCategoria());
			usuario.setEstadoUsuario(this.getEstadoUsuario());
			usuario.setRol(this.getRolUsuairo());
			usuario.setPasswd(this.getPassword());
			
			usuarioEJB.agregarUsuario(usuario);
		
	        
		} catch (ServiciosException | NoSuchAlgorithmException e) {
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "ERROR");
			context.addMessage("", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().getExternalContext().redirect("");
			
			
		}
		
		//POST-Redirect-GET 
		FacesContext.getCurrentInstance().getExternalContext().redirect("./../index.xhtml?faces-redirect=true?i=1");

	}

}