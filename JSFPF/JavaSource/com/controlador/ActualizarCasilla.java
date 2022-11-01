package com.controlador;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.Size;

import com.dto.CasillaDTO;
import com.exception.ServiciosException;
import com.negocio.GestionCasillaBean;

@Named("modificarcasilla")
@ConversationScoped
public class ActualizarCasilla implements Serializable {

	private static final long serialVersionUID = 1L;


	@EJB
	private GestionCasillaBean casillaEJB;
	
	private Long id_casilla;
	
	@Size(min=3,max=50, message = "debe contener entre 3 y 50 caracteres.")
	private String descripcion;
	
	@Size(min=3,max=50, message = "debe contener entre 3 y 10 caracteres.")
	private String nombre;

	@Size(min=3,max=40, message = "debe contener entre 3 y 10 caracteres.")
	private String tipoDato;
	
	@Size(min=3,max=40, message = "debe contener entre 3 y 10 caracteres.")
	private String unidadMedida;

	public void initConversation(){
	    if (!FacesContext.getCurrentInstance().isPostback() ) {
	    	FacesContext fc = FacesContext.getCurrentInstance();	
			if(!fc.getExternalContext().getRequestParameterMap().isEmpty()) {
				String nombre = fc.getExternalContext().getRequestParameterMap().get("nombre");
				System.out.println("------------------------HOLA ENTTRE " + nombre );
				try {
					CasillaDTO fs = (CasillaDTO) casillaEJB.obtenerCasillaNombre(nombre);
					this.setId_casilla(fs.getId_casilla());
					this.descripcion = fs.getDescripcion();
					this.nombre = fs.getNombre();
					this.unidadMedida = fs.getUnidadMedida();
					this.tipoDato = fs.getTipoDato();
					
				} catch (ServiciosException e) {
					FacesContext context = FacesContext.getCurrentInstance();
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "ERROR");
					context.addMessage("", message);
				}
			}
	    }
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
	
	public Long getId_casilla() {
		return id_casilla;
	}

	public void setId_casilla(Long id_casilla) {
		this.id_casilla = id_casilla;
	}

	public String modificarCasilla() {
		
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
			CasillaDTO cas = (CasillaDTO) casillaEJB.obtenerCasillaNombre(nombre);
			CasillaDTO casilla = new CasillaDTO();
			casilla.setId_casilla(cas.getId_casilla());
			casilla.setUsuario(cas.getUsuario());
			casilla.setDescripcion(this.getDescripcion());
			casilla.setNombre(this.getNombre());
			casilla.setUnidadMedida(this.getUnidadMedida());
			casilla.setTipoDato(this.getTipoDato());
			casilla.setContienes(cas.getContienes());
			
			casillaEJB.modificarCasilla(casilla);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "La casilla fue actualizada", "OK");
			context.addMessage("", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			        
		} catch (ServiciosException  e) {
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "ERROR");
			context.addMessage("", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
	        return " ";
		}
		//POST-Redirect-GET 
		return "/pages/modificarCasilla.xhtml?faces-redirect=true";
		
	}
	
	
}
