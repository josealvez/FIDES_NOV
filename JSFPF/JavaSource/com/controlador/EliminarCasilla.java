package com.controlador;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.dto.CasillaDTO;
import com.exception.ServiciosException;
import com.negocio.GestionCasillaBean;

@Named("eliminarcasilla")
@ConversationScoped
public class EliminarCasilla implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EJB
	private GestionCasillaBean casillaEJB;
	
	private Long id_casilla;
	private String descripcion;
	private String lugarubicacion;
	private String nombre;
	private String parametro;
	private String tipoDato;
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
	
	public Long getId_casilla() {
		return id_casilla;
	}

	public void setId_casilla(Long id_casilla) {
		this.id_casilla = id_casilla;
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
	public String getLugarubicacion() {
		return lugarubicacion;
	}
	public void setLugarubicacion(String lugarubicacion) {
		this.lugarubicacion = lugarubicacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getParametro() {
		return parametro;
	}
	public void setParametro(String parametro) {
		this.parametro = parametro;
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
	
public String cancelar() {
	return "/pages/listarCasilla.xhtml?faces-redirect=true";
}
public String eliminar() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		String redirect = "/pages/eliminarCasilla.xhtml?faces-redirect=true";
		if(!this.nombre.isEmpty()) {
			try {
				CasillaDTO cas = (CasillaDTO) casillaEJB.obtenerCasillaNombre(nombre);
				casillaEJB.bajaLogicaCasilla(cas.getId_casilla());
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "La casilla fue Eliminada", "OK");
				context.addMessage("", message);
				context.getExternalContext().getFlash().setKeepMessages(true);
				
			} catch (ServiciosException e) {
				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "ERROR");
				context.addMessage("", message);
				context.getExternalContext().getFlash().setKeepMessages(true);
		        return " ";
			}
		}else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Debe seleccionar una Casilla");
			context.addMessage("", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}

		return redirect;
	}
}
