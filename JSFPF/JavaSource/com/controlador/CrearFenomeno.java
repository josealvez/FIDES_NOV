package com.controlador;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.Size;

import com.dto.CasillaDTO;
import com.dto.FenomenoDTO;
import com.dto.LocalidadDTO;
import com.exception.ServiciosException;
import com.negocio.GestionFenomenoBean;



@Named("altafenomeno")
@ConversationScoped	
public class CrearFenomeno implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EJB
	private GestionFenomenoBean fenomenoEJB;
	
	@Size(min=3,max=10, message = "El codigo debe contener entre 3 y 10 caracteres.")
	private String codigo;
	
	@Size(min=3,max=50, message = "El nombre debe contener entre 3 y 50 caracteres.")
	private String nombre;
	
	@Size(min=3,max=50, message = "La descripcion debe contener entre 3 y 50 caracteres.")
	private String descripcion;

	@Size(min=3,max=3, message = "El telefono debe contener solo 3 caracteres.")
	private String telefono;
	

	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String crearFenomeno() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		if(this.codigo.trim().equals("")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "Código requerido");
			context.addMessage("Código requerido", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}else if((this.codigo.length() < 3) || (this.codigo.length() > 10)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "Código tiene que ser mayor a 2 y menor a 10 caracteres");
			context.addMessage("Código tiene que ser mayor a 2 y menor a 10 caracteres", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}
		
		if(this.descripcion.trim().equals("")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "Descripción requerida");
			context.addMessage("Descripción requerida", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}else if((this.descripcion.length() < 3) || (this.descripcion.length() > 50)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "Descripción tiene que ser mayor a 3 y menor a 50 caracteres");
			context.addMessage("Descripción tiene que ser mayor a 3 y menor a 50 caracteres", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}
		
		if(this.nombre.trim().equals("")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "El nombre debe contener entre 3 y 50 caracteres.");
			context.addMessage( "El nombre debe contener entre 3 y 50 caracteres." , message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}else if( (this.nombre.length() < 3) || (this.nombre.length() > 50)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación",  "El nombre debe contener entre 3 y 50 caracteres.");
			context.addMessage( "El nombre debe contener entre 3 y 50 caracteres.", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}
		
		if(this.telefono.trim().equals("")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "El telefono debe contener solo 3 caracteres.");
			context.addMessage( "El telefono debe contener solo 3 caracteres." , message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}else if( this.telefono.length() != 3 ){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación",  "El telefono debe contener solo 3 caracteres.");
			context.addMessage( "El telefono debe contener solo 3 caracteres.", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}
		
		
		
		try {
			
			FenomenoDTO fenomeno = new FenomenoDTO();
			
			fenomeno.setCodigo(this.codigo);
			fenomeno.setDescripcion(this.descripcion);
			fenomeno.setNombre(this.nombre);
			fenomeno.setTelefono(this.telefono);
			
			fenomenoEJB.agregarFenomeno(fenomeno);
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "EL Fenomeno fue creado", "OK");
			context.addMessage("", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			
	      
	        
		} catch (ServiciosException  e) {
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "ERROR");
			context.addMessage("", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
	        return " ";
			
			
		}
		
		//POST-Redirect-GET 
		return "/pages/altaFenomeno.xhtml?faces-redirect=true?i=1";

	}
	

	
}
