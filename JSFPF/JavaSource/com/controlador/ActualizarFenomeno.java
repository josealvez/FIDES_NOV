package com.controlador;
import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.Size;

import com.dto.FenomenoDTO;
import com.exception.ServiciosException;
import com.negocio.GestionFenomenoBean;

@Named("modificarfenomeno")
@ConversationScoped
public class ActualizarFenomeno implements Serializable{

	private static final long serialVersionUID = 1L;


	@EJB
	private GestionFenomenoBean fenomenoEJB;
	

	
	private Long id_fenomeno;

	@Size(min=2,max=50, message = "El codigo debe contener entre 2 y 50 caracteres.")
	private String codigoBusqueda;
	
	@Size(min=2,max=50, message = "El codigo debe contener entre 2 y 50 caracteres.")
	private String codigo;
	
	@Size(min=2,max=50, message = "El nombre debe contener entre 2 y 50 caracteres.")
	private String nombre;
	
	@Size(min=2,max=50, message = "La descripcion debe contener entre 2 y 50 caracteres.")
	private String descripcion;

	@Size(min=3,max=3, message = "El telefono debe contener solo 3 caracteres.")
	private String telefono;
	
	
	
	public void initConversation(){
	    if (!FacesContext.getCurrentInstance().isPostback() ) {
	    	FacesContext fc = FacesContext.getCurrentInstance();	
			if(!fc.getExternalContext().getRequestParameterMap().isEmpty()) {
				String nombreFenomeno= fc.getExternalContext().getRequestParameterMap().get("nombre");
				try {
					FenomenoDTO fs = fenomenoEJB.obtenerFenomenoNombre(nombreFenomeno);
					if(fs != null) {
						this.id_fenomeno = fs.getId_fenomeno();
						this.codigo = fs.getCodigo();
						this.nombre = fs.getNombre();
						this.descripcion = fs.getDescripcion();
						this.telefono = fs.getTelefono();
						System.out.print("ACA ESTA EL ID: " + fs.getId_fenomeno());
					}
					
				} catch (ServiciosException e) {
					FacesContext context = FacesContext.getCurrentInstance();
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "ERROR");
					context.addMessage("", message);
				}
			}
	    }
	}
	

	public GestionFenomenoBean getFenomenoEJB() {
		return fenomenoEJB;
	}

	public void setFenomenoEJB(GestionFenomenoBean fenomenoEJB) {
		this.fenomenoEJB = fenomenoEJB;
	}

	
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

	
	public String getCodigoBusqueda() {
		return codigoBusqueda;
	}


	public void setCodigoBusqueda(String codigoBusqueda) {
		this.codigoBusqueda = codigoBusqueda;
	}

	public String busquedaPorCodigo() {
		return "/pages/modificarFenomeno.xhtml?faces-redirect=true&codigo=" + this.codigo;
	}
	
	public String modificarFenomeno() {
		
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
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "Descripción tiene que ser mayor a 2 y menor a 50 caracteres");
			context.addMessage("Descripción tiene que ser mayor a 3 y menor a 50 caracteres", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}
		
		if(this.nombre.trim().equals("")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "El nombre debe contener entre 2 y 50 caracteres.");
			context.addMessage( "El nombre debe contener entre 3 y 50 caracteres." , message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}else if( (this.nombre.length() < 3) || (this.nombre.length() > 50)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación",  "El nombre debe contener entre 2 y 50 caracteres.");
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
			fenomeno.setId_fenomeno(this.getId_fenomeno());
			fenomeno.setCodigo(this.getCodigo());
			fenomeno.setNombre(this.getNombre());
			fenomeno.setDescripcion(this.getDescripcion());
			fenomeno.setTelefono(this.getTelefono());
			
			
			fenomenoEJB.modificarFenomeno(fenomeno);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "EL Fenomeno fue actualizado", "OK");
			context.addMessage("", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			        
		} catch (ServiciosException  e) {
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "ERROR");
			context.addMessage("", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
	        return " ";
		}
		//POST-Redirect-GET 
		return "/pages/modificarFenomeno.xhtml?faces-redirect=true";
		
	}
	
	public void cancelar() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().
		redirect("/JSFPF/pages/listarFenomeno.xhtml?faces-redirect=true");
	}


	public Long getId_fenomeno() {
		return id_fenomeno;
	}


	public void setId_fenomeno(Long id_fenomeno) {
		this.id_fenomeno = id_fenomeno;
	}
}
