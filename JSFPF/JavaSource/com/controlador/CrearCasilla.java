package com.controlador;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.Size;

import com.dto.CasillaDTO;
import com.dto.UsuarioDTO;
import com.enumerados.EnumTipoDato;
import com.exception.ServiciosException;
import com.negocio.GestionCasillaBean;

@Named("altacasilla")
@ConversationScoped	
public class CrearCasilla implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private GestionCasillaBean casillaEJB;

	@Size(min=3,max=50, message = "debe contener entre 3 y 50 caracteres.")
	private String descripcion;
	
	@Size(min=3,max=50, message = "debe contener entre 3 y 10 caracteres.")
	private String nombre;
	
	@Size(min=2,max=40, message = "El tipo de dato debe contener entre 2 y 40 caracteres.")
	private String tipoDato;
	
	@SuppressWarnings("unused")
	private EnumTipoDato etdc;

	@Size(min=3,max=40, message = "debe contener entre 3 y 10 caracteres.")
	private String unidadMedida;
	
	private String usuario;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public GestionCasillaBean getCasillaEJB() {
		return casillaEJB;
	}

	public void setCasillaEJB(GestionCasillaBean casillaEJB) {
		this.casillaEJB = casillaEJB;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	
	public void setEtdc(EnumTipoDato etdc) {
		this.etdc = etdc;
	}

	public EnumTipoDato[] getEtdc() {
			return EnumTipoDato.values();
	}

public String crearCasilla() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		
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

		if(this.unidadMedida.trim().equals("")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "debe contener entre 3 y 50 caracteres.");
			context.addMessage( "debe contener entre 3 y 50 caracteres." , message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}else if( (this.unidadMedida.length() < 3) || (this.unidadMedida.length() > 50)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación",  "debe contener entre 3 y 50 caracteres.");
			context.addMessage( "debe contener entre 3 y 50 caracteres.", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}
		
		try {
			UsuarioDTO usr = (UsuarioDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
			CasillaDTO casilla = new CasillaDTO();
			casilla.setTipoDato(this.getTipoDato());
			casilla.setUnidadMedida(this.getUnidadMedida());
			casilla.setNombre(this.getNombre());
			casilla.setDescripcion(this.getDescripcion());
			casilla.setUsuario(usr.getUsername());
	    	
			casillaEJB.agregarCasilla(casilla);
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "La casilla fue creada", "OK");
			context.addMessage("", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
	        
		} catch (ServiciosException  e) {
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "ERROR");
			context.addMessage("", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
	        return " ";
		}
		
		//POST-Redirect-GET 
		return "/pages/altaCasilla.xhtml?faces-redirect=true?i=1";
	}
}
