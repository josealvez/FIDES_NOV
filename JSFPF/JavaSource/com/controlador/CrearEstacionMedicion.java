package com.controlador;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.Size;

import com.dto.EstacionMedicionDTO;
import com.exception.ServiciosException;
import com.negocio.GestionEstacionMedicion;

@Named("altaEM")
@SessionScoped
public class CrearEstacionMedicion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private GestionEstacionMedicion gEstacionMedicion;
	
	@Size(min=3,max=50, message = "debe contener entre 3 y 50 caracteres.")
	private  String nombre;
	@Size(min=3,max=50, message = "debe contener entre 3 y 50 caracteres.")
	private  String parametro;
	private  String unidadMedida;
	@Size(min=3,max=50, message = "debe contener entre 3 y 50 caracteres.")
	private  String descripcion;
	@Size(min=3,max=50, message = "debe contener entre 3 y 50 caracteres.")
	private  String tipoDato;
	@Size(min=3,max=50, message = "debe contener entre 3 y 50 caracteres.")
	private  String departamento;
	@Size(min=3,max=50, message = "debe contener entre 3 y 50 caracteres.")
	private  String ciudad;
	@Size(min=3,max=50, message = "debe contener entre 3 y 50 caracteres.")
	private  String usuario;
	public GestionEstacionMedicion getgEstacionMedicion() {
		return gEstacionMedicion;
	}
	public void setgEstacionMedicion(GestionEstacionMedicion gEstacionMedicion) {
		this.gEstacionMedicion = gEstacionMedicion;
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
	public String getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTipoDato() {
		return tipoDato;
	}
	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
public String crearEstacionMedicion() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		
//		if(this.descripcion.trim().equals("")) {
//			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validaci贸n", "Descripci贸n requerida");
//			context.addMessage("Descripci贸n requerida", message);
//			context.getExternalContext().getFlash().setKeepMessages(true);
//			return " ";
//		}else if((this.descripcion.length() < 3) || (this.descripcion.length() > 50)){
//			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validaci贸n", "Descripci贸n tiene que ser mayor a 3 y menor a 50 caracteres");
//			context.addMessage("Descripci贸n tiene que ser mayor a 3 y menor a 50 caracteres", message);
//			context.getExternalContext().getFlash().setKeepMessages(true);
//			return " ";
//		}
//		
//		if(this.nombre.trim().equals("")) {
//			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validaci贸n", "El nombre debe contener entre 3 y 50 caracteres.");
//			context.addMessage( "El nombre debe contener entre 3 y 50 caracteres." , message);
//			context.getExternalContext().getFlash().setKeepMessages(true);
//			return " ";
//		}else if( (this.nombre.length() < 3) || (this.nombre.length() > 50)){
//			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validaci贸n",  "El nombre debe contener entre 3 y 50 caracteres.");
//			context.addMessage( "El nombre debe contener entre 3 y 50 caracteres.", message);
//			context.getExternalContext().getFlash().setKeepMessages(true);
//			return " ";
//		}
		
		try {
			
			EstacionMedicionDTO emDTO = new EstacionMedicionDTO();
			
			emDTO.setCiudad(this.getCiudad());
			emDTO.setDepartamento(this.getDepartamento());
			emDTO.setDescripcion(this.getDescripcion());
			emDTO.setNombre(this.getNombre());
			emDTO.setParametro(this.getParametro());
			emDTO.setTipoDato(this.getTipoDato());
			emDTO.setUnidadMedida(this.getUnidadMedida());
			emDTO.setUsuario(this.getUsuario());
	    	
			gEstacionMedicion.agregarEM(emDTO);
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "La estaci髇 fue creada", "OK");
			context.addMessage("", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
	        
		} catch (ServiciosException  e) {
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "ERROR");
			context.addMessage("", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
	        return " ";
			
		}
		
		//POST-Redirect-GET 
		return "/pages/altaEstacionMedicion.xhtml?faces-redirect=true?i=1";
	}
	
	
	
}
