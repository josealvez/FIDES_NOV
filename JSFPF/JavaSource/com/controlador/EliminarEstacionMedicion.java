package com.controlador;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.dto.EstacionMedicionDTO;
import com.exception.ServiciosException;
import com.negocio.GestionEstacionMedicion;

@Named("bajaEM")
@ConversationScoped
public class EliminarEstacionMedicion implements Serializable{

	private static final long serialVersionUID = 1L;

	@EJB
	private GestionEstacionMedicion gEstacionMedicion;
	
	private Long id_estacionmedicion;
	private  String nombre;
	private  String parametro;
	private  String unidadMedida;
	private  String descripcion;
	private  String tipoDato;
	private  String departamento;
	private  String ciudad;
	private  String usuario;
	
	public void initConversation(){
	    if (!FacesContext.getCurrentInstance().isPostback() ) {
	    	FacesContext fc = FacesContext.getCurrentInstance();	
			if(!fc.getExternalContext().getRequestParameterMap().isEmpty()) {
				String nombreEstacion= fc.getExternalContext().getRequestParameterMap().get("nombre");
				try {
					EstacionMedicionDTO em = gEstacionMedicion.obtenerEstacionNombre(nombreEstacion);
					if(em != null) {
						this.id_estacionmedicion = em.getId_estacionmedicion();
						this.nombre = em.getNombre();
						this.ciudad = em.getCiudad();
						this.departamento = em.getDepartamento();
						this.descripcion = em.getDescripcion();
						this.parametro = em.getParametro();
						this.tipoDato = em.getTipoDato();
						this.unidadMedida = em.getUnidadMedida();
						this.usuario = em.getUsuario();
						System.out.print("ACA ESTA EL ID: " + em.getId_estacionmedicion());
					}
					System.out.print("No encontro por nombre");
				} catch (ServiciosException e) {
					FacesContext context = FacesContext.getCurrentInstance();
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "ERROR");
					context.addMessage("", message);
				}
			}
	    }	    
	}

	public GestionEstacionMedicion getgEstacionMedicion() {
		return gEstacionMedicion;
	}

	public void setgEstacionMedicion(GestionEstacionMedicion gEstacionMedicion) {
		this.gEstacionMedicion = gEstacionMedicion;
	}

	public Long getId_estacionmedicion() {
		return id_estacionmedicion;
	}

	public void setId_estacionmedicion(Long id_estacionmedicion) {
		this.id_estacionmedicion = id_estacionmedicion;
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
	
public String eliminar() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		String redirect = "/pages/eliminarEstacionMedicion.xhtml?faces-redirect=true";
		if(!this.nombre.isEmpty()) {
			try {
				
				gEstacionMedicion.bajaLogicaEM(this.nombre);;
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "La estacion fue Eliminada", "OK");
				context.addMessage("", message);
				context.getExternalContext().getFlash().setKeepMessages(true);
				
			} catch (ServiciosException e) {
				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "ERROR");
				context.addMessage("", message);
				context.getExternalContext().getFlash().setKeepMessages(true);
		        return " ";
			}
		}else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Debe buscar una Estacion");
			context.addMessage("", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}

		return redirect;
	}
	
	
}
