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


import com.dto.CaracteristicaDTO;
import com.dto.FenomenoDTO;
import com.enumerados.EnumTipoDatoCaracteristica;
import com.exception.ServiciosException;
import com.negocio.GestionCaracteristicaBean;
import com.negocio.GestionFenomenoBean;

@Named("crearcaracteristica")
@ConversationScoped	
public class CrearCaracteristica implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private GestionCaracteristicaBean gCaracteristica;
	
	@EJB
	private GestionFenomenoBean gFenomeno;
	
	@Size(min=2,max=40, message = "El nombre debe contener entre 2 y 40 caracteres.")
	private String fenomeno;
	
	@Size(min=2,max=40, message = "El nombre debe contener entre 2 y 40 caracteres.")
	private String nombre;
	
	@Size(min=2,max=40, message = "La Etiqueta debe contener entre 2 y 40 caracteres.")
	private String etiqueta;
	
	@Size(min=2,max=40, message = "El tipo de dato debe contener entre 2 y 40 caracteres.")
	private String tipoDato;
	
	@SuppressWarnings("unused")
	private List<String> fenomenosValues;
	
	
	@SuppressWarnings("unused")
	private EnumTipoDatoCaracteristica etdc;

	public GestionCaracteristicaBean getgCaracteristica() {
		return gCaracteristica;
	}

	public void setgCaracteristica(GestionCaracteristicaBean gCaracteristica) {
		this.gCaracteristica = gCaracteristica;
	}

	public GestionFenomenoBean getgFenomeno() {
		return gFenomeno;
	}

	public void setgFenomeno(GestionFenomenoBean gFenomeno) {
		this.gFenomeno = gFenomeno;
	}

	public String getFenomeno() {
		return fenomeno;
	}

	public void setFenomeno(String fenomeno) {
		this.fenomeno = fenomeno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public String getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}

	public List<String> getFenomenosValues() throws ServiciosException {
		List<String> fenomenosNombre = new ArrayList<String>();
		for(FenomenoDTO fem : gFenomeno.obtenerFenomenos()) {
			fenomenosNombre.add(fem.getNombre());
		}
		return fenomenosNombre;
	}


	public EnumTipoDatoCaracteristica[] getEtdc() {
		return EnumTipoDatoCaracteristica.values();
	}


	public String crearCaracteristica() {
		
FacesContext context = FacesContext.getCurrentInstance();
		
		if(this.fenomeno.trim().equals("")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "El nombre debe contener entre 2 y 40 caracteres");
			context.addMessage("Fenomeno requerido", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}else if((this.fenomeno.length() < 3) || (this.fenomeno.length() > 10)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "El nombre debe contener entre 2 y 40 caracteres");
			context.addMessage("Fenomeno tiene que ser mayor a 2 y menor a 50 caracteres", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}
		
		if(this.nombre.trim().equals("")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "El nombre debe contener entre 2 y 40 caracteres");
			context.addMessage("Nombre requerido", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}else if((this.nombre.length() < 3) || (this.nombre.length() > 50)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "El nombre debe contener entre 2 y 40 caracteres");
			context.addMessage("Nombre tiene que ser mayor a 3 y menor a 50 caracteres", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}
		
		if(this.etiqueta.trim().equals("")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "La Etiqueta debe contener entre 2 y 40 caracteres.");
			context.addMessage( "La etiqueta debe contener entre 3 y 50 caracteres." , message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}else if( (this.etiqueta.length() < 3) || (this.etiqueta.length() > 50)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación",  "La Etiqueta debe contener entre 2 y 40 caracteres.");
			context.addMessage( "La etiqueta debe contener entre 3 y 50 caracteres.", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}
		
		
		
		
		try {
			
			CaracteristicaDTO caracteristica = new CaracteristicaDTO();
			caracteristica.setEtiqueta(this.etiqueta);
			caracteristica.setFenomeno(this.fenomeno);
			caracteristica.setNombre(this.nombre);
			caracteristica.setTipoDato(this.tipoDato);
			getgCaracteristica().agregarCaracteristica(caracteristica);

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "EL Fenomeno fue creado", "OK");
			context.addMessage("", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			
		} catch (ServiciosException e) {

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "ERROR");
			context.addMessage("", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
	        return " ";
		
		}
		
		//POST-Redirect-GET 
		return "/pages/crearCaracteristica.xhtml?faces-redirect=true?i=1";
	}
	
	
	
}
